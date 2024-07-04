package org.dromara.system.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.system.domain.bo.TDeviceTypeBo;
import org.dromara.system.domain.vo.TDeviceTypeVo;
import org.dromara.system.domain.TDeviceType;
import org.dromara.system.mapper.TDeviceTypeMapper;
import org.dromara.system.service.ITDeviceTypeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 设备类型管理Service业务层处理
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@RequiredArgsConstructor
@Service
public class TDeviceTypeServiceImpl implements ITDeviceTypeService {

    private final TDeviceTypeMapper baseMapper;

    /**
     * 查询设备类型管理
     *
     * @param id 主键
     * @return 设备类型管理
     */
    @Override
    public TDeviceTypeVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询设备类型管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 设备类型管理分页列表
     */
    @Override
    public TableDataInfo<TDeviceTypeVo> queryPageList(TDeviceTypeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TDeviceType> lqw = buildQueryWrapper(bo);
        Page<TDeviceTypeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的设备类型管理列表
     *
     * @param bo 查询条件
     * @return 设备类型管理列表
     */
    @Override
    public List<TDeviceTypeVo> queryList(TDeviceTypeBo bo) {
        LambdaQueryWrapper<TDeviceType> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TDeviceType> buildQueryWrapper(TDeviceTypeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TDeviceType> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), TDeviceType::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getDeviceModel()), TDeviceType::getDeviceModel, bo.getDeviceModel());
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), TDeviceType::getCode, bo.getCode());
        lqw.eq(StringUtils.isNotBlank(bo.getPic()), TDeviceType::getPic, bo.getPic());
        lqw.eq(StringUtils.isNotBlank(bo.getAttributes()), TDeviceType::getAttributes, bo.getAttributes());
        lqw.eq(bo.getDeleted() != null, TDeviceType::getDeleted, bo.getDeleted());
        return lqw;
    }

    /**
     * 新增设备类型管理
     *
     * @param bo 设备类型管理
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TDeviceTypeBo bo) {
        TDeviceType add = MapstructUtils.convert(bo, TDeviceType.class);
        validEntityBeforeSave(add);
        //System.out.println(add);
        boolean flag = baseMapper.insert(add) > 0;

        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
//        return true;
    }

    /**
     * 修改设备类型管理
     *
     * @param bo 设备类型管理
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TDeviceTypeBo bo) {
        TDeviceType update = MapstructUtils.convert(bo, TDeviceType.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TDeviceType entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除设备类型管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
