package se.bhg.photos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import se.bhg.photos.util.PhpBB3AuthenticationProvider;

@Configuration
@EnableWebSecurity
@Import({ApplicationConfiguration.class})
public class BhgWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	PhpBB3AuthenticationProvider phpBB3AuthenticationProvider;
	
    @Override
    protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    	auth.authenticationProvider(phpBB3AuthenticationProvider);
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
      web
        .ignoring()
           .antMatchers("/assets/**"); // #3
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .authorizeRequests()
          /*.antMatchers("/signup","/about").permitAll()
          .antMatchers("/admin/**").hasRole("ADMIN") */
          .anyRequest().authenticated() // 7
          .and()
      .formLogin()  // #8
          .loginPage("/login") // #9
          .permitAll();    }
}
