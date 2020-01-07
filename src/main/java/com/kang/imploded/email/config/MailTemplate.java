package com.kang.imploded.email.config;

import com.kang.imploded.email.entity.MailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author kang
 */
@Component
public class MailTemplate {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    /**
     * 无附件邮件发送
     * @param to 收信人
     * @param subject 邮件主题
     * @param content 邮件内容
     * @return
     */
    public void sendMail(String to,String subject,String content){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom("1067905926@qq.com");
        javaMailSender.send(message);

    }

    /**
     * 带附件邮件发送
     * @return
     */
    public void sendMimeMail(MailEntity mailEntity) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setTo(mailEntity.getEmailAddress());
        mimeMessageHelper.setSubject(mailEntity.getSubject());
        mimeMessageHelper.setText(mailEntity.getContent(),true);
        mimeMessageHelper.setFrom("1067905926@qq.com");

        //上传文件
        mimeMessageHelper.addAttachment(mailEntity.getFileName(),mailEntity.getFile());
        javaMailSender.send(mimeMessage);
    }

    /**
     * html 网页发送
     * 该方法为同步方法
     **/
    public void sendEmailAsSysExceptionHtml(final MailEntity mailEntity) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("1067905926@qq.com");
        mimeMessageHelper.setTo(mailEntity.getEmailAddress());
        mimeMessageHelper.setText("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "\t<head>\n" +
                        "\t\t<meta charset=\"UTF-8\">\n" +
                        "\t\t<title></title>\n" +
                        "\t</head>\n" +
                        "\t<style>\n" +
                        "\t\tbody,\n" +
                        "\t\ttable,\n" +
                        "\t\ttbody,\n" +
                        "\t\ttr {\n" +
                        "\t\t\tbackground-color: aquamarine;\n" +
                        "\t\t\tbackground-size: 100%;\n" +
                        "\t\t}\n" +
                        "\t</style>\n" +
                        "\n" +
                        "\t<body>\n" +
                        "\t\t<table border=\"solid 2 px\" align=\"center\" style=\"text-align: center;\">\n" +
                        "\t\t\t<tbody>\n" +
                        "\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t<td width=\"200px\" bgcolor=\"coral\">时间</td>\n" +
                        "\t\t\t\t\t<td width=\"80%\" bgcolor=\"azure\">" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "</td>\n" +
                        "\t\t\t\t</tr>\n" +
                        "\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t<td width=\"200px\" bgcolor=\"coral\">信息</td>\n" +
                        "\t\t\t\t\t<td width=\"80%\" bgcolor=\"azure\">" + "测试" + "</td>\n" +
                        "\t\t\t\t</tr>\n" +
                        "\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t<td width=\"200px\" bgcolor=\"coral\">堆栈</td>\n" +
                        "\t\t\t\t\t<td width=\"80%\" bgcolor=\"azure\" style=\"text-align: left;\">" + mailEntity.getContent() + "</td>\n" +
                        "\t\t\t\t</tr>\n" +
                        "\t\t\t</tbody>\n" +
                        "\t\t</table>\n" +
                        "\t</body>\n" +
                        "\n" +
                        "</html>"
                , true);

        this.javaMailSender.send(mimeMessage);
    }


}
