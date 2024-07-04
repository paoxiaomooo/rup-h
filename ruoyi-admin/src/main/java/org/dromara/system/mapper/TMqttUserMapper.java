package org.dromara.system.mapper;

import org.dromara.system.domain.TMqttUser;
import org.dromara.system.domain.vo.TMqttUserVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * mqtt客户的连接鉴权，密码为sha256加密Mapper接口
 *
 * @author Lion Li
 * @date 2024-07-04
 */
public interface TMqttUserMapper extends BaseMapperPlus<TMqttUser, TMqttUserVo> {

}
