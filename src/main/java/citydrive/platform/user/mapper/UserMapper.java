package citydrive.platform.user.mapper;

import citydrive.platform.user.dto.request.UserRequest;
import citydrive.platform.user.dto.response.UserResponse;
import citydrive.platform.user.dto.response.UserProfileResponse;
import citydrive.platform.user.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public UserRequest toUserRequest(UserEntity userEntity){
        return new UserRequest(
                userEntity.getFullName(),
                userEntity.getGender(),
                userEntity.getEmail(),
                userEntity.getCity(),
                userEntity.getBirthDate(),
                userEntity.getPassword()
        );
    }

    public UserResponse toUserResponse(UserEntity userEntity){
        return new UserResponse(
                userEntity.getId(),
                userEntity.getFullName(),
                userEntity.getGender(),
                userEntity.getEmail(),
                userEntity.getCity(),
                userEntity.getBirthDate()
        );
    }

    public UserEntity toUserEntity(UserRequest userRequest){
        return new UserEntity(
                userRequest.email(),
                userRequest.fullName(),
                userRequest.gender(),
                userRequest.city(),
                userRequest.birthDate(),
                userRequest.password()
        );
    }




    public List<UserResponse> toUsersResponse(List<UserEntity> userEntities){
        var userListDTO = userEntities.stream().map(userEntity -> new UserResponse(
                userEntity.getId(),
                userEntity.getFullName(),
                userEntity.getGender(),
                userEntity.getEmail(),
                userEntity.getCity(),
                userEntity.getBirthDate()
        )).toList();
        return userListDTO;
    }

    public UserProfileResponse toUserProfileResponse(UserEntity userEntity){
        return new UserProfileResponse(
             userEntity.getFullName(),
                userEntity.getCity(),
                userEntity.getBirthDate(),
                userEntity.getGender(),
                userEntity.getAvatarUrl(),
                userEntity.getLanguage()

        );
    }

}
