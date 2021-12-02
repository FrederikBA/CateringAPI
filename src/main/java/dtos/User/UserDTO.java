package dtos.User;

import dtos.Menu.MenuDTO;
import dtos.Role.RoleDTO;
import entities.Menu;
import entities.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDTO {
    private String userName;
    private String userPass;
    private List<RoleDTO> roles;


    public static List<UserDTO> getFromList(List<User> users) {
        return users.stream()
                .map(user -> new UserDTO(user))
                .collect(Collectors.toList());
    }

    public UserDTO(User user) {
        this.userName = user.getUserName();
        this.userPass = user.getUserPass();
        this.roles = RoleDTO.getFromList(user.getRoleList());
    }

    public UserDTO(String userName, String userPass, List<RoleDTO> roles) {
        this.userName = userName;
        this.userPass = userPass;
        this.roles = roles;
    }

    public UserDTO(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    public UserDTO() {
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(userName, userDTO.userName) && Objects.equals(userPass, userDTO.userPass) && Objects.equals(roles, userDTO.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userPass, roles);
    }
}
