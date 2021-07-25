package com.qcby.lxt.redis.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: UserVo
 * @description:
 * @author: lxt
 * @create: 2021-07-25 10:13
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserVo1 {

    private Long id;
    private String name1;
    private LocalDateTime createTime;
    private List<ResourceVo> resourceVoList;


    public List<String> getAuthStr(){
        if(CollectionUtils.isEmpty(resourceVoList)){
            return Collections.EMPTY_LIST;
        }
        return resourceVoList.stream().map(r->r.getAuthStr()).collect(Collectors.toList());
    }


    public boolean checked(){
        return true;
    }


}
