package com.gui.security.bean;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


/**
 * 用户认证信息 上面 UserDetailsService 加载好用户认证信息后会封装认证信息到一个 UserDetails 的实现类。 默认实现是
 * User 类，我们这里没有特殊需要，简单继承即可，复杂需求可以在此基础上进行拓展。 JwtUserDetails.java
 * 
 * @author DELL
 *
 */
public class JwtUserDetails extends User {
	private static final long serialVersionUID = 1L;

	public JwtUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		this(username, password, true, true, true, true, authorities);
	}

	public JwtUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

}
