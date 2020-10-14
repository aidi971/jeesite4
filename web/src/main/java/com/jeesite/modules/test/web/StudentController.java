package com.jeesite.modules.test.web;

import com.jeesite.common.utils.excel.ExcelImport;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.response.Response;
import com.jeesite.modules.test.entity.Student;
import com.jeesite.modules.test.service.StudentService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;

/**
 * @Author: eddie
 * @Date: 2020/10/13 13:19
 **/

@RestController
@RequestMapping("/student")
public class StudentController extends BaseController {
    @Autowired
    private StudentService studentService;


    @CrossOrigin
    @GetMapping("/all")
    public List<Student> getAllStudent() {
        return studentService.getAllStudentInfo();
    }

    @CrossOrigin
    @PostMapping("/findby/{id}")
    public Response findStudentInfo(@PathVariable("id") int id) {
        Student student = studentService.findStudentInfoById(id);
        System.out.println(student);
        return new Response(200, "成功", student);
    }

    @CrossOrigin
    @PostMapping("/add")
    public Response addStudentInfo(@RequestBody Student requestStudent) {
        Student student = new Student();
        Student check = studentService.findByStudentNumber(requestStudent.getStudentNumber());
        if (check != null) {
            return new Response(200, "失败", "该学生号已存在");
        } else {
            student.setSex(requestStudent.getSex());
            student.setStudentName(requestStudent.getStudentName());
            student.setStudentNumber(requestStudent.getStudentNumber());
            studentService.addStudentInfo(requestStudent);
            return new Response(200, "成功", student);
        }
    }

    @CrossOrigin
    @PostMapping("/update/{id}")
    public Response updateStudentInfo(@RequestBody Student requestStudent, @PathVariable("id") int id) {
        Student student = studentService.findStudentInfoById(id);
        Student check = studentService.findByStudentNumber(requestStudent.getStudentNumber());
        if (check != null) {
            return new Response(200, "失败", "该学生号已存在");
        } else {
            studentService.updateStudentInfo(requestStudent.getStudentName(),
                    requestStudent.getSex(),
                    requestStudent.getStudentNumber(),
                    id);
            return new Response(200, "成功", requestStudent);
        }
    }

    @CrossOrigin
    @PostMapping("/delete/{id}")
    public Response deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudentInfo(id);
        return new Response(200, "成功", null);
    }

    //
//    @CrossOrigin
//    @RequestMapping(value = "save",method = RequestMethod.POST)
//    public Response excelImport(@RequestParam("file") MultipartFile file) throws Exception {
//        int successNum = 0;
//        int failNum = 0;
//        StringBuilder failMsg = new StringBuilder();
//        ExcelImport ex = new ExcelImport(file, 0, 0);
//        List<Student> list = ex.getDataList(Student.class);
//        if (list != null) {
//            for (Student student : list) {
//                studentService.addStudentInfo(student);
//                successNum++;
//
//            }
//        } else {
//            return new Response(500, "检查文件格式或excel为空", null);
//        }
//        return new Response(200, "成功", null);
//    }

    @CrossOrigin
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Response excelImport(@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException, InvalidFormatException {
        try {
            int successNum = 0;
            int failNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ExcelImport excelImport = new ExcelImport(multipartFile, 1,"Sheet1");
            List<Student> list = excelImport.getDataList(Student.class);
            if (list == null) {
                return new Response(503, "导入失败", "数据为空");
            }
            for (Student student : list) {
                try {
                    studentService.addStudentInfo(student);
                    successNum++;
                } catch (ConstraintViolationException e) {
                    failNum++;
                } catch (Exception e) {
                    failNum++;
                }
            }
            if (failNum > 0) {
                failureMsg.insert(0, ",失败" + failNum + "条记录。");
                return new Response(501, "失败", failureMsg);
            }
            return new Response(200, "成功导入"+successNum + "条记录",list );

        } catch (Exception e) {
            return new Response(502, "导入失败，检查文件类型", e.getMessage());
        }
//        for (int i = excelImport.getDataRowNum();i<excelImport.getLastDataRowNum();i++){
//            Row row = excelImport.getRow(i);
//            for (int j=0;j<excelImport.getLastCellNum();j++){
//                Object v = excelImport.getCellValue(row,j);
//                studentService.save();
//            }
//        }
//      }
    }
}

//    public Response importFile(Student student, MultipartFile file) {
//        try {
//            ExcelUtil util = new ExcelUtil();
//            List<Map<String, Object>> list = util.getExcelInfo(file);
//            Map<String,Object> map = new HashMap<String, Object>();
//
//            if (list == null) {
//                return new Response(500, "失败", "别整个空文档啊！");
//            }
//            int arr = list.size();
//            List<Object[]> list1 = new ArrayList<Object[]>();
//            for (int i =0;i<arr;i++) {
//            }
//            int arr1 = list.stream().toArray().length;
//            List<Student> objectList = new ArrayList<Student>();
//            Object objects = list.toArray();
//
//            for (int i =0;i<arr1;i++){
//                student.setStudentId();
//
//            }
//
//            System.out.println(list);
//
//        } catch (Exception e) {
//            return new Response(501, "失败", e.getMessage());
//        }
//        return new Response(200, "成功", "导入成功");
//    }

