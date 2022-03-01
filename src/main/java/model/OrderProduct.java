package model;
/**
 * @author Ardelean Adriana Ioana
 * clasa care reprezinta o intrare a tabelei OrderProduct
 */
public class OrderProduct {
    private int id;
    private int orderId;
    private int productId;
    private int quantity;


    public OrderProduct(int orderId, int productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }
    /**
     * @param id id-ul comenzii
     * @param orderId id-ul comenzii
     * @param productId id-ul produsului comandat
     * @param quantity cantitatea in care a fost comandat produsul
     */
    public OrderProduct(int id, int orderId, int productId, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
