package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CateringOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private Date created;
    private String deliveryDate;

    @OneToOne(mappedBy = "cateringOrder")
    private Menu menu;

    public CateringOrder(String deliveryDate) {
        this.created = new Date();
        this.deliveryDate = deliveryDate;
    }

    public CateringOrder() {
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
