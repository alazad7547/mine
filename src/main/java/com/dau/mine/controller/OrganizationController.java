package com.dau.mine.controller;

import com.dau.mine.DAO.DeptDAO;
import com.dau.mine.DTO.DeptDTO;
import org.apache.ibatis.annotations.Param;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/org")
@MapperScan(basePackages="com.dau.mine.DAO")//탐색할 패키시 설정
public class OrganizationController {
    private static final Logger log = LoggerFactory.getLogger(OrganizationController.class);
    @Autowired
    private DeptDAO deptDAO;

    @PostMapping("/dept")
    public void dept(
            @RequestParam(value="dept_nm")String dept_cd,
            @RequestParam(value="type")String type, //드롭다운 버튼등으로 데이터 통제
            @RequestParam(value="upDept_cd")String upDept_cd //다이얼로그등으로 상위부서 선택 데이터 통제
         ) throws Exception {
        String[] typeArr = {"Company","Division","Department"};
        try {
            if(!Arrays.asList(typeArr).contains(type)){
                throw new RuntimeException();
            }
            deptDAO.deptInsert(dept_cd, type, upDept_cd);
        }catch (SQLException e){
            System.out.println("OrganizationController.dept");
            throw e;
        }
    }

    @GetMapping(value = "/users")
    public List<DeptDTO> users(
            @RequestParam(value="dept_nm")String dept_nm
//            @RequestParam(value="type")String type, //드롭다운 버튼등으로 데이터 통제
//            @RequestParam(value="upDept_cd")String upDept_cd //다이얼로그등으로 상위부서 선택 데이터 통제
    ) throws Exception {
        try {
//            if(!Arrays.asList(typeArr).contains(type)){
//                throw new RuntimeException();
//            }
            System.out.println(dept_nm);
            List<String> list = new ArrayList<>();
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("dept_nm",dept_nm);
            final List<DeptDTO> deptList = deptDAO.users(dept_nm);
//            final List<DeptDTO> deptList = deptDAO.users();
            return deptList;
        }catch (SQLException e){
            System.out.println("OrganizationController.dept");
            throw e;
        }
    }
}
