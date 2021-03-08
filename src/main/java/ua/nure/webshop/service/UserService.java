package ua.nure.webshop.service;

import net.moznion.random.string.RandomStringGenerator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.w3c.dom.CharacterData;
import ua.nure.webshop.domain.User;
import ua.nure.webshop.repos.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public void addUser(User user) {
        repository.save(user);
    }

    public String generateRandomSpecialCharacters() {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator();
        pwdGenerator.setNumOfUpperLimit(3);
        return pwdGenerator.generateByRegex("!@#$%^&*()_+");
    }
}
