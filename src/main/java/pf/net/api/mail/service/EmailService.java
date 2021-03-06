package pf.net.api.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pf.net.api.mail.model.Email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void send(Email eParams) {

        if (eParams.isHtml()) {
            try {
                sendHtmlMail(eParams);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            try {
                sendPlainTextMail(eParams);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendHtmlMail(Email eParams) throws MessagingException {

        boolean isHtml = true;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(eParams.getTo().toArray(new String[eParams.getTo().size()]));
        helper.setReplyTo(eParams.getFrom());
        helper.setFrom(eParams.getFrom());
        helper.setSubject(eParams.getSubject());
        helper.setText(eParams.getMessage(), isHtml);

        if (eParams.getCc().isEmpty()) {
            helper.setCc(eParams.getCc().toArray(new String[eParams.getCc().size()]));
        }

        mailSender.send(message);
    }

    private void sendPlainTextMail(Email eParams) throws MessagingException {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        eParams.getTo().toArray(new String[eParams.getTo().size()]);
        mailMessage.setTo(eParams.getTo().toArray(new String[eParams.getTo().size()]));
        mailMessage.setReplyTo(eParams.getFrom());
        mailMessage.setFrom(eParams.getFrom());
        mailMessage.setSubject(eParams.getSubject());
        mailMessage.setText(eParams.getMessage());

        if (eParams.getCc().isEmpty()) {
            mailMessage.setCc(eParams.getCc().toArray(new String[eParams.getCc().size()]));
        }

        mailSender.send(mailMessage);
    }
}
