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
import org.dromara.system.domain.vo.TMqttAclVo;
import org.dromara.system.domain.bo.TMqttAclBo;
import org.dromara.system.service.ITMqttAclService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * mqtt客户的acl规则，符合该规则的发布/订阅才可行
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/mqttAcl")
public class TMqttAclController extends BaseController {

    private final ITMqttAclService tMqttAclService;

    /**
     * 查询mqtt客户的acl规则，符合该规则的发布/订阅才可行列表
     */
    @SaCheckPermission("system:mqttAcl:list")
    @GetMapping("/list")
    public TableDataInfo<TMqttAclVo> list(TMqttAclBo bo, PageQuery pageQuery) {
        return tMqttAclService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出mqtt客户的acl规则，符合该规则的发布/订阅才可行列表
     */
    @SaCheckPermission("system:mqttAcl:export")
    @Log(title = "mqtt客户的acl规则，符合该规则的发布/订阅才可行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TMqttAclBo bo, HttpServletResponse response) {
        List<TMqttAclVo> list = tMqttAclService.queryList(bo);
        ExcelUtil.exportExcel(list, "mqtt客户的acl规则，符合该规则的发布/订阅才可行", TMqttAclVo.class, response);
    }

    /**
     * 获取mqtt客户的acl规则，符合该规则的发布/订阅才可行详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:mqttAcl:query")
    @GetMapping("/{id}")
    public R<TMqttAclVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(tMqttAclService.queryById(id));
    }

    /**
     * 新增mqtt客户的acl规则，符合该规则的发布/订阅才可行
     */
    @SaCheckPermission("system:mqttAcl:add")
    @Log(title = "mqtt客户的acl规则，符合该规则的发布/订阅才可行", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TMqttAclBo bo) {
        return toAjax(tMqttAclService.insertByBo(bo));
    }

    /**
     * 修改mqtt客户的acl规则，符合该规则的发布/订阅才可行
     */
    @SaCheckPermission("system:mqttAcl:edit")
    @Log(title = "mqtt客户的acl规则，符合该规则的发布/订阅才可行", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TMqttAclBo bo) {
        return toAjax(tMqttAclService.updateByBo(bo));
    }

    /**
     * 删除mqtt客户的acl规则，符合该规则的发布/订阅才可行
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:mqttAcl:remove")
    @Log(title = "mqtt客户的acl规则，符合该规则的发布/订阅才可行", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(tMqttAclService.deleteWithValidByIds(List.of(ids), true));
    }
}
