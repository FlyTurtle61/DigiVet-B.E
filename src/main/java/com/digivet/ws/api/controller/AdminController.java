package com.digivet.ws.api.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digivet.ws.Shared.dto.AdminDto;
import com.digivet.ws.model.request.AdminRequestModel;
import com.digivet.ws.model.response.AdminRest;
import com.digivet.ws.service.Impl.UserServiceImp;
import com.digivet.ws.service.repositories.AdminService;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
	Logger logger = LogManager.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;
	@PostMapping("/create")
	public AdminRest createAdmin(@RequestBody AdminRequestModel adminRequestModel) {
		AdminRest returnValue = new AdminRest();
		AdminDto storedByService = new AdminDto();
		AdminDto adminDto = new AdminDto();
		
		try {
			BeanUtils.copyProperties(adminRequestModel, adminDto);
			storedByService = this.adminService.createAdmin(adminDto);
			BeanUtils.copyProperties(storedByService, returnValue);
			logger.info("createAdmin controller is invoke");
		} catch (Exception e) {
			logger.error("createAdmin controller is not invoke error code:"+e.getMessage());
		}
		return returnValue;
		
	}
	

	@PostMapping("/findAdmin")
	public Boolean findAdmin(@RequestBody AdminRequestModel adminRequestModel) {
		AdminDto adminDto = new AdminDto();
		Boolean returnValue = null;
		
		try {
			BeanUtils.copyProperties(adminRequestModel, adminDto);
			returnValue = this.adminService.findAdmin(adminDto);
			logger.info("findAdmin controller is invoke");
		} catch (Exception e) {
			logger.error("findAdmin controller is not invoked");
		}
		
		return returnValue;
		
	}
	

}
