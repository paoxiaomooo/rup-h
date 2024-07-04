package org.dromara.system.domain.vo;

import org.dromara.system.domain.TDeviceType;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 设备类型管理视图对象 t_device_type
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TDeviceType.class)
public class TDeviceTypeVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 设备类型名
     */
    @ExcelProperty(value = "设备类型名")
    private String name;

    /**
     * 型号
     */
    @ExcelProperty(value = "型号")
    private String deviceModel;

    /**
     * 设备码
     */
    @ExcelProperty(value = "设备码")
    private String code;

    /**
     * 设备图片
     */
    @ExcelProperty(value = "设备图片")
    private String pic;

    /**
     * json自定义属性
     */
    @ExcelProperty(value = "json自定义属性")
    private String attributes;

    /**
     * 1:已删除，0:正常
     */
    @ExcelProperty(value = "1:已删除，0:正常")
    private Integer deleted;


}
