package com.ut.mgmt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ut.mgmt.ProjectGlobal;
import com.ut.mgmt.model.AssestMgmtVO;
import com.ut.mgmt.model.UserInfo;
import com.ut.mgmt.service.AssestManagementService;

@Controller
@SessionAttributes("assestMgmtVO")
public class AssestManagementController {

	@ModelAttribute("assestMgmtVO")
	public AssestMgmtVO assestMgmtVO() {
		return new AssestMgmtVO();
	}

	@Autowired
	private AssestManagementService assestManagementService;

	@GetMapping("/signin")
	public ModelAndView getLogin(@ModelAttribute("assestMgmtVO") AssestMgmtVO assestMgmtVO) {
		ProjectGlobal.LOG.info("User Triggered LoginPage");
		assestMgmtVO.addNewLink("login");
		return new ModelAndView("login");
	}

	@PostMapping("/authenticate")
	public ModelAndView doAuthenticate(@RequestParam("username") String username,
			@RequestParam("password") String password, @ModelAttribute AssestMgmtVO assestMgmtVO) {
		ProjectGlobal.LOG.info("User submitted Login Form");
		UserInfo authResp = assestManagementService.doAuthenticate(username, password);
		assestMgmtVO.setUserInfo(authResp);
		if (null != authResp) {
			assestMgmtVO.addNewLink("dashboard");
			return new ModelAndView("dashboard");
		} else
			return new ModelAndView("login");
	}

	@GetMapping("/logout")
	public ModelAndView doLogout(HttpSession sess) {
		sess.invalidate();
		return new ModelAndView("login");
	}

	@GetMapping("/navigation")
	public ModelAndView doNavigation(@ModelAttribute("assestMgmtVO") AssestMgmtVO assestMgmtVO,
			@RequestParam("menu") String navigationTo) {
		assestMgmtVO.addNewLink(navigationTo);
		return new ModelAndView(navigationTo);
	}

}
