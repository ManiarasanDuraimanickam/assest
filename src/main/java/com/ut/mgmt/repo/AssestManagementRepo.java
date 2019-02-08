package com.ut.mgmt.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ut.mgmt.ProjectGlobal;
import com.ut.mgmt.model.UserInfo;

@Repository
public class AssestManagementRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public UserInfo doAuthenticate(String username, String password) {
		ProjectGlobal.LOG.info("validate the user");
		UserInfo userInfo = this.jdbcTemplate.query(ProjectGlobal.authenticate_QUERY,
				new Object[] { username, password }, new ResultSetExtractor<UserInfo>() {

					@Override
					public UserInfo extractData(ResultSet rs) throws SQLException, DataAccessException {
						UserInfo userInfo = null;
						if (rs.next()) {
							userInfo = new UserInfo();
							userInfo.setUserName(rs.getString("username"));
							userInfo.setRole(rs.getInt("role"));
							userInfo.setAlive(rs.getBoolean("isalive"));
							userInfo.setLoggedIn(rs.getBoolean("isloggedin"));
						} else
							return null;
						return userInfo;
					}
				});
		return userInfo;
	}
}
