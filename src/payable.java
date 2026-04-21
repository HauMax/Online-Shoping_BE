public interface payable {
    double getFinalPrice();
    void pay(double amount);
    boolean isPaid();
}
