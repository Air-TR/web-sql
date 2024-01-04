package com.huibo.websql.controller;

import com.huibo.websql.exception.BusinessException;
import com.huibo.websql.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: TR
 * @Date: 2023/4/13
 */
@Api(tags = "SQL")
@RestController
public class SqlController {

    @Resource
    JdbcTemplate jdbcTemplate;


    @ApiOperation(value = "执行 SQL 查询")
    @GetMapping("/query/{sql}")
    public Result querySql(@PathVariable String sql) {
        if (!sql.toLowerCase().startsWith("select")) {
            throw new BusinessException("查询语句格式错误");
        }
        return Result.success(jdbcTemplate.queryForList(sql));
    }

    @ApiOperation(value = "执行 SQL 删除")
    @GetMapping("/delete/{sql}")
    public Result deleteSql(@PathVariable String sql) {
        if (!sql.toLowerCase().startsWith("delete")) {
            throw new BusinessException("删除语句格式错误");
        }
        jdbcTemplate.execute(sql);
        return Result.success("删除成功");
    }

    @ApiOperation(value = "执行 SQL 新增、更新")
    @GetMapping("/save/{sql}")
    public Result updateSql(@PathVariable String sql) {
        if (!sql.toLowerCase().startsWith("insert") && !sql.toLowerCase().startsWith("update")) {
            throw new BusinessException("新增、更新语句格式错误");
        }
        return Result.success(jdbcTemplate.update(sql));
    }

}
