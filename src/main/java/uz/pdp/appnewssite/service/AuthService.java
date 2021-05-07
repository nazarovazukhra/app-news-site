package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.exceptions.ResourceNotFoundException;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.repository.LavozimRepository;
import uz.pdp.appnewssite.payload.RegisterDto;
import uz.pdp.appnewssite.repository.UserRepository;
import uz.pdp.appnewssite.utils.AppConstants;

@Service
public class AuthService implements UserDetailsService {

    final UserRepository userRepository;
    final LavozimRepository lavozimRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, LavozimRepository lavozimRepository) {
        this.userRepository = userRepository;
        this.lavozimRepository = lavozimRepository;
    }

    public ApiResponse registerUser(RegisterDto registerDto) {

        if (!registerDto.getPassword().equals(registerDto.getPrePassword()))
            return new ApiResponse("Passwords are not equal", false);

        boolean existsByUsername = userRepository.existsByUsername(registerDto.getUsername());
        if (existsByUsername)
            return new ApiResponse("Such user already exists", false);

        User user = new User(
                registerDto.getFullName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                lavozimRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("lavozim", "name", AppConstants.USER)),
                true
        );

        userRepository.save(user);
        return new ApiResponse("User successfully registered", true);

    }


    public UserDetails loadUserByUsername(String username) {

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }


}
