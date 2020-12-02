package e.kozlova.CurrencyConverter.service;

import e.kozlova.CurrencyConverter.entity.User;
import e.kozlova.CurrencyConverter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findUserById(Long id){
        return userRepository.findById(id).get();
    }
}
