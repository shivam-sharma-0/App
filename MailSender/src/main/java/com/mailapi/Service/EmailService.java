package com.mailapi.Service;

import com.mailapi.DTO.EmailDetails;

public interface EmailService {
 
    // Method 1 Simple Mail
    String sendSimpleMail(EmailDetails details);
 
    // Method 2 is For Send mail with attachment
    String sendMailWithAttachment(EmailDetails details);
}
