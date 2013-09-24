package se.bhg.photos.service.impl;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class PhpBBUserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SimpleJdbcCall readUserData;
		
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		SqlParameterSource in = new MapSqlParameterSource()
		.addValue("username_clean", username.toLowerCase());
		Map out = readUserData.execute(in);
		String username = out.get("username");
		String password = out.get("password");
		UserDetails user = new User(out.get("username"));
		
        
		return null;
	}

}
