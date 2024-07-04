package org.dromara.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.system.domain.TMqttAcl;
import org.dromara.system.domain.TMqttUser;
import org.dromara.system.domain.bo.TMqttUserBo;
import org.dromara.system.domain.vo.TMqttUserVo;
import org.dromara.system.mapper.TMqttAclMapper;
import org.dromara.system.mapper.TMqttUserMapper;
import org.springframework.stereotype.Service;
import org.dromara.system.domain.bo.TDeviceBo;
import org.dromara.system.domain.vo.TDeviceVo;
import org.dromara.system.domain.TDevice;
import org.dromara.system.mapper.TDeviceMapper;
import org.dromara.system.service.ITDeviceService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 设备信息管理Service业务层处理
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@RequiredArgsConstructor
@Service
public class TDeviceServiceImpl implements ITDeviceService {

    private final TDeviceMapper baseMapper;
    private final TMqttUserMapper tMqttUserMapper;
    private final TMqttAclMapper tMqttAclMapper;
    /**
     * 查询设备信息管理
     *
     * @param id 主键
     * @return 设备信息管理
     */
    @Override
    public TDeviceVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询设备信息管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 设备信息管理分页列表
     */
    @Override
    public TableDataInfo<TDeviceVo> queryPageList(TDeviceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TDevice> lqw = buildQueryWrapper(bo);
        Page<TDeviceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的设备信息管理列表
     *
     * @param bo 查询条件
     * @return 设备信息管理列表
     */
    @Override
    public List<TDeviceVo> queryList(TDeviceBo bo) {
        LambdaQueryWrapper<TDevice> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TDevice> buildQueryWrapper(TDeviceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TDevice> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getSerialNum()), TDevice::getSerialNum, bo.getSerialNum());
        lqw.eq(bo.getDeviceTypeId() != null, TDevice::getDeviceTypeId, bo.getDeviceTypeId());
        lqw.eq(bo.getDeleted() != null, TDevice::getDeleted, bo.getDeleted());
        return lqw;
    }

    /**
     * 新增设备信息管理
     *
     * @param bo 设备信息管理
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TDeviceBo bo) {
        TDevice add = MapstructUtils.convert(bo, TDevice.class);
        QueryWrapper<TMqttUser> queryWrapper = new QueryWrapper<>();
        // 设置查询条件：status 字段等于指定值
        queryWrapper.eq("username", add.getSerialNum());
        if(tMqttUserMapper.selectCount(queryWrapper)>0){//没有该设备再插入
           return false;
        }
        TMqttUser tMqttUser=new TMqttUser();
        tMqttUser.setUsername(add.getSerialNum());
        tMqttUserMapper.insert(tMqttUser);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
            String s1="/zustse/dev/"+ add.getId()+"/cmd";
            String s2="/zustse/dev/"+ add.getId()+"/data";
            String username=add.getSerialNum();
            TMqttAcl acl1=new TMqttAcl();
            TMqttAcl acl2=new TMqttAcl();
            acl1.setTopic(s1);
            acl1.setUsername(username);
            acl1.setAccess(1L);//订阅
            acl1.setAllow(1L);
            acl2.setTopic(s2);
            acl2.setUsername(username);
            acl2.setAccess(2L);//发布
            acl2.setAllow(1L);
            tMqttAclMapper.insert(acl1);
            tMqttAclMapper.insert(acl2);
        }
        return flag;
    }

    /**
     * 修改设备信息管理
     *
     * @param bo 设备信息管理
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TDeviceBo bo) {
        TDevice update = MapstructUtils.convert(bo, TDevice.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TDevice entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除设备信息管理信息
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
        QueryWrapper<TDevice> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",ids);
        List<TDevice>userList=baseMapper.selectList(queryWrapper);
        List<String> serialNumList = userList.stream()
            .map(TDevice::getSerialNum)
            .toList();
        QueryWrapper<TMqttUser>queryWrapperUser=new QueryWrapper<>();
        queryWrapperUser.in("username",serialNumList);
        tMqttUserMapper.delete(queryWrapperUser);
        QueryWrapper<TMqttAcl>queryWrapperAcl=new QueryWrapper<>();
        queryWrapperAcl.in("username",serialNumList);
        tMqttAclMapper.delete(queryWrapperAcl);
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
