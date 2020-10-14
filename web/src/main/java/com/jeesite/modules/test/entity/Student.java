package com.jeesite.modules.test.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * @Author: eddie
 * @Date: 2020/10/13 12:06
 **/


@Table(name = "student", alias = "s", columns = {
        @Column(name = "student_id", attrName = "studentId", isPK = true),
        @Column(name = "student_name", attrName = "studentName", queryType = QueryType.LIKE),
        @Column(name = "sex", attrName = "sex", label = "性别"),
        @Column(name = "student_number", attrName = "studentNumber"),
        @Column(includeEntity = DataEntity.class)
})


public class Student extends DataEntity<Student> {
    private Integer studentId;
    private String studentName;
    private String sex;
    private Integer studentNumber;

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", sex='" + sex + '\'' +
                ", studentNumber=" + studentNumber +
                '}';
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    @ExcelField(title = "学生姓名", align = ExcelField.Align.CENTER)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @ExcelField(title = "性别", align = ExcelField.Align.CENTER)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @ExcelField(title = "学生证号码", align = ExcelField.Align.CENTER)
    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }
}
