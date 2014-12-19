package com.greenhi.common.mail;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * 메일 서비스
 * @since 1.5
 */
public class MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);
	/**
	 * spring JavaMailSender
	 */
	private JavaMailSender mailSender;
	/**
	 * template engine
	 */
    private VelocityEngine velocityEngine;
    /**
     * 보내는 메일 정보
     */
    private String from;

	public MailService() {
	}
	
	public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

	public void setFrom(String from) {
		this.from = from;
	}

	/**
     * 이메일 전송
     * @param emal 이메일
     * @param user_nm 이름
     * @param subject 메일제목
     * @param contents 메일 내용
     * @param template 메일template
	 * @throws UnsupportedEncodingException 
     */
	public void send( final String currentDate, final String emal, final String userNm, final String subject, final String bordSj, final String contents, final String template ) {
        new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					MimeMessagePreparator preparator = new MimeMessagePreparator() {
			            public void prepare(MimeMessage mimeMessage) throws Exception {
			                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
			                message.setFrom(from);
			                message.setTo(new InternetAddress(emal, userNm));
			                message.setSubject(subject);
			                Map<String, Object> model = new HashMap<String, Object>();
			                model.put("currentDate", currentDate);
			                model.put("userNm", userNm);
			                model.put("bordSj", bordSj.replace("Re:", ""));
			                model.put("contents", contents);
			                String text = VelocityEngineUtils.mergeTemplateIntoString(
			                        velocityEngine, template, "UTF-8", model);
			                message.setText(text, true);
			                
			                logger.info("mailFrom:{} -> mailTo:{}", from, emal);
			            }
			        };
					mailSender.send(preparator);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}).start();
	}
}
