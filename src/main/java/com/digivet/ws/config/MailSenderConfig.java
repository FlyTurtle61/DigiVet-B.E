package com.digivet.ws.config;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.digivet.ws.Shared.dto.MeetingDto;
import com.digivet.ws.Shared.dto.RecommendationDto;
import com.digivet.ws.Shared.dto.VetMeetingCanceledDto;
@Configuration
public class MailSenderConfig {

	@Autowired
	private JavaMailSender mailSender;

	
	public String sendMail(VetMeetingCanceledDto canceledDto) {
		 MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	     MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

	        try {
	            messageHelper.setTo(canceledDto.getUserEmail().toString());
	            messageHelper.setText(canceledDto.getDescription().toString());
	            messageHelper.setSubject("Randevu İptali Hk.");
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            return "Error...";
	        }
	        mailSender.send(mimeMessage);
	        return "Mail Sent!";
	}
	public String vetMeetSendMail(MeetingDto meetingDto) {
		 MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	     MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

	        try {
	            messageHelper.setTo(meetingDto.getVetEmail().toString());
	            messageHelper.setText("Değerli Veterinerimiz:"
	            +"\n"+meetingDto.getUserFirstName().toString()+" "+meetingDto.getUserLastName().toString()
	            +" isimli Kullanıcımız sizden "+meetingDto.getMeetingDate().toString()+" tarihli Randevu Oluşturmuştur"
	            +"\n Bilginize....");
	            messageHelper.setSubject("Randevu Hk.");
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            return "Error...";
	        }
	        mailSender.send(mimeMessage);
	        return "Mail Sent!";
	}
	public String userMeetSendMail(MeetingDto meetingDto) {
		 MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	     MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

	        try {
	            messageHelper.setTo(meetingDto.getUserEmail().toString());
	            messageHelper.setText("Değerli Kullanıcımız:"
	            +"\n Veteriner Hekim "+meetingDto.getVetFirstName().toString()+" "+meetingDto.getVetLastName().toString()
	            +"  olan "+meetingDto.getMeetingDate().toString()+" tarihli Randevu Oluşturmuştur"
	            +"\n Bilginize....");
	            messageHelper.setSubject("Randevu Hk.");
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            return "Error...";
	        }
	        mailSender.send(mimeMessage);
	        return "Mail Sent!";
	}
	
	public String RecommendationInProgres(RecommendationDto recommendationDto) {
		 MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	     MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

	        try {
	            messageHelper.setTo(recommendationDto.getReportedMail().toString());
	            messageHelper.setText("Değerli Kullanıcımız:"
	            +"\n "+recommendationDto.getId()+" Numaralı Talep Kaydınız için Çalışma Başlatılmıştır"+"\n iyi günler dileriz");
	            messageHelper.setSubject("Talep Hk.");
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            return "Error...";
	        }
	        mailSender.send(mimeMessage);
	        return "Mail Sent!";
	}
	
	public String RecommendationComplete(RecommendationDto recommendationDto) {
		 MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	     MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

	        try {
	            messageHelper.setTo(recommendationDto.getReportedMail().toString());
	            messageHelper.setText("Değerli Kullanıcımız:"
	            +"\n "+recommendationDto.getId()+" Numaralı Talep Kaydınız Çözülmüştür "+"\n"+recommendationDto.getResponse()+"\n iyi günler dileriz");
	            messageHelper.setSubject("Talep Hk.");
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            return "Error...";
	        }
	        mailSender.send(mimeMessage);
	        return "Mail Sent!";
	}
	public String RecommendedCreateSendMail(RecommendationDto recommendationDto) {
		 MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	     MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

	        try {
	            messageHelper.setTo(recommendationDto.getReportedMail().toString());
	            messageHelper.setText("Değerli Kullanıcımız:"
	            +"\n "+recommendationDto.getId()+" Numaralı Talep Kaydınız Başarılı Bir şekilde Oluşturulmuştur en kısa sürede size yapılacaktır");
	            messageHelper.setSubject("Talep Hk.");
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            return "Error...";
	        }
	        mailSender.send(mimeMessage);
	        return "Mail Sent!";
	}
	
}
