package com.kang.imploded.email.entity;

import lombok.Data;

import java.io.File;

/**
 * @author kang
 * @date 2019-11-26
 */
@Data
public class MailEntity {

    /**
     * 收件人邮箱地址
     **/
    private String emailAddress;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件
     */
    private File file;

}
