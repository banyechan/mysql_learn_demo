package com.banyechan.mysql_learn_demo.service;



import com.banyechan.mysql_learn_demo.entity.StudentModel;

import java.util.List;

public interface StudentService {

    StudentModel getByByPrimaryKey(Integer id);

    List<StudentModel> listStudent(StudentModel record);

    boolean  saveStudent(StudentModel record);

    boolean  updateStudent(StudentModel record);

    boolean  deleteStudent(Integer id);

    List<StudentModel> listAllStudent();


    boolean  batchAddStudent(int num);




}
