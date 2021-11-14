package com.dau.mine.controller;

import com.dau.mine.DAO.DeptDAO;
import com.dau.mine.DAO.DeptListDAO;
import com.dau.mine.DAO.MemberDAO;
import com.dau.mine.DTO.DeptDTO;
import com.dau.mine.DTO.DeptListDTO;
import com.dau.mine.DTO.MemberDTO;
import com.dau.mine.Service.OganizationService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
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
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private DeptListDAO deptListDAO;

    @PostMapping("/dept")
    public void DeptInsert(
            @RequestBody DeptDTO dto
    ) throws Exception {
//        String[] typeArr = {"Company","Division","Department"};
        try {
            System.out.println(dto.toString());
            int bool = deptDAO.DeptInsert(dto);
            System.out.println(bool);
        }catch (SQLException e){
            System.out.println("OrganizationController.DeptInsert");
            throw e;
        }
    }

    @PutMapping("/dept/{deptId}")
    public void DeptUpdate(
            @RequestBody DeptDTO dto,
            @PathVariable("deptId") String dept_cd
    ) throws Exception {
        try {
            int bool = deptDAO.DeptUpdate(dto,dept_cd);
            //사원수정
            System.out.println(bool);
        }catch (SQLException e){
            System.out.println("OrganizationController.DeptUpdate");
            throw e;
        }
    }

    @DeleteMapping("/dept/{deptId}")
    public void DeptDelete(
            @PathVariable("deptId") String dept_cd
    ) throws Exception {
        try {
            int bool = deptDAO.DeptDelete(dept_cd);
            System.out.println(bool);
            bool = memberDAO.MemberAllDelete(dept_cd);
            System.out.println(bool);
        }catch (SQLException e){
            System.out.println("OrganizationController.DeptDelete");
            throw e;
        }
    }





    @PostMapping("/member")
    public void MemeberInsert(
            @RequestBody MemberDTO dto
    ) throws Exception {
        try {
            if(!dto.getManager_yn().equals("Y")){
                dto.setManager_yn("N");
            }
            int bool = memberDAO.MemberInsert(dto);

            System.out.println(bool);
        }catch (SQLException e){
            System.out.println("OrganizationController.MemeberInsert");
            throw e;
        }
    }

    @PutMapping("/member/{memberId}")
    public void MemberUpdate(
            @RequestBody MemberDTO dto,
            @PathVariable("memberId") String member_no
    ) throws Exception {
        try {
            int bool = memberDAO.MemberUpdate(dto,member_no);
            System.out.println(bool);
        }catch (SQLException e){
            System.out.println("OrganizationController.MemberUpdate");
            throw e;
        }
    }

    @DeleteMapping("/member/{memberId}")
    public void MemberDelete(
            @PathVariable("memberId") String member_no
    ) throws Exception {
        try {
            //sssion에서
            System.out.println(member_no);
//            int bool = memberDAO.MemberDelete(dto,member_no);
//            System.out.println(bool);
        }catch (Exception e){
            System.out.println("OrganizationController.MemberDelete");
            throw e;
        }
    }

    @GetMapping(value = "/organizations")
      public List<DeptListDTO> Organizations(
//    public JSONArray Organizations(
            @RequestParam(value="deptCode",required = false, defaultValue = "")String dept_nm, //기준부서코드
            @RequestParam(value="deptOnly",required = false, defaultValue = "false")boolean deptOnly,//부서원 포함 여부
            @RequestParam(value="searchType",required = false, defaultValue = "dept")String searchType, //검색대상
            @RequestParam(value="searchKeyword",required = false, defaultValue = "")String searchKeyword //검색어
    ) throws Exception {
        try {

            List<DeptListDTO> deptList = new ArrayList<>();
            if(deptOnly){
                deptList = deptListDAO.DeptOnlyList(dept_nm,searchType,"%"+searchKeyword+"%");
            }
            else{
                deptList = deptListDAO.DeptList(dept_nm,searchType,"%"+searchKeyword+"%");
            }
            OganizationService service = new OganizationService();
            if(deptList.size()==0){
                return deptList;
            }
            List<DeptListDTO> resultData = service.MakeGroupData(deptList);
            return resultData;
        }catch (SQLException e){
            System.out.println("OrganizationController.dept");
            throw e;
        }
    }
}
