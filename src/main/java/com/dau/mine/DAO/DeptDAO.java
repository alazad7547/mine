package com.dau.mine.DAO;

import com.dau.mine.DTO.DeptDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DeptDAO {
//    void deptInsert(String dept_cd, String type, String upDept_cd) throws Exception;
    int deptInsert(@Param("dto") DeptDTO dto) throws Exception;
    int deptUpdate(@Param("dto") DeptDTO dto,@Param("dept_cd") String dept_cd) throws Exception;
    int deptDelete(@Param("dept_cd") String dept_cd) throws Exception;
//    List<DeptDTO> users(@Param("dept_nm") String dept_nm) throws Exception;

//    List<DeptDTO> users(@Param("dept_nm") Map<String,Object> map) throws Exception;
    List<DeptDTO> users(@Param("dto") DeptDTO dto) throws Exception;
//    List<DeptDTO> users() throws Exception;
}
