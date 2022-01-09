package com.lxt.demo.oss.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@Component
public class OssQiNiuHelper {

    @Value("${oss.qiniu.bucketName}")
    private String bucketName ;
    @Value("${oss.qiniu.domain}")
    private String fileDomain;

    @Autowired
    private Configuration configuration;
    @Autowired
    private UploadManager uploadManager;
    @Autowired
    private BucketManager bucketManager;


    // 密钥配置
    @Autowired
    private Auth auth;
    @Autowired
    private Gson gson;


    //简单上传模式的凭证
    public String getUpToken() {
        return auth.uploadToken(bucketName);
    }
    //覆盖上传模式的凭证
    public String getUpToken(String fileKey) {
        return auth.uploadToken(bucketName, fileKey);
    }

    /**
     * 上传二进制数据
     * @param data
     * @param fileKey
     * @return
     * @throws IOException
     */
    public DefaultPutRet upload(byte[] data, String fileKey) throws IOException {
        Response res = uploadManager.put(data, fileKey, getUpToken(fileKey));
        // 解析上传成功的结果
        DefaultPutRet putRet = gson.fromJson(res.bodyString(), DefaultPutRet.class);
        System.out.println(putRet.key);
        System.out.println(putRet.hash);
        return  putRet;
    }

    /**
     * 上传输入流
     * @param inputStream
     * @param fileKey
     * @return
     * @throws IOException
     */
    public DefaultPutRet upload(InputStream inputStream, String fileKey) throws IOException {
        Response res = uploadManager .put(inputStream, fileKey, getUpToken(fileKey),null,null);
        // 解析上传成功的结果
        DefaultPutRet putRet = gson.fromJson(res.bodyString(), DefaultPutRet.class);
        System.out.println(putRet.key);
        System.out.println(putRet.hash);
        return putRet ;

    }

    /**
     * 删除文件
     * @param fileKey
     * @return
     * @throws QiniuException
     */
    public boolean delete(String fileKey) throws QiniuException {
        Response response = bucketManager.delete(bucketName, fileKey);
        return response.statusCode == 200 ? true:false;
    }

    /**
     * 获取公共空间文件
     * @param fileKey
     * @return
     */
    public String getFile(String fileKey) throws Exception{
        String encodedFileName = URLEncoder.encode(fileKey, "utf-8").replace("+", "%20");
        String url = String.format("%s/%s", fileDomain, encodedFileName);
        return url;
    }


    /**
     * 获取私有空间文件
     * @param fileKey
     * @return
     */
    public String getPrivateFile(String fileKey) throws Exception{
        String encodedFileName = URLEncoder.encode(fileKey, "utf-8").replace("+", "%20");
        String publicUrl = String.format("%s/%s", "http://"+fileDomain, encodedFileName);
        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
        String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
        return finalUrl;
    }



}
