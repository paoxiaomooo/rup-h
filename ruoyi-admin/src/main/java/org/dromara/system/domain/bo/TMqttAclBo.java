package org.dromara.system.domain.bo;

import org.dromara.system.domain.TMqttAcl;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * mqtt客户的acl规则，符合该规则的发布/订阅才可行业务对象 t_mqtt_acl
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TMqttAcl.class, reverseConvertGenerate = false)
public class TMqttAclBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String username;

    /**
     * 主题
     */
    @NotBlank(message = "主题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String topic;

    /**
     * 访问类型： 1->订阅；2->发布；3->订阅与发布
     */
    @NotNull(message = "访问类型： 1->订阅；2->发布；3->订阅与发布不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long access;

    /**
     * 是否允许访问：0-deny，1-allow
     */
    @NotNull(message = "是否允许访问：0-deny，1-allow不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long allow;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
