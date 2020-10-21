package com.banyechan.mysql_learn_demo;

import com.banyechan.mysql_learn_demo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MysqlLearnDemoApplicationTests {

    @Autowired
    private StudentService studentService;

    @Test
    void contextLoads() {
    }



    @Test
    public void batchAddStudent() {
        studentService.batchAddStudent(500);
    }

}
