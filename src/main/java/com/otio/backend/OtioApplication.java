package com.otio.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.otio.backend.filter.BodyTokenFilter;
import com.otio.backend.filter.ParamTokenFilter;
import com.otio.backend.filter.TokenFilter;
import com.otio.backend.filter.BodyFilter;
import com.otio.backend.service.UserService;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"com.otio.backend.repo"})
public class OtioApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtioApplication.class, args);
	}

	@Bean
	FilterRegistrationBean<BodyTokenFilter> bodyTokenFilter(UserService userService) {
		FilterRegistrationBean<BodyTokenFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new BodyTokenFilter(userService));
		registrationBean.addUrlPatterns("/user/addtimeslot", "/user/removetimeslot",
										"/user/saveactivity", "/user/profilepicture/update");
		registrationBean.setOrder(1);

		return registrationBean;
	}

	@Bean
	FilterRegistrationBean<ParamTokenFilter> paramTokenFilter(UserService userService) {
		FilterRegistrationBean<ParamTokenFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new ParamTokenFilter(userService));
		registrationBean.addUrlPatterns("/activities/*");
		registrationBean.addUrlPatterns("/user/deletesavedactivity");
		registrationBean.setOrder(2);

		return registrationBean;
	}

	@Bean
	FilterRegistrationBean<TokenFilter> loginFilter(UserService userService) {
		FilterRegistrationBean<TokenFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new TokenFilter(userService));
		registrationBean.addUrlPatterns("/user/logout", "/user/deleteaccount", "/user/gettimeslots", "/user/savedactivities",
							 			"/user/profilepicture", "/user/profilepicture/delete");
		registrationBean.addUrlPatterns("/users/*");
		
		registrationBean.setOrder(3);

		return registrationBean;
	}

	@Bean
	FilterRegistrationBean<BodyFilter> requestBodyFilter() {
		FilterRegistrationBean<BodyFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new BodyFilter());
		registrationBean.addUrlPatterns("/user/register", "/user/login");
		registrationBean.setOrder(4);

		return registrationBean;
	}

}
