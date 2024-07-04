package org.dromara.system.service;

import org.dromara.system.domain.vo.TDeviceVo;
import org.dromara.system.domain.bo.TDeviceBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 设备信息管理Service接口
 *
 * @author Lion Li
 * @date 2024-07-04
 */
public interface ITDeviceService {

    /**
     * 查询设备信息管理
     *
     * @param id 主键
     * @return 设备信息管理
     */
    TDeviceVo queryById(Long id);

    /**
     * 分页查询设备信息管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 设备信息管理分页列表
     */
    TableDataInfo<TDeviceVo> queryPageList(TDeviceBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的设备信息管理列表
     *
     * @param bo 查询条件
     * @return 设备信息管理列表
     */
    List<TDeviceVo> queryList(TDeviceBo bo);

    /**
     * 新增设备信息管理
     *
     * @param bo 设备信息管理
     * @return 是否新增成功
     */
    Boolean insertByBo(TDeviceBo bo);

    /**
     * 修改设备信息管理
     *
     * @param bo 设备信息管理
     * @return 是否修改成功
     */
    Boolean updateByBo(TDeviceBo bo);

    /**
     * 校验并批量删除设备信息管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
