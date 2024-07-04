package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * mqtt客户的acl规则，符合该规则的发布/订阅才可行对象 t_mqtt_acl
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_mqtt_acl")
public class TMqttAcl extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 主题
     */
    private String topic;

    /**
     * 访问类型： 1->订阅；2->发布；3->订阅与发布
     */
    private Long access;

    /**
     * 是否允许访问：0-deny，1-allow
     */
    private Long allow;

    /**
     * 备注
     */
    private String remark;


}
