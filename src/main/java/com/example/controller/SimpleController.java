package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @Autowired
    JdbcTemplate jdbctemplate;

    @GetMapping("/all-simple-table")
    public Integer getAllSimpleTable() {
        return jdbctemplate.queryForObject("select count(*) from simple_table", Integer.class);
    }
}
