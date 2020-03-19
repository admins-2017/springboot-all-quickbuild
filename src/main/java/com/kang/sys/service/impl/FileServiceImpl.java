package com.kang.sys.service.impl;

import com.google.gson.Gson;
import com.kang.sys.service.FileService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
/**
 * @author kang
 * @version 1.0
 * @date 2020/2/24 12:23
 */
@Service
public class FileServiceImpl implements FileService {


    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private Auth auth;


    @Value("${qiniu.bucket}")
    private String bucket;


    private StringMap putPolicy;

    @Override
    public String uploadFile(File file) throws QiniuException {
        Response response = this.uploadManager.put(file,null,getUploadToken());
        //解析上传的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(),DefaultPutRet.class);

        String imageName = putRet.hash;
        int retry = 0;
        while(response.needRetry() && retry < 3){
            response = this.uploadManager.put(file,null,getUploadToken());
        }
        return imageName;
    }



    private String getUploadToken(){
        return this.auth.uploadToken(bucket,null,3600,putPolicy);
    }



}

