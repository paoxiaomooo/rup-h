package org.dromara.system.domain.vo;

import org.dromara.system.domain.TMqttUser;
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
 * mqtt客户的连接鉴权，密码为sha256加密视图对象 t_mqtt_user
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TMqttUser.class)
public class TMqttUserVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ExcelProperty(value = "编号")
    private Long id;

    /**
     * 用户名
     */
    @ExcelProperty(value = "用户名")
    private String username;

    /**
     * 密码
     */
    @ExcelProperty(value = "密码")
    private String password;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
