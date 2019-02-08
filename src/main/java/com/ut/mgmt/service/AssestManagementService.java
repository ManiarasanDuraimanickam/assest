package com.ut.mgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ut.mgmt.model.UserInfo;
import com.ut.mgmt.repo.AssestManagementRepo;

@Service
public class AssestManagementService {

	@Autowired
	private AssestManagementRepo assestManagementRepo;

	public UserInfo doAuthenticate(String username, String password) {
		UserInfo userInfo = assestManagementRepo.doAuthenticate(username, password);
		return userInfo;
	}

}
