package kr.ac.dgu.base.cmm.security;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * @Class Name : dguAuthenticationProvider.java
 * @Description : 사용자인증 기능을 제공
 * @author DGU
 * @시스템 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 *
 *            <pre>
 * ------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------
 *   수정일             수정자         수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 7.		DGU         신규생성
 * </pre>
 */ 
@Component
public class DguAuthenticationProvider implements AuthenticationProvider {

    /**
     * Spring Security 사용자인증 처리
     */
    @SuppressWarnings("unchecked")
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String userName = (String) auth.getPrincipal();
        String userPassword = (String) auth.getCredentials();

        Collection<? extends GrantedAuthority> roles = null;
        return new UsernamePasswordAuthenticationToken(userName, userPassword, roles);
    }

    /**
     * 인증토큰 지원여부
     */
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
