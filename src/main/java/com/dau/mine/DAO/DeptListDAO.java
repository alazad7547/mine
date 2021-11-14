package com.dau.mine.DAO;

import com.dau.mine.DTO.DeptListDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DeptListDAO {
    List<DeptListDTO> DeptOnlyList(@Param("dept_nm") String dept_nm, @Param("searchType") String searchType, @Param("searchKeyword") String searchKeyword) throws Exception;
    List<DeptListDTO> DeptList(@Param("dept_nm") String dept_nm, @Param("searchType") String searchType, @Param("searchKeyword") String searchKeyword) throws Exception;
}
