package org.dromara.system.domain.bo;

import org.dromara.system.domain.TDevice;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 设备信息管理业务对象 t_device
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TDevice.class, reverseConvertGenerate = false)
public class TDeviceBo extends BaseEntity {

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 设备编号
     */
    @NotBlank(message = "设备编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String serialNum;

    /**
     * 外键, 设备类型id
     */
    @NotNull(message = "外键, 设备类型id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long deviceTypeId;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;

    /**
     * 1:已删除，0:正常
     */
    @NotNull(message = "1:已删除，0:正常不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer deleted;


}
