package org.dromara.system.service;

import org.dromara.system.domain.vo.TDeviceTypeVo;
import org.dromara.system.domain.bo.TDeviceTypeBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 设备类型管理Service接口
 *
 * @author Lion Li
 * @date 2024-07-04
 */
public interface ITDeviceTypeService {

    /**
     * 查询设备类型管理
     *
     * @param id 主键
     * @return 设备类型管理
     */
    TDeviceTypeVo queryById(Long id);

    /**
     * 分页查询设备类型管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 设备类型管理分页列表
     */
    TableDataInfo<TDeviceTypeVo> queryPageList(TDeviceTypeBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的设备类型管理列表
     *
     * @param bo 查询条件
     * @return 设备类型管理列表
     */
    List<TDeviceTypeVo> queryList(TDeviceTypeBo bo);

    /**
     * 新增设备类型管理
     *
     * @param bo 设备类型管理
     * @return 是否新增成功
     */
    Boolean insertByBo(TDeviceTypeBo bo);

    /**
     * 修改设备类型管理
     *
     * @param bo 设备类型管理
     * @return 是否修改成功
     */
    Boolean updateByBo(TDeviceTypeBo bo);

    /**
     * 校验并批量删除设备类型管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
