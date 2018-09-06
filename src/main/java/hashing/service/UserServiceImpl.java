package hashing.service;

import hashing.model.RegistrationRequest;
import hashing.model.UserSession;
import hashing.model.Users;
import hashing.repository.UserRepository;
import hashing.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    //todo transfer to application properties file and should be created using new SecureRandom();
    private String localsalt = "adsdasfas";
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserSessionRepository userSessionRepository;
    @Autowired
    PasswordEncoder encoder;

    @Transactional
    @Override
    public String registration(RegistrationRequest registrationRequest) {

        if (registrationRequest.getEmail() == null || registrationRequest.getPassword() == null){
            return "User details cannot be blank";
        }
        Users u = Users.builder()
                .email(registrationRequest.getEmail())
                .password(encoder.encode(localsalt + registrationRequest.getPassword()))
                .build();
        userRepository.save(u);
        return u.getPassword();
    }

    @Transactional
    @Override
    public String login(RegistrationRequest registrationRequest) {
        Optional<Users> u = userRepository.findById(registrationRequest.getEmail());
        if (encoder.matches(localsalt + registrationRequest.getPassword(), u.get().getPassword())){
            UserSession userSession = UserSession.builder()
                    .sessionId(UUID.randomUUID().toString())
                    .u(u.get())
                    .build();
            userSessionRepository.save(userSession);
            return userSession.getSessionId();
        }
        return "FAIL";
    }

    @Override
    public Users getUser(String email) {
        Users s = userRepository.findByEmail(email);
        return s;

    }
    }
