package org.dromara.system.domain.vo;

import org.dromara.system.domain.TDevice;
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
 * 设备信息管理视图对象 t_device
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TDevice.class)
public class TDeviceVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 设备编号
     */
    @ExcelProperty(value = "设备编号")
    private String serialNum;

    /**
     * 外键, 设备类型id
     */
    @ExcelProperty(value = "外键, 设备类型id")
    private Long deviceTypeId;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 1:已删除，0:正常
     */
    @ExcelProperty(value = "1:已删除，0:正常")
    private Integer deleted;


}
