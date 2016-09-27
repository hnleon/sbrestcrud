package ua.pp.leon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected Id id;
    @Column
    protected Integer quantity;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    protected Product product;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    protected Order order;

    public OrderItem() {
        id = new Id();
    }

    public OrderItem(Order order, Product product, Integer quantity) {
        id = new Id(order.getId(), product.getId());
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

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

    public Id getId() {
        return id;
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
        this.id.setProductId(product.getId());
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
        this.id.setOrderId(order.getId());
    }

    @Embeddable
    public static class Id implements Serializable {

        private static final long serialVersionUID = 1L;

        @Column(name = "product_id")
        protected Long productId;
        @Column(name = "order_id")
        protected Long orderId;

        public Id() {
            //
        }

        public Id(Long orderId, Long productId) {
            this.productId = productId;
            this.orderId = orderId;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 97 * hash + Objects.hashCode(this.productId);
            hash = 97 * hash + Objects.hashCode(this.orderId);
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
            final Id other = (Id) obj;
            if (!Objects.equals(this.productId, other.productId)) {
                return false;
            }
            if (!Objects.equals(this.orderId, other.orderId)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "Id{" + "productId=" + productId + ", orderId=" + orderId + '}';
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }
    }
}
