package com.portfolio.sso;

import com.portfolio.sso.models.Blog;
import com.portfolio.sso.models.ERole;
import com.portfolio.sso.models.Role;
import com.portfolio.sso.repository.RoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SsoApplication {

	@Autowired
	static RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SsoApplication.class, args);
//		roleRepository.save(new Role(ERole.ROLE_ADMIN));
//		roleRepository.save(new Role(ERole.ROLE_MODERATOR));
//		roleRepository.save(new Role(ERole.ROLE_USER));
	}
}
