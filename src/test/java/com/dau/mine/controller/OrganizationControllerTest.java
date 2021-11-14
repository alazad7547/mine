package com.dau.mine.controller;

import com.dau.mine.DAO.DeptDAO;
import com.dau.mine.DTO.DeptDTO;
import com.dau.mine.DTO.DeptListDTO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationControllerTest {
    @Autowired
    private DeptDAO deptDAO;

    @Test
    public void getList() throws Exception {
        OrganizationController org= new OrganizationController();
        org.users("경영지원본부", "test");
    }

    @Test
    public void TestDept() throws Exception {
        OrganizationController org= new OrganizationController();
        DeptDTO dto = new DeptDTO();
        dto.setDept_nm("경영지원본부");
        dto.setType("Division");
        dto.setUpdept_cd("1");
//        org.deptInsert();
    }



}