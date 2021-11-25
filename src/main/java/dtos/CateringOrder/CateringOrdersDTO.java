package dtos.CateringOrder;

import dtos.Role.RoleDTO;
import entities.CateringOrder;

import java.util.List;
import java.util.Objects;

public class CateringOrdersDTO {

    private List<CateringOrderDTO> orders;

    public CateringOrdersDTO(List<CateringOrder> orders) {
        this.orders = CateringOrderDTO.getFromList(orders);
    }

    public List<CateringOrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<CateringOrderDTO> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CateringOrdersDTO that = (CateringOrdersDTO) o;
        return Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orders);
    }

    @Override
    public String toString() {
        return "CateringOrdersDTO{" +
                "orders=" + orders +
                '}';
    }
}
