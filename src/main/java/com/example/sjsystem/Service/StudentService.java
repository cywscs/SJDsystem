package com.example.sjsystem.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sjsystem.pojo.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public interface StudentService extends IService<Student> {
    List<Student> getStudents();


    IPage<Student> getStudentByOr(Page<Student> page, String studentName);

    List<Student> updateScode(List<Integer> scodes);
}
