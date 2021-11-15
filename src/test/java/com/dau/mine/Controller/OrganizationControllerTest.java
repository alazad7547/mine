package com.dau.mine.Controller;

import com.dau.mine.DAO.DeptDAO;
import com.dau.mine.DTO.DeptDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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