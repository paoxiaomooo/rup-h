package org.dromara.system.service;

import org.dromara.system.domain.vo.TMqttUserVo;
import org.dromara.system.domain.bo.TMqttUserBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * mqtt客户的连接鉴权，密码为sha256加密Service接口
 *
 * @author Lion Li
 * @date 2024-07-04
 */
public interface ITMqttUserService {

    /**
     * 查询mqtt客户的连接鉴权，密码为sha256加密
     *
     * @param id 主键
     * @return mqtt客户的连接鉴权，密码为sha256加密
     */
    TMqttUserVo queryById(Long id);

    /**
     * 分页查询mqtt客户的连接鉴权，密码为sha256加密列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return mqtt客户的连接鉴权，密码为sha256加密分页列表
     */
    TableDataInfo<TMqttUserVo> queryPageList(TMqttUserBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的mqtt客户的连接鉴权，密码为sha256加密列表
     *
     * @param bo 查询条件
     * @return mqtt客户的连接鉴权，密码为sha256加密列表
     */
    List<TMqttUserVo> queryList(TMqttUserBo bo);

    /**
     * 新增mqtt客户的连接鉴权，密码为sha256加密
     *
     * @param bo mqtt客户的连接鉴权，密码为sha256加密
     * @return 是否新增成功
     */
    Boolean insertByBo(TMqttUserBo bo);

    /**
     * 修改mqtt客户的连接鉴权，密码为sha256加密
     *
     * @param bo mqtt客户的连接鉴权，密码为sha256加密
     * @return 是否修改成功
     */
    Boolean updateByBo(TMqttUserBo bo);

    /**
     * 校验并批量删除mqtt客户的连接鉴权，密码为sha256加密信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
