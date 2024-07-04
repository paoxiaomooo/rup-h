package org.dromara.system.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.system.domain.vo.TMqttUserVo;
import org.dromara.system.domain.bo.TMqttUserBo;
import org.dromara.system.service.ITMqttUserService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * mqtt客户的连接鉴权，密码为sha256加密
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/mqttUser")
public class TMqttUserController extends BaseController {

    private final ITMqttUserService tMqttUserService;

    /**
     * 查询mqtt客户的连接鉴权，密码为sha256加密列表
     */
    @SaCheckPermission("system:mqttUser:list")
    @GetMapping("/list")
    public TableDataInfo<TMqttUserVo> list(TMqttUserBo bo, PageQuery pageQuery) {
        return tMqttUserService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出mqtt客户的连接鉴权，密码为sha256加密列表
     */
    @SaCheckPermission("system:mqttUser:export")
    @Log(title = "mqtt客户的连接鉴权，密码为sha256加密", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TMqttUserBo bo, HttpServletResponse response) {
        List<TMqttUserVo> list = tMqttUserService.queryList(bo);
        ExcelUtil.exportExcel(list, "mqtt客户的连接鉴权，密码为sha256加密", TMqttUserVo.class, response);
    }

    /**
     * 获取mqtt客户的连接鉴权，密码为sha256加密详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:mqttUser:query")
    @GetMapping("/{id}")
    public R<TMqttUserVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(tMqttUserService.queryById(id));
    }

    /**
     * 新增mqtt客户的连接鉴权，密码为sha256加密
     */
    @SaCheckPermission("system:mqttUser:add")
    @Log(title = "mqtt客户的连接鉴权，密码为sha256加密", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TMqttUserBo bo) {
        return toAjax(tMqttUserService.insertByBo(bo));
    }

    /**
     * 修改mqtt客户的连接鉴权，密码为sha256加密
     */
    @SaCheckPermission("system:mqttUser:edit")
    @Log(title = "mqtt客户的连接鉴权，密码为sha256加密", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TMqttUserBo bo) {
        return toAjax(tMqttUserService.updateByBo(bo));
    }

    /**
     * 删除mqtt客户的连接鉴权，密码为sha256加密
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:mqttUser:remove")
    @Log(title = "mqtt客户的连接鉴权，密码为sha256加密", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(tMqttUserService.deleteWithValidByIds(List.of(ids), true));
    }
}
