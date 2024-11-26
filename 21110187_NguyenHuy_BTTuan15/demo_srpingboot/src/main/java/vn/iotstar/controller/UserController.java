package vn.iotstar.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import vn.iotstar.model.user;
import vn.iotstar.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {
	private final UserService userService = new UserService();

    @QueryMapping
    public List<user> getAllUsers() {
        return userService.getAllUsers();
    }

    @MutationMapping
    public user createUser(@Argument String fullname, @Argument String email, 
                           @Argument String password, @Argument String phone) {
        user user = new user();
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        return userService.createUser(user);
    }

    @MutationMapping
    public user updateUser(@Argument Long id, @Argument String fullname, 
                           @Argument String email, @Argument String password, 
                           @Argument String phone) {
        user newUser = new user();
        newUser.setFullname(fullname);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setPhone(phone);
        return userService.updateUser(id, newUser);
    }

    @MutationMapping
    public String deleteUser(@Argument Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}
