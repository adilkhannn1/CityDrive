package citydrive.platform.user.controller;

import citydrive.platform.user.dto.request.UserRequest;
import citydrive.platform.user.dto.request.UserProfileRequest;
import citydrive.platform.user.dto.response.UserProfileResponse;
import citydrive.platform.user.dto.response.UserResponse;
import citydrive.platform.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }



    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        var user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        var users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PatchMapping("/{id}/profile")
    public ResponseEntity<UserProfileResponse> updateProfile(Long id, @Valid UserProfileRequest userProfileRequest){
        var userResponse = userService.updateProfile(id, userProfileRequest);
        return ResponseEntity.ok(userResponse);
    }


}
