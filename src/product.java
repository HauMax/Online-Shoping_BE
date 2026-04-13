import java.util.ArrayList;

public class product extends category {
    // Список для хранения всех товаров
    public static ArrayList<product> productList = new ArrayList<>();

    public product(String title, Double price, String description) {
        super(title, price, description);
        productList.add(this); // Автоматически добавляем каждый новый товар в список
    }

    @Override
    public void showInfo() {
        System.out.println("ID: " + get_id() +
                " Название: " + get_Title() +
                " Цена: " + get_Price() + " руб." +
                " Описание: " + get_Description());
    }

    // Статический метод для вывода всего списка
    public static void showAllProducts() {
        System.out.println("\n--- Список всех товаров ---");
        for (product p : productList) {
            p.showInfo();
        }
    }
}


