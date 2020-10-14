package com.jeesite.modules.test.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.Student;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author: eddie
 * @Date: 2020/10/13 12:05
 **/
@MyBatisDao
public interface StudentDao extends CrudDao<Student> {

    List<Student> findAll();

    Student getInfoById(Student student);

    Student findInfoById(int id);

    Student findByStudentNumber(String number);

    void add(Student student);

    void updateInfo(@Param("student_name")String studentName,
                    @Param("sex")int sex,
                    @Param("student_number")String studentNumber,int studentId);
}
