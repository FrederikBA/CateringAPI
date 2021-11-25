package dtos.CateringOrder;

import dtos.Menu.MenuDTO;
import dtos.Role.RoleDTO;
import entities.CateringOrder;
import entities.Role;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CateringOrderDTO {
    private int id;
    private Date created;
    private String deliveryDate;
    private MenuDTO menuDTO;

    public static List<CateringOrderDTO> getFromList(List<CateringOrder> orders) {
        return orders.stream()
                .map(order -> new CateringOrderDTO(order))
                .collect(Collectors.toList());
    }

    public CateringOrderDTO(CateringOrder cateringOrder) {
        this.id = cateringOrder.getId();
        this.created = cateringOrder.getCreated();
        this.deliveryDate = cateringOrder.getDeliveryDate();
        this.menuDTO = new MenuDTO(cateringOrder.getMenu());

    }

    public CateringOrderDTO(Date created, String deliveryDate) {
        int id = -1;
        this.created = created;
        this.deliveryDate = deliveryDate;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CateringOrderDTO that = (CateringOrderDTO) o;
        return id == that.id && Objects.equals(created, that.created) && Objects.equals(deliveryDate, that.deliveryDate) && Objects.equals(menuDTO, that.menuDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, deliveryDate, menuDTO);
    }

    @Override
    public String toString() {
        return "CateringOrderDTO{" +
                "id=" + id +
                ", created=" + created +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", menuDTO=" + menuDTO +
                '}';
    }
}
