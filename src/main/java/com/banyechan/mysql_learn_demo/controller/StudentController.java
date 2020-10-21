package com.banyechan.mysql_learn_demo.controller;


import com.banyechan.mysql_learn_demo.entity.StudentModel;
import com.banyechan.mysql_learn_demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/list")
    public List<StudentModel> getStudentList(@RequestBody StudentModel record) {
        log.info("----  调用列表查询接口 -----");
        List<StudentModel> resultList = studentService.listStudent(record);
        return resultList;
    }

    @GetMapping("/get")
    public StudentModel getStudent(@RequestParam("id") Integer id) {
        log.info("----  调用单个查询接口,id:{} -----",id);
        StudentModel result = studentService.getByByPrimaryKey(id);
        return result;
    }

    @PostMapping("/add")
    public boolean addStudent(@RequestBody StudentModel record) {
        log.info("----  调用新增接口 -----");
        return studentService.saveStudent(record);
    }

    @PostMapping("/update")
    public boolean updateStudent(@RequestBody StudentModel record) {
        log.info("----  调用修改接口 -----");
        return studentService.updateStudent(record);
    }

    @DeleteMapping("/del")
    public boolean deleteStudent(Integer id) {
        log.info("----  调用删除接口,id:{} -----",id);
        return studentService.deleteStudent(id);
    }

    // 批量添加
    @GetMapping("/batch")
    public boolean batchAddStudent() {
        return studentService.batchAddStudent(1);
    }


}
