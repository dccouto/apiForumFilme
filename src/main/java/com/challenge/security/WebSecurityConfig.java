package com.challenge.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private LoginService loginService;
	
	
	//Configuração do esquema de autenticação
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests()
			//permite acesso apenas a página raiz do projeto
			.antMatchers(HttpMethod.GET,"/").permitAll()
			//Para todas as demais url precisam de autenticação
			.anyRequest().authenticated()
			.and()
			.formLogin().permitAll()
			.and()
			//Para encerrar a sessão quando entrar na página /logout do spring security
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.and()
			.httpBasic();
	}

	
	//Método onde irá ficar a autorização
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST, "/api/usuario/cadastrar");
        web.ignoring().antMatchers(HttpMethod.GET, "/h2-console/**"); //liberando o acesso a interface do banco de dados em memória
        web.ignoring().antMatchers(HttpMethod.POST, "/h2-console/**"); //liberando o acesso a interface do banco de dados em memória
        web.ignoring().antMatchers(HttpMethod.GET, "/swagger-ui.html/**");
        web.ignoring().antMatchers(HttpMethod.POST, "/swagger-ui.html/**");
    }
}
