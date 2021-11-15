package com.dau.mine.Controller;

import com.dau.mine.DAO.DeptDAO;
import com.dau.mine.DAO.DeptListDAO;
import com.dau.mine.DAO.MemberDAO;
import com.dau.mine.DTO.DeptDTO;
import com.dau.mine.DTO.DeptListDTO;
import com.dau.mine.DTO.MemberDTO;
import com.dau.mine.Service.OganizationService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/org")
@MapperScan(basePackages="com.dau.mine.DAO")//탐색할 패키시 설정
public class OrganizationController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DeptDAO deptDAO;
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private DeptListDAO deptListDAO;


    @PostMapping("/dept")
    public void DeptInsert(
            @RequestBody DeptDTO dto //부서 정보
    ) throws Exception {
        try {
            deptDAO.DeptInsert(dto);
        }catch (SQLException e){
            log.info("OrganizationController.DeptInsert");
            throw e;
        }catch (Exception e){
            log.info("OrganizationController.DeptInsert");
            throw e;
        }
    }

    @PutMapping("/dept/{deptId}")
    public void DeptUpdate(
            @RequestBody DeptDTO dto, // 부서 정보
            @PathVariable("deptId") String dept_cd //부서 코드
    ) throws Exception {
        try {
            deptDAO.DeptUpdate(dto,dept_cd);
        }catch (SQLException e){
            log.info("OrganizationController.DeptUpdate");
            throw e;
        }catch (Exception e){
            log.info("OrganizationController.DeptInsert");
            throw e;
        }
    }

    @DeleteMapping("/dept/{deptId}")
    public void DeptDelete(
            @PathVariable("deptId") String dept_cd //부서 코드
    ) throws Exception {
        try {
            deptDAO.DeptDelete(dept_cd);
            memberDAO.MemberAllDelete(dept_cd);
        }catch (SQLException e){
            log.info("OrganizationController.DeptDelete");
            throw e;
        }catch (Exception e){
            log.info("OrganizationController.DeptInsert");
            throw e;
        }
    }

    @PostMapping("/member")
    public void MemeberInsert(
            @RequestBody MemberDTO dto //사원정보
    ) throws Exception {
        try {
            if(!dto.getManager_yn().equals("Y")){
                dto.setManager_yn("N");
            }
            memberDAO.MemberInsert(dto);
        }catch (SQLException e){
            log.info("OrganizationController.MemeberInsert");
            throw e;
        }catch (Exception e){
            log.info("OrganizationController.DeptInsert");
            throw e;
        }
    }

    @PutMapping("/member/{memberId}")
    public void MemberUpdate(
            @RequestBody MemberDTO dto, //사원정보
            @PathVariable("memberId") String member_no //사원코드
    ) throws Exception {
        try {
            memberDAO.MemberUpdate(dto,member_no);
        }catch (SQLException e){
            log.info("OrganizationController.MemberUpdate");
            throw e;
        }catch (Exception e){
            log.info("OrganizationController.DeptInsert");
            throw e;
        }
    }

    @DeleteMapping("/member/{memberId}")
    public void MemberDelete(
            @PathVariable("memberId") String member_no //사원코드
    ) throws Exception {
        try {
            //session에서 dept_cd를 가져와야한다
            if(!member_no.contains(",")){
                throw new HttpMessageNotReadableException("");
            }
            String[] StringArr = member_no.split(",");
            memberDAO.MemberDelete(StringArr[0],StringArr[1]);
            //memberDAO.MemberDelete(dept_cd,member_no);
        }catch (SQLException e){
            log.info("OrganizationController.MemberDelete");
            throw e;
        }catch (Exception e){
            log.info("OrganizationController.DeptInsert");
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
            //부서워포함 여부에 따른 분기
            if(deptOnly){
                deptList = deptListDAO.DeptOnlyList(dept_nm,searchType,"%"+searchKeyword+"%");
            }
            else{
                deptList = deptListDAO.DeptList(dept_nm,searchType,"%"+searchKeyword+"%");
            }
            OganizationService service = new OganizationService();
            if(deptList.size()==0){
                if(searchType.equals("dept")){
                    throw new RuntimeException("일치하는 부서가 없습니다.");
                }
                else{
                    throw new RuntimeException("일치하는 사원이 없습니다.");
                }
            }
            //데이터 계층구조 생성
            List<DeptListDTO> resultData = service.MakeGroupData(deptList);
            return resultData;
        }catch (SQLException e){
            log.info("OrganizationController.Organizations");
            throw e;
        }catch (Exception e){
            log.info("OrganizationController.DeptInsert");
            throw e;
        }
    }
}
