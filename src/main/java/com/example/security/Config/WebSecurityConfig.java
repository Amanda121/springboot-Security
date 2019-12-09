package com.example.security.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
/*    @Autowired
    private DataSource dataSource;*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
               /* .rememberMe()
                .userDetailsService(myUserDetailServiceImpl) // 设置userDetailsService
                .tokenRepository(persistentTokenRepository()) // 设置数据访问层
                .tokenValiditySeconds(60 * 60) // 记住我的时间(秒)
                .and()*/

                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                //访问“/admin/**”必须具备ADMIN身份

                .antMatchers("/user/**")
                .access("hasAnyRole('USER')")
                //访问“/user/**”必须具备ADMIN或DBA身份

                .antMatchers("/db/**")
                .access("hasRole('ADMIN') and hasRole('DBA') ")
                //访问“/db/**”必须具备ADMIN和DBA身份

                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/index")
                .permitAll()
                .and()
                .csrf()
                .disable();
    }

    @Autowired
    //配置用户
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("123").roles("ADMIN","USER")
                .and()
                .withUser("user").password("123").roles("USER")
                .and()
                .withUser("root").password("123").roles("DBA","ADMIN");
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
