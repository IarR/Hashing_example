package hashing.service;

import hashing.model.RegistrationRequest;
import hashing.model.Users;

public interface UserService {

    String registration(RegistrationRequest registrationRequest);
    String login(RegistrationRequest registrationRequest);
    Users getUser(String email);
}
