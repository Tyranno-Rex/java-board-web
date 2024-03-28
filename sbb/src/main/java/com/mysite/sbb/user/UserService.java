package com.mysite.sbb.user;

import java.net.Authenticator;
import java.util.Optional;
import java.util.Properties;

import com.mysite.sbb.user.Email.EmailController;
import com.mysite.sbb.user.Email.GenerateNewPassword;
import lombok.RequiredArgsConstructor;
import com.mysite.sbb.DataNotFoundException;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.MessagingException;

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
    public SiteUser findPassword(String username, String email) throws MessagingException {
        Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
        if (siteUser.isPresent()) {
            SiteUser user = siteUser.get();
            if (user.getEmail().equals(email)) {
                EmailController emailController = new EmailController();
                GenerateNewPassword generateNewPassword = new GenerateNewPassword();
                String newPassword = generateNewPassword.getRandomPassword2(10);
                user.setPassword(passwordEncoder.encode(newPassword));
                this.userRepository.save(user);
                emailController.sendEmail(username, email, newPassword + " is your new password");
            } else {
                throw new DataNotFoundException("email not match");
            }
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
        return siteUser.get();
    }
}
