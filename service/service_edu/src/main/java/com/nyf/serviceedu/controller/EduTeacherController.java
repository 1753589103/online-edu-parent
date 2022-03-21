package com.nyf.serviceedu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nyf.serviceedu.entity.EduTeacher;
import com.nyf.serviceedu.entity.vo.TeacherQuery;
import com.nyf.serviceedu.service.EduTeacherService;
import com.nyf.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author NiuYeFan
 * @since 2022-03-16
 */
@Api("讲师管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("所有讲师")
    @GetMapping("findAll")
    public R getAll(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("/deleteById/{id}")
    public R remoceTeacher(@ApiParam(name = "id",value = "讲师id" ,required = true) @PathVariable String id){
        if (eduTeacherService.removeById(id)){
            return R.ok();
        }
        return R.error();
    }

    /**
     * current 当前页
     * limit 每页记录数
     * @return
     */
    @ApiOperation("分页讲师")
    @GetMapping("pageTeacher/{page}/{limit}")
    public R pageListTeacher(@ApiParam(name = "page", value = "当前页码", required = true)
                                 @PathVariable Long page,
                             @ApiParam(name = "limit", value = "每页记录数", required = true)
                                 @PathVariable Long limit){
        Page<EduTeacher> pageParam = new Page<>(page,limit);

        eduTeacherService.page(pageParam,null);
        //总记录数
        long total = pageParam.getTotal();
        //数据list集合
        List<EduTeacher> records = pageParam.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "多条件分页查询讲师列表")
    @PostMapping("/pageTeacherCondition/{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            @RequestBody TeacherQuery teacherQuery){
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        eduTeacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("/save")
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){ boolean res = eduTeacherService.save(teacher);
        return res?R.ok():R.error();
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("/getById/{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("item", teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PostMapping("/updateById")
    public R updateById(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){
        boolean res = eduTeacherService.updateById(teacher);
        return res?R.ok():R.error();
    }

}

