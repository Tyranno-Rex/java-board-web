package com.mysite.sbb.user;

import java.net.Authenticator;
import java.util.Optional;
import java.util.Properties;

import lombok.RequiredArgsConstructor;
import com.mysite.sbb.DataNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }

    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
        if (siteUser.isPresent())
            return siteUser.get();
        else
            throw new DataNotFoundException("siteuser not found");
    }
}
