package se.bhg.photos.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PhpBBUserDetailsServiceImpl implements UserDetailsService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSourceImage(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        String phpbbPasswordHash = jdbcTemplate.queryForObject("select user_password from phpbb_users where username_clean = '" + username.toLowerCase() + "'", String.class);
        Collection<? extends GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(Arrays.asList(new GrantedAuthority[] { new SimpleGrantedAuthority("ROLE_USER") }));
        UserDetails user = new User(username, phpbbPasswordHash, true, true, true, true, authorities);
        return user;
    }
}
