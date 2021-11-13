package com.dau.mine.controller;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestParam;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationControllerTest {

    @Test
    public void TestDept() throws Exception {
        OrganizationController org= new OrganizationController();
        org.dept("경영지원본부","Division","1");
    }

    @Test
    public void getList() throws Exception {
        OrganizationController org= new OrganizationController();
        org.users("경영지원본부");
    }
}