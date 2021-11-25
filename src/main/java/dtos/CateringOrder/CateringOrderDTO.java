package dtos.CateringOrder;

import entities.CateringOrder;

import java.util.Date;
import java.util.Objects;

public class CateringOrderDTO {
    private Date created;
    private String deliveryDate;

    public CateringOrderDTO(CateringOrder cateringOrder) {
        this.created = cateringOrder.getCreated();
        this.deliveryDate = cateringOrder.getDeliveryDate();
    }

    public CateringOrderDTO(Date created, String deliveryDate) {
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
        return Objects.equals(created, that.created) && Objects.equals(deliveryDate, that.deliveryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(created, deliveryDate);
    }

    @Override
    public String toString() {
        return "CateringOrderDTO{" +
                "created=" + created +
                ", deliveryDate='" + deliveryDate + '\'' +
                '}';
    }
}
