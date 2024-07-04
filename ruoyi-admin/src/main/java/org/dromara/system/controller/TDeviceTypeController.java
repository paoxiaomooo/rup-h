package org.dromara.system.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.satoken.utils.LoginHelper;
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
import org.dromara.system.domain.vo.TDeviceTypeVo;
import org.dromara.system.domain.bo.TDeviceTypeBo;
import org.dromara.system.service.ITDeviceTypeService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 设备类型管理
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/deviceType")
public class TDeviceTypeController extends BaseController {

    private final ITDeviceTypeService tDeviceTypeService;

    /**
     * 查询设备类型管理列表
     */
    @SaCheckPermission("system:deviceType:list")
    @GetMapping("/list")
    public TableDataInfo<TDeviceTypeVo> list(TDeviceTypeBo bo, PageQuery pageQuery) {
        return tDeviceTypeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出设备类型管理列表
     */
    @SaCheckPermission("system:deviceType:export")
    @Log(title = "设备类型管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TDeviceTypeBo bo, HttpServletResponse response) {
        List<TDeviceTypeVo> list = tDeviceTypeService.queryList(bo);
        ExcelUtil.exportExcel(list, "设备类型管理", TDeviceTypeVo.class, response);
    }

    /**
     * 获取设备类型管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:deviceType:query")
    @GetMapping("/{id}")
    public R<TDeviceTypeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(tDeviceTypeService.queryById(id));
    }

    /**
     * 新增设备类型管理
     */
    @SaCheckPermission("system:deviceType:add")
    @Log(title = "设备类型管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@RequestHeader("Authorization") String authorizationHeader,@Validated(AddGroup.class) @RequestBody TDeviceTypeBo bo) {
        String token = authorizationHeader.substring(7);
//        System.out.println(token);
        LoginUser user = LoginHelper.getLoginUser(token);
        System.out.println(user);
        return toAjax(tDeviceTypeService.insertByBo(bo));
    }

    /**
     * 修改设备类型管理
     */
    @SaCheckPermission("system:deviceType:edit")
    @Log(title = "设备类型管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TDeviceTypeBo bo) {
        return toAjax(tDeviceTypeService.updateByBo(bo));
    }

    /**
     * 删除设备类型管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:deviceType:remove")
    @Log(title = "设备类型管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(tDeviceTypeService.deleteWithValidByIds(List.of(ids), true));
    }
}
