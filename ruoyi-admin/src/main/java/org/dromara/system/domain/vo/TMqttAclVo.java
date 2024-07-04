package org.dromara.system.domain.vo;

import org.dromara.system.domain.TMqttAcl;
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
 * mqtt客户的acl规则，符合该规则的发布/订阅才可行视图对象 t_mqtt_acl
 *
 * @author Lion Li
 * @date 2024-07-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TMqttAcl.class)
public class TMqttAclVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 用户名
     */
    @ExcelProperty(value = "用户名")
    private String username;

    /**
     * 主题
     */
    @ExcelProperty(value = "主题")
    private String topic;

    /**
     * 访问类型： 1->订阅；2->发布；3->订阅与发布
     */
    @ExcelProperty(value = "访问类型： 1->订阅；2->发布；3->订阅与发布")
    private Long access;

    /**
     * 是否允许访问：0-deny，1-allow
     */
    @ExcelProperty(value = "是否允许访问：0-deny，1-allow")
    private Long allow;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
