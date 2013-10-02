package se.bhg.photos.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import se.bhg.photos.service.impl.PhpBBUserDetailsServiceImpl;

@Component
public class PhpBB3AuthenticationProvider implements AuthenticationProvider {
	@Autowired
	PhpBBUserDetailsServiceImpl userDetailsService;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        if (userDetailsService == null) {
        	System.out.println("USD null");
        }
        
        User user = (User) userDetailsService.loadUserByUsername(username);
        
        if (PhpBB3PasswordUtils.phpbb_check_hash(password, user.getPassword())) {
        	user.eraseCredentials();
            Authentication auth = new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
            return auth;
        } else {
        	throw new BadCredentialsException("Wrong password!");
        }
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}