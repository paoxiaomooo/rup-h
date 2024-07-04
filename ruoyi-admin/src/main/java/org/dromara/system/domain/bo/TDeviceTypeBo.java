package org.dromara.system.domain.bo;

import org.dromara.system.domain.TDeviceType;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 设备类型管理业务对象 t_device_type
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TDeviceType.class, reverseConvertGenerate = false)
public class TDeviceTypeBo extends BaseEntity {

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 设备类型名
     */
    @NotBlank(message = "设备类型名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 型号
     */
    @NotBlank(message = "型号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String deviceModel;

    /**
     * 设备码
     */
    @NotBlank(message = "设备码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String code;

    /**
     * 设备图片
     */
    @NotBlank(message = "设备图片不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pic;

    /**
     * json自定义属性
     */
    @NotBlank(message = "json自定义属性不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attributes;

    /**
     * 1:已删除，0:正常
     */
    @NotNull(message = "1:已删除，0:正常不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer deleted;


}
