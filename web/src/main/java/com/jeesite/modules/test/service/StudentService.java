package com.jeesite.modules.test.service;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.dao.StudentDao;
import com.jeesite.modules.test.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: eddie
 * @Date: 2020/10/13 12:13
 **/

@Service
@Transactional(readOnly = false)
public class StudentService extends CrudService<StudentDao, Student> {

    @Autowired
    private StudentDao studentDao;

    /**
     * 列出所有学生信息
     *
     * @return
     */
    public List<Student> getAllStudentInfo() {
        return studentDao.findAll();
    }

    /**
     * 更新学生信息
     *
     * @param id
     */
    public void updateStudentInfo(String studentName,int sex,String studentNumber, int id) {
       studentDao.updateInfo(studentName,sex,studentNumber,id);
    }

    /**
     * 获取单条记录
     *
     * @param student
     */

    public Student getStudentInfoById(Student student) {
        return studentDao.getInfoById(student);
    }

    /**
     * 添加学生信息
     * @param student
     */

    public void addStudentInfo(Student student) {
        studentDao.add(student);
    }

    /**
     * 删除学生信息
     *
     * @param student
     */

    public void deleteStudentInfo(Student student) {
        super.delete(student);
    }

    public Student findStudentInfoById(int id) {
        return studentDao.findInfoById(id);
    }

    public Student findByStudentNumber(String number){
        return studentDao.findByStudentNumber(number);
    }
}
