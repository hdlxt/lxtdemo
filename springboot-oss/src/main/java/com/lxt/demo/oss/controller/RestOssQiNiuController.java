package com.lxt.demo.oss.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxt.demo.oss.qiniu.OssQiNiuHelper;
import com.lxt.demo.oss.web.ResultJson;
import com.qiniu.storage.model.DefaultPutRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("qiniu")
public class RestOssQiNiuController {
    @Autowired
    private OssQiNiuHelper ossQiNiuHelper;

    @Value("${oss.qiniu.domain}")
    private String fileDomain;

    /**
     * 七牛云文件上传
     *
     * @param file 文件
     * @return
     */
    @PostMapping(value = "/upload")
    public ResultJson upload(MultipartFile file) {
        if (file == null) {
            return ResultJson.fail("上传文件不能为空");
        }
        try {
            FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String fileExtend = originalFilename.substring(originalFilename.lastIndexOf("."));
            String yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            //默认不指定key的情况下，以文件内容的hash值作为文件名
            String fileKey = UUID.randomUUID().toString().replace("-", "") + "-" + yyyyMMddHHmmss + fileExtend;
            Map<String, Object> map = new HashMap<>();
            DefaultPutRet uploadInfo = ossQiNiuHelper.upload(fileInputStream, fileKey);
            map.put("fileName", uploadInfo.key);
            map.put("originName", originalFilename);
            map.put("size", file.getSize());
            //七牛云文件私有下载地址（看自己七牛云公开还是私有配置）
            map.put("url", "http://"+fileDomain+"/"+ uploadInfo.key);
            return ResultJson.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultJson.fail(e.getMessage());
        }
    }

    /**
     * 七牛云私有文件下载
     *
     * @param filename 文件名
     * @return
     */
    @GetMapping(value = "/private/file/{filename}")
    public void privateDownload(@PathVariable("filename") String filename, HttpServletResponse response) {
        if (filename.isEmpty()) {
            return;
        }
        try {
            String privateFile = ossQiNiuHelper.getPrivateFile(filename);
            response.sendRedirect(privateFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 七牛云文件下载
     *
     * @param filename 文件名
     * @return
     */
    @RequestMapping(value = "/file/{filename}",method = {RequestMethod.GET})
    public void download(@PathVariable("filename") String filename, HttpServletResponse response) {
        if (filename.isEmpty()) {
            return;
        }
        try {
            String privateFile = ossQiNiuHelper.getFile(filename);
            response.sendRedirect("http://"+privateFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
