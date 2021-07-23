package com.qcby.lxt.vueueditor.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @ClassName: FileUtil
 * @Description: 文件上传工具类
 * @Version 1.0
 **/
@Slf4j
public class FileUtil {

    public final static FileUtil INSTANCE = new FileUtil();

    private FileUtil(){

    }
    /**
     * 文件上传路径前缀
     */
    private String filePrefix;
    /**
     * 本地磁盘目录
     */
    private String uploadLocalPath;
    /**
     * @Title: uploadFile
     * @Description: 单文件上传到本地磁盘
     * @param: multipartFile
     * @return: java.lang.String
     * @throws:
     */
    public   String uploadFile(MultipartFile multipartFile){
        if(multipartFile == null){
            return null;
        }
        //生成文件名称，以免上传相同文件异常
        String fileName = getUploadFileName(multipartFile.getOriginalFilename());
        // 获取当前日期
        String dateDir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 如果是今天第一次上传，则生成日期文件夹
        File destFileDir = new File(uploadLocalPath + File.separator + dateDir);
        // 文件夹不存在时，创建文件夹
        if(!destFileDir.exists()){
            destFileDir.mkdirs();
        }
        try {
            // 获取上传后文件对象
            File destFile = new File(destFileDir.getAbsoluteFile()+ File.separator+fileName);
            // 上传文件到磁盘指定位置
            multipartFile.transferTo(destFile);
            log.info("文件【"+multipartFile.getOriginalFilename()+"】上传成功");
            // /filePrefix/ + 20210626 + / + 20210626093729817  +   _   +   HU5WMH  +   .jpg
            return filePrefix + dateDir+"/"+fileName;
        } catch (IOException e) {
            log.error("文件上传异常："+multipartFile.getOriginalFilename(),e);
            return null;
        }
    }
    /**
     * @Title: getUploadFilePath
     * @Description: 获取上传后的文件相对路径  --数据库存储该路径
     * @param: fileName
     * @return: java.lang.String
     * @throws:
     */
    private  String getUploadFileName(String fileName){
        //20210626093729817  +   _   +   HU5WMH  +   .jpg
        return new StringBuilder()
                .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")))
                .append("_").append(getRandomStrByNum(6))
                .append(fileName.substring(fileName.lastIndexOf(".")))
                .toString();
    }

    private  String CHAR_STR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * @Title: getRandomStrByNum
     * @Description:  获取不同位数的随机字符串
     * @Author: lxt
     * @param: factor
     * @return: java.lang.String
     * @throws:
     */
    private  String getRandomStrByNum(int factor) {
        // 拼接字符串
        StringBuilder sb = new StringBuilder();
        // java随机数对象
        Random random = new Random();
        for (int i = 0; i < factor; i++) {
            int index = random.nextInt(36);
            char c = CHAR_STR.charAt(index);
            sb.append(c);
        }
        return sb.toString();
    }


    public String getFilePrefix() {
        return filePrefix;
    }

    public void setFilePrefix(String filePrefix) {
        this.filePrefix = filePrefix;
    }

    public String getUploadLocalPath() {
        return uploadLocalPath;
    }

    public void setUploadLocalPath(String uploadLocalPath) {
        this.uploadLocalPath = uploadLocalPath;
    }
}
