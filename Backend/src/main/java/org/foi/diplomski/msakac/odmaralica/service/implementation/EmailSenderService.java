package org.foi.diplomski.msakac.odmaralica.service.implementation;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;
import org.foi.diplomski.msakac.odmaralica.model.security.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendActivationEmail(UserGetDTO user, UserToken activationToken) {
        String subject = "Welcome to Odmaralica - Activate Your Account";
        String activationLink = "http://localhost:8069/auth/activate?token=" + activationToken.getToken();
        String emailContent = this.getActivationEmailContent(activationLink);
        this.sendMimeMail(user.getEmail(), subject, emailContent);
    }

    public void sendActivationSuccessEmail(UserGetDTO user) {
        String subject = "Welcome to Odmaralica - Your Account Has Been Activated";
        String emailContent = this.getActivationSuccessEmailContent();
        this.sendMimeMail(user.getEmail(), subject, emailContent);
    }

    private void sendMimeMail(String toEmail, String subject, String body) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom(new InternetAddress("msakac98@gmail.com"));
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
                mimeMessage.setSubject(subject);
                mimeMessage.setContent(body, "text/html; charset=utf-8");
            }
        };
        try {
            this.mailSender.send(preparator);
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private String getActivationEmailContent(String activationLink) {
        return "<!DOCTYPE html>\n" +
                      "<html>\n" +
                      "<head>\n" +
                      "<style>\n" +
                      ".card {\n" +
                      "  width: 400px;\n" +
                      "  margin: 0 auto;\n" +
                      "  padding: 20px;\n" +
                      "  border: 1px solid #ccc;\n" +
                      "  border-radius: 10px;\n" +
                      "  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);\n" +
                      "}\n" +
                      "\n" +
                      "body {\n" +
                      "  font-family: Arial, sans-serif;\n" +
                      "}\n" +
                      "\n" +
                      "h1 {\n" +
                      "  color: #1e6ebf;\n" +
                      "  text-align: center;\n" +
                      "}\n" +
                      "\n" +
                      "p {\n" +
                      "  color: #333;\n" +
                      "  font-size: 16px;\n" +
                      "  text-align: center;\n" +
                      "}\n" +
                      "\n" +
                      "a {\n" +
                      "  background-color: #1e6ebf;\n" +
                      "  color: white !important;\n" +
                      "  padding: 10px 20px;\n" +
                      "  text-decoration: none;\n" +
                      "  border-radius: 5px;\n" +
                      "  display: block;\n" +
                      "  margin: 20px auto;\n" +
                      "  text-align: center;\n" +
                      "  font-size: 20px;\n" +
                      "}\n" +
                      "\n" +
                      "</style>\n" +
                      "</head>\n" +
                      "<body>\n" +
                      "\n" +
                      "<div class=\"card\">\n" +
                      "  <h1>Welcome to Odmaralica!</h1>\n" +
                      "  <p>Thank you for joining our travel community. We're excited to have you on board.</p>\n" +
                      "  <p>To activate your account and start exploring amazing travel experiences, please click the link below:</p>\n" +
                      "  <a href=\"" + activationLink + "\">Activate My Account</a>\n" +
                      "  <p>If you didn't sign up for Odmaralica, please ignore this email.</p>\n" +
                      "  <p>Happy travels,</p>\n" +
                      "  <p>The Odmaralica Team</p>\n" +
                      "</div>\n" +
                      "\n" +
                      "</body>\n" +
                      "</html>";
    }

    private String getActivationSuccessEmailContent() {
        return "<!DOCTYPE html>\n" +
                      "<html>\n" +
                      "<head>\n" +
                      "<style>\n" +
                      ".card {\n" +
                      "  width: 400px;\n" +
                      "  margin: 0 auto;\n" +
                      "  padding: 20px;\n" +
                      "  border: 1px solid #ccc;\n" +
                      "  border-radius: 10px;\n" +
                      "  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);\n" +
                      "}\n" +
                      "\n" +
                      "body {\n" +
                      "  font-family: Arial, sans-serif;\n" +
                      "}\n" +
                      "\n" +
                      "h1 {\n" +
                      "  color: #1e6ebf;\n" +
                      "  text-align: center;\n" +
                      "}\n" +
                      "\n" +
                      "p {\n" +
                      "  color: #333;\n" +
                      "  font-size: 16px;\n" +
                      "  text-align: center;\n" +
                      "}\n" +
                      "\n" +
                      "a {\n" +
                      "  background-color: #1e6ebf;\n" +
                      "  color: white !important;\n" +
                      "  padding: 10px 20px;\n" +
                      "  text-decoration: none;\n" +
                      "  border-radius: 5px;\n" +
                      "  display: block;\n" +
                      "  margin: 20px auto;\n" +
                      "  text-align: center;\n" +
                      "  font-size: 20px;\n" +
                      "}\n" +
                      "\n" +
                      "</style>\n" +
                      "</head>\n" +
                      "<body>\n" +
                      "\n" +
                      "<div class=\"card\">\n" +
                      "  <h1>Congratulations!</h1>\n" +
                      "  <p>Your account has been successfully activated.</p>\n" +
                      "  <p>You are now ready to explore amazing travel experiences with Odmaralica.</p>\n" +
                      "  <a href=\"http://localhost:8069\">Start Exploring</a>\n" +
                      "  <p>Happy travels,</p>\n" +
                      "  <p>The Odmaralica Team</p>\n" +
                      "</div>\n" +
                      "\n" +
                      "</body>\n" +
                      "</html>";
    }

}