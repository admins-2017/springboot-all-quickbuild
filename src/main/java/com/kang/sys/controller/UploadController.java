package com.kang.sys.controller;



import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kang.imploded.json.JSONResult;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.sys.entity.UserDetails;
import com.kang.sys.service.FileService;
import com.kang.sys.service.IUserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/24 12:24
 */
@RestController
@RequestMapping("/upload")
@Api(value = "上传图片controller",tags = "上传对应操作")
@Slf4j
public class UploadController {


    @Autowired
    FileService fileService;
    @Autowired
    private IUserDetailsService detailsService;

    @Value("${baseUploadUrl}")
    private String url;
    @Value("${qiniu.path}")
    private String path;

    @PostMapping(value = "/uploadImg")
    @ApiOperation(value = "单个图片上传到七牛云")
    public JSONResult uploadImg(@RequestParam(value = "file")MultipartFile upFile) throws IOException {
        String fileName = upFile.getOriginalFilename();
        File file = new File(url + fileName);
        String result="";
        try{
            //将MulitpartFile文件转化为file文件格式
            upFile.transferTo(file);
             result = fileService.uploadFile(file);

        }catch (Exception e){
            e.printStackTrace();
        }
        return JSONResult.ok(result);
    }


    @PostMapping(value = "/uploadImgList")
    @ApiOperation(value = "多个图片上传到七牛云")
    public JSONResult uploadImgList(@RequestParam("files") MultipartFile[] files) throws IOException {
        log.info("长度，{}",files.length);

        List<String> list = new ArrayList<>();
        for (MultipartFile upFile:files) {
            String fileName = upFile.getOriginalFilename();
            File file = new File(url + fileName);
            String url="";
            try{
                //将MulitpartFile文件转化为file文件格式
                upFile.transferTo(file);
                String result = fileService.uploadFile(file);
                url="http://"+path+'/'+result;
                list.add(url);

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return JSONResult.ok(list);
    }


    @PostMapping(value = "/uploadUserAvatar")
    @ApiOperation(value = "用户头像上传到七牛云")
    public JSONResult uploadUserAvatar(@RequestParam(value = "file")MultipartFile upFile) throws IOException {
        String fileName = upFile.getOriginalFilename();
        File file = new File(url + fileName);
        String url="";
        try{
            //将MulitpartFile文件转化为file文件格式
            upFile.transferTo(file);
            String result = fileService.uploadFile(file);
            url="http://"+path+'/'+result;
            detailsService.update(new UpdateWrapper<UserDetails>().set("user_details_url",url).eq("user_id", SecurityUntil.getUserId()));
            return JSONResult.ok(url);
        }catch (Exception e){
            return JSONResult.errorException(e.getMessage());
        }

    }
}


