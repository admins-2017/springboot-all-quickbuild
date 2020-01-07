package com.kang.sys.controller;

import com.kang.imploded.email.config.MailTemplate;
import com.kang.imploded.email.entity.MailEntity;
import com.kang.imploded.json.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kang
 * @version 1.0
 * @date 2019/11/26 17:04
 */
@RestController
@RequestMapping("mail")
public class EmailController {

    @Autowired
    private MailTemplate mailTemplate;


    @PostMapping("/")
    public JSONResult sendMail(MailEntity mailEntity){
        return JSONResult.ok();
    }

    @PostMapping("/enrollment")
    public JSONResult sendEnrollmentMail(MailEntity mailEntity){
        return JSONResult.ok();
    }
}
