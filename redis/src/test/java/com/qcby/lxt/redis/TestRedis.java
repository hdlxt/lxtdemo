package com.qcby.lxt.redis;

import com.qcby.lxt.redis.redis.RedisUtil;
import com.qcby.lxt.redis.vo.ResourceVo;
import com.qcby.lxt.redis.vo.UserVo;
import com.qcby.lxt.redis.vo.UserVo1;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: TestRedis
 * @description:
 * @author: lxt
 * @create: 2021-07-25 10:26
 **/
@Slf4j
public class TestRedis extends RedisApplicationTests{

    @Autowired
    private RedisUtil redisUtil;

    static List<ResourceVo> resourceVoList = new ArrayList<>();
    static UserVo userVo = null;

    // 短信验证码的前缀
    public final static  String REDIS_SMS_PREFIX = "sms_";


    @Test
    public void testEx() throws InterruptedException {
        String code = "1234";
        String tel = "1501111";
        // 60s 超时
        redisUtil.setex(REDIS_SMS_PREFIX+tel,code,60);
        while(true){

            Object r = redisUtil.get(REDIS_SMS_PREFIX+tel);
            if(r != null){
                log.info("短信验证码：{}",r.toString());
            }else{
                log.info("短信验证码不存在！");
            }
            // 每200ms打印一次
            Thread.sleep(5000);
        }

    }
    static {
        // 数据初始化
        resourceVoList.add(ResourceVo.builder().id(1L).name("用户管理").url("/user").authStr("/user").build());
        resourceVoList.add(ResourceVo.builder().id(2L).name("角色管理").url("/role").authStr("/role").build());
        resourceVoList.add(ResourceVo.builder().id(3L).name("资源管理").url("/resource").authStr("/resource").build());
        userVo = UserVo.builder()
                .id(1L)
                .name("admin")
                .createTime(LocalDateTime.now())
                .resourceVoList(resourceVoList).build();
    }

    @Test
    public void testRedis(){
        redisUtil.set(userVo.getName(),userVo);
        UserVo1 userVoDb = (UserVo1) redisUtil.get(userVo.getName());
        log.info("userVoDb:{}",userVoDb);
    }
}
