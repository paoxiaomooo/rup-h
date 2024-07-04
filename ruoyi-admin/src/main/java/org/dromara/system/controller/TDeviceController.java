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
import org.dromara.system.domain.vo.TDeviceVo;
import org.dromara.system.domain.bo.TDeviceBo;
import org.dromara.system.service.ITDeviceService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 设备信息管理
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/device")
public class TDeviceController extends BaseController {

    private final ITDeviceService tDeviceService;

    /**
     * 查询设备信息管理列表
     */
    @SaCheckPermission("system:device:list")
    @GetMapping("/list")
    public TableDataInfo<TDeviceVo> list(TDeviceBo bo, PageQuery pageQuery) {
        return tDeviceService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出设备信息管理列表
     */
    @SaCheckPermission("system:device:export")
    @Log(title = "设备信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TDeviceBo bo, HttpServletResponse response) {
        List<TDeviceVo> list = tDeviceService.queryList(bo);
        ExcelUtil.exportExcel(list, "设备信息管理", TDeviceVo.class, response);
    }

    /**
     * 获取设备信息管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:device:query")
    @GetMapping("/{id}")
    public R<TDeviceVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(tDeviceService.queryById(id));
    }

    /**
     * 新增设备信息管理
     */
    @SaCheckPermission("system:device:add")
    @Log(title = "设备信息管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@RequestHeader("Authorization") String authorizationHeader,@Validated(AddGroup.class) @RequestBody TDeviceBo bo) {
        return toAjax(tDeviceService.insertByBo(bo));
    }

    /**
     * 修改设备信息管理
     */
    @SaCheckPermission("system:device:edit")
    @Log(title = "设备信息管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TDeviceBo bo) {
        return toAjax(tDeviceService.updateByBo(bo));
    }

    /**
     * 删除设备信息管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:device:remove")
    @Log(title = "设备信息管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(tDeviceService.deleteWithValidByIds(List.of(ids), true));
    }
}
