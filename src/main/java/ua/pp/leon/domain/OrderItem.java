package ua.pp.leon.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
@Entity(name = "order_items")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "oi_generator", strategy = "increment")
    @GeneratedValue(generator = "oi_generator")
    protected Long id;
    @Column
    protected Integer quantity;
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    protected Product product;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.quantity);
        hash = 53 * hash + Objects.hashCode(this.product);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderItem other = (OrderItem) obj;
        if (!Objects.equals(this.quantity, other.quantity)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "id=" + id + ", quantity=" + quantity + ", product="
                + product.getName() + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
