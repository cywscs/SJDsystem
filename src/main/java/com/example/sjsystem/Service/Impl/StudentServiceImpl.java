package com.example.sjsystem.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sjsystem.Mapper.StudentMapper;
import com.example.sjsystem.Service.StudentService;
import com.example.sjsystem.pojo.Student;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Override
    public List<Student> getStudents(){
        return baseMapper.selectList(null);
    }


    @Override
    public IPage<Student> getStudentByOr(Page<Student> pageParam,String studentName){
        QueryWrapper queryWrapper=new QueryWrapper();
        if(!StringUtils.isEmpty(studentName)){
            queryWrapper.like("Sname",studentName);
        }

        queryWrapper.orderByDesc("Times");

        Page<Student> page=baseMapper.selectPage(pageParam,queryWrapper);

        return page;

    }

    @Override
    public List<Student> updateScode(List<Integer> scodes){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("Scode",scodes);
        List<Student> students=baseMapper.selectList(queryWrapper);
        for(Student student:students){
            student.Times++;
        }

        return students;
    }
}
