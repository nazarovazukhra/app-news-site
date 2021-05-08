package uz.pdp.appnewssite.service;

import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Lavozim;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.UserDto;
import uz.pdp.appnewssite.repository.LavozimRepository;
import uz.pdp.appnewssite.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;
    final LavozimRepository lavozimRepository;

    public UserService(UserRepository userRepository, LavozimRepository lavozimRepository) {
        this.userRepository = userRepository;
        this.lavozimRepository = lavozimRepository;
    }

    public ApiResponse editUser(Long id, UserDto userDto) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("Such user not found", false);

        User editingUser = optionalUser.get();
        editingUser.setUsername(userDto.getUsername());
        editingUser.setFullName(userDto.getFullName());
        editingUser.setPassword(userDto.getPassword());

        Optional<Lavozim> optionalLavozim = lavozimRepository.findById(userDto.getLavozimId());
        if (!optionalLavozim.isPresent())
            return new ApiResponse("Such lavozim not found", false);
        Lavozim lavozim = optionalLavozim.get();

        editingUser.setLavozim(lavozim);

        userRepository.save(editingUser);
        return new ApiResponse("User edited", true);

    }


    public ApiResponse deleteUser(Long id) {

        boolean existsById = userRepository.existsById(id);
        if (!existsById)
            return new ApiResponse("User not found", false);

        userRepository.deleteById(id);
        return new ApiResponse("User deleted", true);
    }

    public List<User> getAll() {

        return userRepository.findAll();
    }
}
