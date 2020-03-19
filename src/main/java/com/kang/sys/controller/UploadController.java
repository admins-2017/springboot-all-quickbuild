package com.kang.sys.controller;



import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kang.imploded.json.JSONResult;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.sys.entity.UserDetails;
import com.kang.sys.service.FileService;
import com.kang.sys.service.IUserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "图片上传接口")
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
        List<String> list = new ArrayList<>();
        for (MultipartFile upFile:files) {
            String fileName = upFile.getOriginalFilename();
            File file = new File(url + fileName);

            try{
                //将MulitpartFile文件转化为file文件格式
                upFile.transferTo(file);
                list.add(fileService.uploadFile(file));

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return JSONResult.ok(list);
    }


    @PostMapping(value = "/uploadUserAvatar")
    @ApiOperation(value = "单个图片上传到七牛云")
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


