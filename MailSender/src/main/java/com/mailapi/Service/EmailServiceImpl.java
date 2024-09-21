package com.mailapi.Service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mailapi.DTO.EmailDetails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
 
    @Autowired 
    private JavaMailSender javaMailSender;
 

    @Override
    // Method 1
    public String sendSimpleMail(EmailDetails details)
    {
 
        
        try {
 
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(details.getTo());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
        catch (MailException e) {
            return "Error while Sending Mail";
        }
    }
 
    // Method 2
    @Override
    public String sendMailWithAttachment(EmailDetails details)
    {
        MimeMessage mimeMessage
            = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
 
        try {
 
            mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(details.getTo());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                details.getSubject());
 
            FileSystemResource file
                = new FileSystemResource(
                    new File(details.getAttachment()));
 
            mimeMessageHelper.addAttachment(
                file.getFilename(), file);
 
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }
 
        catch (MessagingException e) {
 
            return "Error while sending mail!!!";
        }
    }
}
