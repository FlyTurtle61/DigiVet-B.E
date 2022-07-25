package com.digivet.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.digivet.ws.Shared.dto.VetMeetingCanceledDto;
import com.digivet.ws.config.MailSenderConfig;
import com.digivet.ws.service.Impl.MeetingCanceledServiceImpl;

@SpringBootApplication
public class WsApplication{

	
	
	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
	}

	
	
	
}
