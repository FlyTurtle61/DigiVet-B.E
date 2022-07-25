package com.digivet.ws.service.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.introspector.BeanAccess;

import com.digivet.ws.Shared.dto.AdminDto;
import com.digivet.ws.entities.Admin;
import com.digivet.ws.entities.Users;
import com.digivet.ws.repositories.AdminRepository;
import com.digivet.ws.service.repositories.AdminService;
@Service
public class AdminServiceImpl implements AdminService {

	Logger logger = LogManager.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public AdminDto createAdmin(AdminDto adminDto) {
		AdminDto returnValue = new AdminDto();
		Admin storedByData = new Admin();
		Admin adminEntity = new Admin();
		try {
			BeanUtils.copyProperties(adminDto, adminEntity);
			String Password = adminEntity.getPassword();
			String jwt = this.bCryptPasswordEncoder.encode(Password);
			adminEntity.setPassword(jwt);
			storedByData = this.adminRepository.save(adminEntity);
			BeanUtils.copyProperties(storedByData, returnValue);
			logger.debug("createAdmin service is invoke");
		}catch(Exception ex) {
			logger.error("createAdmin service is not invoke");
		}
		
		return returnValue;
	}

	@Override
	public Boolean findAdmin(AdminDto adminDto) {

		boolean returnValue = true;
		Admin adminEntity = new Admin();
		try {
			logger.info("findAdmin service is invoke");

			BeanUtils.copyProperties(adminDto, adminEntity);
			String email = adminEntity.getAdminEmail();
			String password = adminEntity.getPassword();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
						
			if (encoder.matches(password,this.adminRepository.findByPassword(email)) == true)
			{
				
				returnValue = true;
			}
			
			else {
				returnValue = false;
			}
		} catch (Exception ex) {
			logger.error("findAdmin service is not invoke error message:" + ex.getMessage());
			returnValue = false;

		}
		return returnValue;
	}

}
