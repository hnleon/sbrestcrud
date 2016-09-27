package ua.pp.leon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
@Entity(name = "orders")
public class Order implements Serializable, Comparable<Order> {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "order_generator", strategy = "increment")
    @GeneratedValue(generator = "order_generator")
    protected Long id;
    @NotNull
    @Column(nullable = false, name = "order_date")
    @Temporal(TemporalType.DATE)
    protected Date orderDate;
    @NotNull
    @Column(nullable = false, name = "summ", precision = 10, scale = 2)
    protected Double sum = 0.0;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
        CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST
    })
    @JsonIgnore
    @JoinTable(name = "product_order",
            joinColumns = @JoinColumn(name = "order_id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "product_id", nullable = false, updatable = false),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    protected Set<Product> products = new HashSet<>();

    @Override
    public int compareTo(Order o) {
        return sum.compareTo(o.sum);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.sum);
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.sum, other.sum)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "Order{" + "id=" + id + ", orderDate="
                + new SimpleDateFormat("yyyy-MM-dd").format(orderDate)
                + ", sum=" + sum + ", products=" + products.size() + '}';
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getSum() {
        return sum;
    }

    public Double recalculateSum() {
        sum = 0.0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return sum;
    }

    public Set<Product> getProducts() {
        return products;
    }
}
