package com.book.backend.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Per
 */
@Data
public class DemoData {
    @ExcelProperty(value="整数",index = 0)
    private Integer number;
    @ExcelProperty(value="字符串",index = 1)
    private String  string;
    @ExcelProperty(value="小数",index =2)
    private Double price;
    @ExcelProperty(value="日期",index = 3)
    private Date datetime;
}
