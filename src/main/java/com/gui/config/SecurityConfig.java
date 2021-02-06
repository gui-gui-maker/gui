package com.gui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.gui.jwt.CustomAccessDeineHandler;
import com.gui.jwt.UnAuthorizedEntryPoint;
import com.gui.jwt.filter.JwtLoginAuthFilter;
import com.gui.jwt.filter.JwtLogoutSuccessHandler;
import com.gui.jwt.filter.JwtPreAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	// 因为UserDetailsService的实现类实在太多啦，这里设置一下我们要注入的实现类
	@Qualifier("userDetailsServiceImpl")
	private UserDetailsService userDetailsService;
	@Autowired
	@Qualifier("bCryptPasswordEncoder")
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// 加密器
	/*
	 * @Bean public BCryptPasswordEncoder bCryptPasswordEncoder(){ return new
	 * BCryptPasswordEncoder(); }
	 */
	/**
	 * description: 加载userDetailsService，用于从数据库中取用户信息
	 * 
	 * @param auth
	 * @return void
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	/**
	 * description: http细节
	 * 
	 * @param http
	 * @return void
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		// 开启跨域资源共享
		http.cors().and()
				// 关闭csrf
				.csrf().disable()
				// 关闭session
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.httpBasic()
				.authenticationEntryPoint(new UnAuthorizedEntryPoint())//无权限处理
				.and()
				.authorizeRequests()//定义哪些URL需要被保护、哪些不需要被保护
				// 需要角色为ADMIN才能删除该资源
				.antMatchers(HttpMethod.DELETE, "/tasks/**").hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/university/**").hasAnyRole("ADMIN")
				// 测试用资源，需要验证了的用户才能访问
				//.antMatchers("/user/**").hasAnyRole("MANAGER")
				.antMatchers("/login","/index","/pages/**","/error/**","/js/**","/css/**","/images/**","/font/**","/assets/**","/Widget/**","/products/**","/upload/**").permitAll()
				// 其他都放行了
				.anyRequest()// 任何请求
				//.permitAll()// 放行
				.authenticated()//登陆后可以访问
				.and()
				.exceptionHandling()
				.accessDeniedHandler(new CustomAccessDeineHandler())
				.and()
				.formLogin()
				.loginPage("/login")
				// .failureUrl("/toLogin?error=true")
				.permitAll()
				// .successHandler(new Fx)
				.and()
				.logout()// 默认注销行为为logout
				.logoutSuccessHandler(new JwtLogoutSuccessHandler())
				.and()
				// 添加到过滤链中
				// 先是UsernamePasswordAuthenticationFilter用于login校验
				.addFilter(new JwtLoginAuthFilter(authenticationManager()))
				// 再通过OncePerRequestFilter，对其他请求过滤
				.addFilter(new JwtPreAuthFilter(authenticationManager()));
	}

	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**","/css/**","/img/**","/font/**");
		//web.ignoring().antMatchers("/login");
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

}
