package ua.pp.leon.controller.data;

/**
 * Create new order Parameter in {@link StatsRestController}.
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
public class CreateOrderParam {

    private Long productId;
    private Integer quantity;

    public CreateOrderParam() {
        //
    }

    public CreateOrderParam(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
    
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
