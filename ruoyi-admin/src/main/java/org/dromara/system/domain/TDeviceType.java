package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 设备类型管理对象 t_device_type
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_device_type")
public class TDeviceType extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 设备类型名
     */
    private String name;

    /**
     * 型号
     */
    private String deviceModel;

    /**
     * 设备码
     */
    private String code;

    /**
     * 设备图片
     */
    private String pic;

    /**
     * json自定义属性
     */
    private String attributes;

    /**
     * 1:已删除，0:正常
     */
    private Integer deleted;


}
