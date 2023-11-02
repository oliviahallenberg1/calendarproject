package hh.sof03.kalenteriprojekti.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.sof03.kalenteriprojekti.domain.UserRepository;
import hh.sof03.kalenteriprojekti.domain.User;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository repository;

    @Autowired
    public UserDetailService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentUser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, currentUser.getPassword(),
                AuthorityUtils.createAuthorityList(currentUser.getRole()));
        return user;
    }
}
