package dtos.User;

import entities.User;

import java.util.List;
import java.util.Objects;

public class UsersDTO {
    private List<UserDTO> users;

    public UsersDTO(List<User> users) {
        this.users = UserDTO.getFromList(users);
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersDTO usersDTO = (UsersDTO) o;
        return Objects.equals(users, usersDTO.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    @Override
    public String toString() {
        return "UsersDTO{" +
                "users=" + users +
                '}';
    }
}
