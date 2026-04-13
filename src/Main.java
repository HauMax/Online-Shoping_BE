public class Main {
    public static void main(String[] args) {
        // разные товары
        new MobileDevice("iPhone 15", 95000.0, "Смартфон");
        new electronic("Наушники Sony", 25000.0, "Беспроводные наушники");
        new gardenItem("Лопата", 1200.0, "Стальная, садовая");

        // Выводим весь список
        product.showAllProducts();
        Catalog.printStats();
    }
}