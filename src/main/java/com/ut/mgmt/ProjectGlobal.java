package com.ut.mgmt;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

public class ProjectGlobal {

	public static final Logger LOG = Logger.getLogger("AssestManagementController");
	public static final String authenticate_QUERY="select * from auth where username=? and password=? and isalive=true and isloggedin=false";
}
