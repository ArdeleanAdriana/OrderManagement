package model;
/**
 * @author Ardelean Adriana Ioana
 * clasa care reprezinta o intrare a tabelei Orders
 */
public class Orders {
    private int id;
    private int clientId;
    /**
     * @param id id-ul comenzii
     * @param clientId id-ul clientului care a efectuat comanda
     */

    public Orders(int id, int clientId) {
        this.id = id;
        this.clientId=clientId;
    }
    public Orders(int clientId) {
        this.clientId=clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientId=" + clientId +
                '}';
    }
}
