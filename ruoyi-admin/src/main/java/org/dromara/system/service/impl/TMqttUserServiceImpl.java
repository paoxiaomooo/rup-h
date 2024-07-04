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
import org.dromara.system.domain.bo.TMqttUserBo;
import org.dromara.system.domain.vo.TMqttUserVo;
import org.dromara.system.domain.TMqttUser;
import org.dromara.system.mapper.TMqttUserMapper;
import org.dromara.system.service.ITMqttUserService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * mqtt客户的连接鉴权，密码为sha256加密Service业务层处理
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@RequiredArgsConstructor
@Service
public class TMqttUserServiceImpl implements ITMqttUserService {

    private final TMqttUserMapper baseMapper;

    /**
     * 查询mqtt客户的连接鉴权，密码为sha256加密
     *
     * @param id 主键
     * @return mqtt客户的连接鉴权，密码为sha256加密
     */
    @Override
    public TMqttUserVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询mqtt客户的连接鉴权，密码为sha256加密列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return mqtt客户的连接鉴权，密码为sha256加密分页列表
     */
    @Override
    public TableDataInfo<TMqttUserVo> queryPageList(TMqttUserBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TMqttUser> lqw = buildQueryWrapper(bo);
        Page<TMqttUserVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的mqtt客户的连接鉴权，密码为sha256加密列表
     *
     * @param bo 查询条件
     * @return mqtt客户的连接鉴权，密码为sha256加密列表
     */
    @Override
    public List<TMqttUserVo> queryList(TMqttUserBo bo) {
        LambdaQueryWrapper<TMqttUser> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TMqttUser> buildQueryWrapper(TMqttUserBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TMqttUser> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getUsername()), TMqttUser::getUsername, bo.getUsername());
        lqw.eq(StringUtils.isNotBlank(bo.getPassword()), TMqttUser::getPassword, bo.getPassword());
        return lqw;
    }

    /**
     * 新增mqtt客户的连接鉴权，密码为sha256加密
     *
     * @param bo mqtt客户的连接鉴权，密码为sha256加密
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TMqttUserBo bo) {
        TMqttUser add = MapstructUtils.convert(bo, TMqttUser.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改mqtt客户的连接鉴权，密码为sha256加密
     *
     * @param bo mqtt客户的连接鉴权，密码为sha256加密
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TMqttUserBo bo) {
        TMqttUser update = MapstructUtils.convert(bo, TMqttUser.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TMqttUser entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除mqtt客户的连接鉴权，密码为sha256加密信息
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
