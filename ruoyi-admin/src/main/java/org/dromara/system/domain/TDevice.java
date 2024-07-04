package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 设备信息管理对象 t_device
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_device")
public class TDevice extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 设备编号
     */
    private String serialNum;

    /**
     * 外键, 设备类型id
     */
    private Long deviceTypeId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 1:已删除，0:正常
     */
    private Integer deleted;


}
