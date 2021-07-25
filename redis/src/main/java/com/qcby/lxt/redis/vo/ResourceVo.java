package com.qcby.lxt.redis.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @className: UserVo
 * @description:
 * @author: lxt
 * @create: 2021-07-25 10:13
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResourceVo {
    private Long id;
    private String name;
    private String url;
    private String authStr;
    private LocalDateTime localDateTime;
}
