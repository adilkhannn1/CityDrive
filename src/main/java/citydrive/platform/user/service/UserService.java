package citydrive.platform.user.service;

import citydrive.platform.user.dto.request.UserRequest;
import citydrive.platform.user.dto.request.UserProfileRequest;
import citydrive.platform.user.dto.response.UserResponse;
import citydrive.platform.user.dto.response.UserProfileResponse;
import citydrive.platform.user.entity.UserEntity;
import citydrive.platform.user.enums.Role;
import citydrive.platform.user.mapper.UserMapper;
import citydrive.platform.user.mapper.UserProfileUpdateMapper;
import citydrive.platform.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserProfileUpdateMapper userProfileUpdateMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserEntity createUser(UserRequest userRequest){

        if(userRepository.existsByEmail(userRequest.email())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists by this email");
        }
        var hashedPassword = passwordEncoder.encode(userRequest.password());
        var entity = userMapper.toUserEntity(userRequest);
        entity.setRole(Role.USER);
        entity.setPassword(hashedPassword);
        UserEntity savedUser = userRepository.save(entity);
        log.info("User created successfully with ID: {}",  savedUser.getId());
        return savedUser;
    }



    public UserResponse getUserById(Long id){
        var user = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found by this id"));
        return userMapper.toUserResponse(user);
    }

    public List<UserResponse> getAllUsers(){
        var users = userRepository.findAll();
        return userMapper.toUsersResponse(users);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public UserProfileResponse updateProfile(Long id, UserProfileRequest userProfileRequest) {
        var entity = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found by this ID"));
        entity.setCity(userProfileRequest.city());
        entity.setFullName(userProfileRequest.fullName());
        entity.setBirthDate(userProfileRequest.birthDate());
        entity.setGender(userProfileRequest.gender());
        entity.setLanguage(userProfileRequest.language());
        userRepository.save(entity);
        return userMapper.toUserProfileResponse(entity);
    }
    public UserEntity getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found by this email!"));
    }


}
