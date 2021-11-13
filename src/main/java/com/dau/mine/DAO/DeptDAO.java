package com.dau.mine.DAO;

import com.dau.mine.DTO.DeptDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DeptDAO {
    void deptInsert(String dept_cd, String type, String upDept_cd) throws Exception;
    void deptDelete() throws Exception;
    void deptmodyfi() throws Exception;
    List<DeptDTO> users(@Param("dept_nm") String dept_nm) throws Exception;
//    List<DeptDTO> users() throws Exception;
}
