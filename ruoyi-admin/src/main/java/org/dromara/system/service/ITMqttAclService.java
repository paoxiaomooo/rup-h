package org.dromara.system.service;

import org.dromara.system.domain.vo.TMqttAclVo;
import org.dromara.system.domain.bo.TMqttAclBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * mqtt客户的acl规则，符合该规则的发布/订阅才可行Service接口
 *
 * @author Lion Li
 * @date 2024-07-04
 */
public interface ITMqttAclService {

    /**
     * 查询mqtt客户的acl规则，符合该规则的发布/订阅才可行
     *
     * @param id 主键
     * @return mqtt客户的acl规则，符合该规则的发布/订阅才可行
     */
    TMqttAclVo queryById(Long id);

    /**
     * 分页查询mqtt客户的acl规则，符合该规则的发布/订阅才可行列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return mqtt客户的acl规则，符合该规则的发布/订阅才可行分页列表
     */
    TableDataInfo<TMqttAclVo> queryPageList(TMqttAclBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的mqtt客户的acl规则，符合该规则的发布/订阅才可行列表
     *
     * @param bo 查询条件
     * @return mqtt客户的acl规则，符合该规则的发布/订阅才可行列表
     */
    List<TMqttAclVo> queryList(TMqttAclBo bo);

    /**
     * 新增mqtt客户的acl规则，符合该规则的发布/订阅才可行
     *
     * @param bo mqtt客户的acl规则，符合该规则的发布/订阅才可行
     * @return 是否新增成功
     */
    Boolean insertByBo(TMqttAclBo bo);

    /**
     * 修改mqtt客户的acl规则，符合该规则的发布/订阅才可行
     *
     * @param bo mqtt客户的acl规则，符合该规则的发布/订阅才可行
     * @return 是否修改成功
     */
    Boolean updateByBo(TMqttAclBo bo);

    /**
     * 校验并批量删除mqtt客户的acl规则，符合该规则的发布/订阅才可行信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
