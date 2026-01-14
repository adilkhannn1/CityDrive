package citydrive.platform.user.service;

import citydrive.platform.user.dto.request.UserRequest;
import citydrive.platform.user.dto.request.UserProfileRequest;
import citydrive.platform.user.dto.response.UserResponse;
import citydrive.platform.user.dto.response.UserProfileResponse;
import citydrive.platform.user.mapper.UserMapper;
import citydrive.platform.user.mapper.UserProfileUpdateMapper;
import citydrive.platform.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserProfileUpdateMapper userProfileUpdateMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper, UserProfileUpdateMapper userProfileUpdateMapper){
        this.userRepository  = userRepository;
        this.userMapper = userMapper;
        this.userProfileUpdateMapper = userProfileUpdateMapper;
    }

    public UserResponse createUser(UserRequest userDTO){
        if(userRepository.existsByPhoneNumber(userDTO.phoneNumber())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User already exists by this phoneNumber");
        }
        var entity = userMapper.toUserEntity(userDTO);
        userRepository.save(entity);
        return userMapper.toUserResponse(entity);
    }
    public UserResponse getUserById(Long id){
        var user = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found by this id"));
        return userMapper.toUserResponse(user);
    }

    public List<UserResponse> getAllUsers(){
        var users = userRepository.findAll();
        return userMapper.toUsersResponse(users);
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





}
