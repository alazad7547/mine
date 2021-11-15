package com.dau.mine.Service;

import com.dau.mine.DTO.DeptListDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganizationService {
    public List<DeptListDTO> MakeGroupData(List<DeptListDTO> deptList) throws Exception{
        String firstStr ="";
        if(deptList.get(0).getUpdept_cd() != null){
            firstStr = deptList.get(0).getUpdept_cd();
        }
        else{
            firstStr = "0";
       }
       Map<String, List<DeptListDTO>> grouprMap = new HashMap<>();
       for(DeptListDTO dto: deptList){
            String updept_cd = dto.getUpdept_cd();
            if(updept_cd == null){
                updept_cd = "0";
            }
            List<DeptListDTO> newList;
            if(grouprMap.get(updept_cd) == null){
                newList = new ArrayList<>();
            }
            else{
                newList = grouprMap.get(updept_cd);
            }
            newList.add(dto);
            grouprMap.put(updept_cd,newList);
       }

       List<DeptListDTO> resultData =MakeChildren(grouprMap,firstStr);
       return resultData;
    }
    //children 생성하는 재귀함수
    public List<DeptListDTO> MakeChildren(Map<String, List<DeptListDTO>> grouprMap, String nextStr){
        List<DeptListDTO> list = grouprMap.get(nextStr);
        List<DeptListDTO> dataList = new ArrayList<>();
        for(DeptListDTO dto : list){
            if(grouprMap.get(dto.getCode()) != null){
                dto.setChildren(MakeChildren(grouprMap,dto.getCode()));
            }
            dataList.add(dto);
        }
        return dataList;
    }
}
