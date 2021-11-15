package com.dau.mine;

import com.dau.mine.DTO.DeptListDTO;
import com.dau.mine.Service.OganizationService;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MineApplicationTests {

    @Test
    public JSONArray MakeGroup() throws JSONException {
        OganizationService service = new OganizationService();
        List<DeptListDTO> list = new ArrayList<>();

        if(list.size()==0){
            return new JSONArray();
        }
        String firstStr = list.get(0).getUpdept_cd();
        Map<String, List<DeptListDTO>> grouprMap = new HashMap<>();
        for(DeptListDTO dto: list){
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
        JSONArray data = service.(new JSONArray(),grouprMap,firstStr);
        System.out.println(data.toString());
        return data;
    }

}
