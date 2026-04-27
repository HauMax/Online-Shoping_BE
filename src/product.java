import java.util.ArrayList;

public class product extends category implements payable {
    public static java.util.ArrayList<product> productList = new java.util.ArrayList<>();
    private boolean paidStatus = false;

    public product(String title, Double price, String description) {
        super(title, price, description);
        productList.add(this);
    }

    @Override
    public double getFinalPrice() { return get_Price(); }

    @Override
    public void pay(double amount) {
        if (amount >= getFinalPrice()) {
            this.paidStatus = true;
            System.out.println("Товар '" + get_Title() + "' оплачен.");
        }
    }

    @Override
    public boolean isPaid() { return paidStatus; }

    @Override
    public void showInfo() {
        System.out.println(this.toString() + (paidStatus ? " ОПЛАЧЕНО" : " НЕ ОПЛАЧЕНО"));
    }

    public static void showAllProducts() {
        System.out.println("\n--- Список всех товаров ---");
        for (product p : productList) p.showInfo();
    }
}


