package dtos.Menu;

import entities.Menu;

import java.util.List;
import java.util.Objects;

public class MenusDTO {
    private List<MenuDTO> menus;

    public MenusDTO(List<Menu> menus) {
        this.menus = MenuDTO.getFromList(menus);
    }

    public List<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDTO> menus) {
        this.menus = menus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenusDTO menusDTO = (MenusDTO) o;
        return Objects.equals(menus, menusDTO.menus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menus);
    }

    @Override
    public String toString() {
        return "MenusDTO{" +
                "menus=" + menus +
                '}';
    }
}
