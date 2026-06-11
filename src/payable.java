public interface payable {
    double getFinalPrice();
    void pay(double amount) throws StoreException;
    boolean isPaid();
}
