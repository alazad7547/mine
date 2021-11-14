package com.dau.mine.controller;

import com.dau.mine.DAO.DeptDAO;
import com.dau.mine.DTO.DeptDTO;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RestController
@NoArgsConstructor
@RequestMapping("/org")
@MapperScan(basePackages="com.dau.mine.DAO")//탐색할 패키시 설정
public class OrganizationController {
    private static final Logger log = LoggerFactory.getLogger(OrganizationController.class);
    @Autowired
    private DeptDAO deptDAO;

    @GetMapping("/dept")
    public void deptInsert(
            @RequestParam(value="dept_nm",defaultValue = "")String dept_nm,
            @RequestParam(value="type")String type,
            @RequestParam(value="updept_cd",defaultValue = "")String updept_cd
    ) throws Exception {
        String[] typeArr = {"Company","Division","Department"};
        try {
            DeptDTO dto = new DeptDTO();
            dto.setUpdept_cd(updept_cd);
            dto.setDept_nm(dept_nm);
            dto.setType(type);
            System.out.println(dto.toString());
            int bool = deptDAO.deptInsert(dto);
            System.out.println(bool);
        }catch (SQLException e){
            System.out.println("OrganizationController.deptInsert");
            throw e;
        }
    }

    @RequestMapping(value = "/dept/{dept_id}",method = RequestMethod.PUT)
    public void deptUpdate(
        @RequestBody DeptDTO dto,
        @PathVariable("dept_id") String dept_cd
    ) throws Exception {
        try {
            int bool = deptDAO.deptUpdate(dto,dept_cd);
            //사원수정
            System.out.println(bool);
        }catch (SQLException e){
            System.out.println("OrganizationController.deptUpdate");
            throw e;
        }
    }

    @RequestMapping(value = "/dept/{dept_id}",method = RequestMethod.DELETE)
    public void deptDelete(
            @PathVariable("dept_id") String dept_cd
    ) throws Exception {
        try {
            int bool = deptDAO.deptDelete(dept_cd);
            //사원삭제
            System.out.println(bool);
        }catch (SQLException e){
            System.out.println("OrganizationController.deptDelete");
            throw e;
        }
    }

    @GetMapping(value = "/users")
    public List<DeptDTO> users(
            @RequestParam(value="dept_nm",defaultValue = "")String dept_nm,
            @RequestParam(value="type")String type //드롭다운 버튼등으로 데이터 통제
//            @RequestParam(value="upDept_cd")String upDept_cd //다이얼로그등으로 상위부서 선택 데이터 통제
    ) throws Exception {
        try {
            System.out.println(dept_nm);
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("dept_nm",dept_nm);
            param.put("type",type);
            DeptDTO dto = new DeptDTO();
            dto.setDept_nm("ABC회사");
            dto.setType("Company");
            final List<DeptDTO> deptList = deptDAO.users(dto);
//            final List<DeptDTO> deptList = deptDAO.users();
            return deptList;
        }catch (SQLException e){
            System.out.println("OrganizationController.dept");
            throw e;
        }
    }
}
