package org.myproject.config;

import lombok.RequiredArgsConstructor;
import org.myproject.entity.HeadMonkEntity;
import org.myproject.entity.HelperEntity;
import org.myproject.entity.MemberEntity;
import org.myproject.repository.HeadMonkRepository;
import org.myproject.repository.HelperRepository;
import org.myproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final HeadMonkRepository headMonkRepository;
    private final MemberRepository memberRepository;
    private final HelperRepository helperRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        HeadMonkEntity headMonk = headMonkRepository.findByPhoneNumber(phoneNumber);
        if (headMonk != null) {
            return new User(headMonk.getPhoneNumber(), headMonk.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_HEADMONK")));
        }
        MemberEntity member = memberRepository.findByPhoneNumber(phoneNumber);
        if (member != null) {
            return new User(member.getPhoneNumber(), member.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_MEMBER")));
        }
        HelperEntity helper = helperRepository.findByPhoneNumber(phoneNumber);
        if (helper != null) {
            return new User(helper.getPhoneNumber(), helper.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_HELPER")));
        }
        throw new UsernameNotFoundException("User not found with phone number: " + phoneNumber);
    }
}
