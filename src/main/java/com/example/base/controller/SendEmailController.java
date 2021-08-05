package com.example.base.controller;

import java.util.Properties;
/*import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.base.serviceFacade.BaseServiceFacade;
@Log4j2
public class SendEmailController{
	@SuppressWarnings("unused")
	@Autowired
	private BaseServiceFacade baseSF;
	@Autowired
	/*private Multipart multipart;*/

	public void handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {

		String fileName = "Estimate.jrxml";
		String savePath = "C:\\JSPpj\\KHJ_estimulo_T222\\WebContent\\resources\\iReportForm";

		String host = "smtp.gmail.com";
		final String user = "estimulo65k@gmail.com";
		final String password = "estimulo1234";

		String to = "gudwns4134@naver.com";

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

	/*	Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});*/

		// Compose the message
		/*try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));*/

			// Subject

			switch (1) {
			case 1:

				break;

			default:
				break;
			}
			/*message.setSubject("요청하신 견적서 입니다.");
			multipart = new MimeMultipart();

			// Text
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText("요청하신 견적서 입니다. ");
			multipart.addBodyPart(mbp1);

			// send the message
			if (fileName != null) {
				DataSource source = new FileDataSource(savePath + "\\" + fileName);
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(fileName);
				multipart.addBodyPart(messageBodyPart);
			}
			message.setContent(multipart);
			Transport.send(message);
			log.info("메일 발송 성공!");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return null;*/
	}
}
