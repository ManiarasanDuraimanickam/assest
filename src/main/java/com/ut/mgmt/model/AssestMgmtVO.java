package com.ut.mgmt.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class AssestMgmtVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2616809731618138464L;

	private UserInfo userInfo;
	private String navpage;
	private LinkedList<String> breadcrumb = new LinkedList<String>();

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getNavpage() {
		return navpage;
	}

	public void setNavpage(String navpage) {
		this.navpage = navpage;
	}

	public String addNewLink(String link) {
		this.navpage = link;
		String firstLink = link;
		if (breadcrumb.size() > 3)
			firstLink = breadcrumb.remove();

		breadcrumb.add(link);
		return firstLink;
	}

	public LinkedList<String> getBreadcrumb() {
		return breadcrumb;
	}

}
