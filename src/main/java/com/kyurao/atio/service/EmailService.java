package com.kyurao.atio.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public EmailBuilder newEmail(String subject) {
        return new EmailBuilder(subject);
    }

    public class EmailBuilder {
        private final String subject;
        private String sendFrom;
        private String content;
        private Set<String> sendTo = new HashSet<>();
        private Set<Attachment> attachments = new HashSet<>();

        private EmailBuilder(String subject) {
            this.subject = subject;
        }

        public EmailBuilder to(String receiverAddress) {
            this.sendTo.add(receiverAddress);
            return this;
        }

        public EmailBuilder to(Collection<String> receiverAddresses) {
            this.sendTo.addAll(receiverAddresses);
            return this;
        }

        public EmailBuilder from(String sendFrom) {
            this.sendFrom = sendFrom;
            return this;
        }

        public EmailBuilder content(String content) {
            this.content = content;
            return this;
        }

        public EmailBuilder attach(File file, String attachmentName) {
            var attachment = new Attachment(attachmentName, file);
            this.attachments.add(attachment);
            return this;
        }

        public EmailBuilder attach(File file) {
            var attachment = new Attachment(file.getName(), file);
            this.attachments.add(attachment);
            return this;
        }

        public void send() {
            log.info("Send email to: {}", sendTo);
            try {
                var hasAttachment = !attachments.isEmpty();
                var message = mailSender.createMimeMessage();
                var helper = new MimeMessageHelper(message, hasAttachment);
                helper.setSubject(subject);
                helper.setText(content);
                helper.setFrom(isNotBlank(sendFrom) ? sendFrom : from);
                helper.setTo(sendTo.toArray(new String[0]));
                if (hasAttachment) {
                    for (var attachment : attachments) {
                        var src = new FileSystemResource(attachment.getFile());
                        helper.addAttachment(attachment.getName(), src);
                    }
                }
                mailSender.send(message);
            } catch (MessagingException ex) {
                log.warn("Cannot send an email: [{}]", ex.getMessage());
            }
        }

        @Getter
        @AllArgsConstructor
        private class Attachment {
            private String name;
            private File file;
        }
    }
}
