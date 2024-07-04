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
import org.dromara.system.domain.bo.TMqttAclBo;
import org.dromara.system.domain.vo.TMqttAclVo;
import org.dromara.system.domain.TMqttAcl;
import org.dromara.system.mapper.TMqttAclMapper;
import org.dromara.system.service.ITMqttAclService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * mqtt客户的acl规则，符合该规则的发布/订阅才可行Service业务层处理
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@RequiredArgsConstructor
@Service
public class TMqttAclServiceImpl implements ITMqttAclService {

    private final TMqttAclMapper baseMapper;

    /**
     * 查询mqtt客户的acl规则，符合该规则的发布/订阅才可行
     *
     * @param id 主键
     * @return mqtt客户的acl规则，符合该规则的发布/订阅才可行
     */
    @Override
    public TMqttAclVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询mqtt客户的acl规则，符合该规则的发布/订阅才可行列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return mqtt客户的acl规则，符合该规则的发布/订阅才可行分页列表
     */
    @Override
    public TableDataInfo<TMqttAclVo> queryPageList(TMqttAclBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TMqttAcl> lqw = buildQueryWrapper(bo);
        Page<TMqttAclVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的mqtt客户的acl规则，符合该规则的发布/订阅才可行列表
     *
     * @param bo 查询条件
     * @return mqtt客户的acl规则，符合该规则的发布/订阅才可行列表
     */
    @Override
    public List<TMqttAclVo> queryList(TMqttAclBo bo) {
        LambdaQueryWrapper<TMqttAcl> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TMqttAcl> buildQueryWrapper(TMqttAclBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TMqttAcl> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getUsername()), TMqttAcl::getUsername, bo.getUsername());
        lqw.eq(StringUtils.isNotBlank(bo.getTopic()), TMqttAcl::getTopic, bo.getTopic());
        lqw.eq(bo.getAccess() != null, TMqttAcl::getAccess, bo.getAccess());
        lqw.eq(bo.getAllow() != null, TMqttAcl::getAllow, bo.getAllow());
        return lqw;
    }

    /**
     * 新增mqtt客户的acl规则，符合该规则的发布/订阅才可行
     *
     * @param bo mqtt客户的acl规则，符合该规则的发布/订阅才可行
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TMqttAclBo bo) {
        TMqttAcl add = MapstructUtils.convert(bo, TMqttAcl.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改mqtt客户的acl规则，符合该规则的发布/订阅才可行
     *
     * @param bo mqtt客户的acl规则，符合该规则的发布/订阅才可行
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TMqttAclBo bo) {
        TMqttAcl update = MapstructUtils.convert(bo, TMqttAcl.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TMqttAcl entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除mqtt客户的acl规则，符合该规则的发布/订阅才可行信息
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
