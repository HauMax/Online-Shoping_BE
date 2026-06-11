import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class product extends category implements payable {
    public static ArrayList<product> productList = new ArrayList<>();

    private boolean paidStatus = false;
    private PaymentStatus orderStatus = PaymentStatus.ORDERED;
    private boolean inStock = true;
    private double discount = 0.0;

    public product(String title, Double price, String description) {
        super(title, price, description);
        productList.add(this);
    }

    public PaymentStatus getOrderStatus() { return orderStatus; }
    public void setOrderStatus(PaymentStatus status) { this.orderStatus = status; }

    public boolean isInStock() { return inStock; }
    public void setInStock(boolean inStock) { this.inStock = inStock; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) {
        this.discount = Math.max(0, Math.min(100, discount));
    }

    @Override
    public double getFinalPrice() {
        return get_Price() * (1 - discount / 100);
    }

    @Override
    public void pay(double amount) throws StoreException {
        if (amount < getFinalPrice()) {
            throw new StoreException("Внесенной суммы недостаточно для оплаты товара.");
        }
        this.paidStatus = true;
        this.orderStatus = PaymentStatus.RECEIVED;
        System.out.println("Товар '" + get_Title() + "' успешно оплачен.");
    }

    @Override
    public boolean isPaid() { return paidStatus; }

    @Override
    public void showInfo() {
        System.out.println(String.format("[%s] ID: %d | %s | %.2f руб. | Скидка: %.0f%% | %s | Статус: %s",
                getClass().getSimpleName(),
                get_id(),
                get_Title(),
                getFinalPrice(),
                discount,
                inStock ? "В наличии" : "Нет в наличии",
                orderStatus.getDescription()));
    }

    public static List<product> filterByPriceRange(double min, double max) {
        return productList.stream()
                .filter(p -> p.get_Price() >= min && p.get_Price() <= max)
                .collect(Collectors.toList());
    }

    public static List<product> filterByName(String keyword) {
        return productList.stream()
                .filter(p -> p.get_Title().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static List<product> filterByDiscount(double minDiscount) {
        return productList.stream()
                .filter(p -> p.getDiscount() >= minDiscount)
                .collect(Collectors.toList());
    }

    public static List<product> filterInStock() {
        return productList.stream()
                .filter(product::isInStock)
                .collect(Collectors.toList());
    }

    public static List<product> filterByStatus(PaymentStatus status) {
        return productList.stream()
                .filter(p -> p.getOrderStatus() == status)
                .collect(Collectors.toList());
    }

    public static List<product> filterCustom(ProductFilter filter) {
        return productList.stream()
                .filter(filter::test)
                .collect(Collectors.toList());
    }

    public static void showAllProducts() {
        System.out.println("\n--- Список всех товаров ---");
        productList.forEach(product::showInfo);
    }

    public static void showProducts(List<product> products) {
        if (products.isEmpty()) {
            System.out.println("Товары не найдены.");
            return;
        }
        System.out.println("\n--- Найдено товаров: " + products.size() + " ---");
        products.forEach(product::showInfo);
    }
}


