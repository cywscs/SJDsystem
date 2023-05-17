package com.example.sjsystem.Controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sjsystem.Mapper.StudentMapper;
import com.example.sjsystem.Service.StudentService;
import com.example.sjsystem.pojo.Student;
import com.example.sjsystem.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@ResponseBody
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;


    @PostMapping("/postData")
    public Result<JSONArray> postData(@RequestBody JSONObject jsonObject){
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.get("aims").toString());
        JSONArray jsonArray1 = new JSONArray();
        //排序
        List<Integer> list;
        list = JSONObject.parseArray(jsonArray.toJSONString(), Integer.class);
        list.sort(Comparator.naturalOrder());
        jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        System.out.println(jsonArray);


        //实到人数
        int count = 0;
        for(int i = 0; i < jsonArray.size(); i++)
        {
//            1.进行查询
            QueryWrapper<Student> queryWrapper = new QueryWrapper<Student>();
            queryWrapper.eq("Scode", jsonArray.get(i));
            queryWrapper.eq("classs", jsonObject.get("class").toString().substring(1, 8));
            Student student= studentMapper.selectOne(queryWrapper);
//            map.put(student.getScode(),student.getSname());
            String ss = student.getScode()+" "+student.getSname();

            if (!ss.endsWith(" ")){
                jsonArray1.add(ss);
                count += 1;
                Integer time = student.Times + 1;
                student.setTimes(time);
                studentService.saveOrUpdate(student);
            }
        }
        return Result.ok(jsonArray1);
    }
    //查询全部
    @GetMapping("/pagedList")
    public Result<List<Student>> getStudents(){
        List<Student> students= studentService.getStudents();
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.orderByDesc("Times");
        return Result.ok(students);
    }
    //根据id删除用户
    @PostMapping("/delete")
    public Result deleStudent(@RequestBody JSONObject jsonObject){
        studentService.removeById(jsonObject.get("id").toString());
        return Result.ok();
    }
    @PostMapping("/saveOrUpdateStudent")
    public Result saveOrUpdate(@RequestBody Student student){
        studentService.saveOrUpdate(student);
        return Result.ok();
    }
    @GetMapping("/student/{name}")
    public Result<Student> schoolByName(@PathVariable String name) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Sname", name);
        Student student=studentService.getOne(queryWrapper);
        return Result.ok(student);
    }

    @GetMapping("/getStudents/{pageNo}/{pageSize}")
    public Result<IPage<Student>> getStudents(
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            String studentName
    ){
        //分页，带条件查询
    Page<Student> page=new Page<>(pageNo,pageSize);
    //通过服务层
        IPage<Student> pageRs=studentService.getStudentByOr(page,studentName);
        //封装对象
        return Result.ok(pageRs);
    }

}
