package com.banyechan.mysql_learn_demo.mapper;


import com.banyechan.mysql_learn_demo.entity.StudentModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface StudentModelMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StudentModel record);

    int insertSelective(StudentModel record);

    StudentModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentModel record);

    int updateByPrimaryKey(StudentModel record);

    List<StudentModel> listByCondition(StudentModel record);

    List<StudentModel> listStudent();


}