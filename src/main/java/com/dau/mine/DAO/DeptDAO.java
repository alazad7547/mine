package com.dau.mine.DAO;

import com.dau.mine.DTO.DeptDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DeptDAO {
    int DeptInsert(@Param("dto") DeptDTO dto) throws Exception;
    int DeptUpdate(@Param("dto") DeptDTO dto,@Param("dept_cd") String dept_cd) throws Exception;
    int DeptDelete(@Param("dept_cd") String dept_cd) throws Exception;
    List<DeptDTO> users(@Param("dto") DeptDTO dto) throws Exception;
}
