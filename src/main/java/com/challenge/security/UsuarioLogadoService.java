package com.challenge.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UsuarioLogadoService {

	public String getUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String email;    

		if (principal instanceof UserDetails) {
		    email = ((UserDetails)principal).getUsername();
		    System.out.println(email);
		} else {
		    email = principal.toString();
		}
		return email;
	}
}