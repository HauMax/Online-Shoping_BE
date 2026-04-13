public class Catalog {
    private int id;
    private String title;
    private double price;

    // Статические счетчики
    private static int categoryCount = 0;
    private static int subCategoryCount = 0;

    public Catalog(int id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    // Методы для увеличения счетчиков
    public static void incrementCategory() { categoryCount++; }
    public static void incrementSubCategory() { subCategoryCount++; }

    // Методы для получения значений
    public static void printStats() {
        System.out.println("\n--- Статистика каталога ---");
        System.out.println("Всего категорий: " + categoryCount);
        System.out.println("Всего подкатегорий (типов товаров): " + subCategoryCount);
    }

    // Геттеры и сеттеры
    public int getID() { return id; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
}
