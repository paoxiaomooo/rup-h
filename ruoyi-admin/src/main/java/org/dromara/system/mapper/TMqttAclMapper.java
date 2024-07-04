package org.dromara.system.mapper;

import org.dromara.system.domain.TMqttAcl;
import org.dromara.system.domain.vo.TMqttAclVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * mqtt客户的acl规则，符合该规则的发布/订阅才可行Mapper接口
 *
 * @author Lion Li
 * @date 2024-07-04
 */
public interface TMqttAclMapper extends BaseMapperPlus<TMqttAcl, TMqttAclVo> {

}
