import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Инициализация данных
        new MobileDevice("iPhone 15", 95000.0, "Смартфон");
        new MobileDevice("Xiaomi 13", 45000.0, "Смартфон");
        new electronic("Наушники Sony", 25000.0, "Беспроводные");
        new gardenItem("Лопата", 1200.0, "Стальная");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Пользовательское меню ---");
            System.out.println("1. Проверить категории и статистику");
            System.out.println("2. Проверить все товары");
            System.out.println("3. Критерии сортировки (Цена)");
            System.out.println("4. Сравнение двух товаров (по ID)");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Catalog.printStats();
                    break;
                case "2":
                    product.showAllProducts();
                    break;
                case "3":
                    sortMenu(scanner);
                    break;
                case "4":
                    compareProducts(scanner);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Ошибка: Выберите пункт от 0 до 4.");
            }
        }
    }

    private static void sortMenu(Scanner scanner) {
        System.out.println("1. По возрастанию цены");
        System.out.println("2. По убыванию цены");
        System.out.println("3. По названию (А-Я)");

        String sortChoice = scanner.nextLine();
        ArrayList<product> list = product.productList;

        switch (sortChoice) {
            case "1":
                Collections.sort(list); // Использует Comparable
                break;
            case "2":
                list.sort(Comparator.comparing(category::get_Price).reversed());
                break;
            case "3":
                list.sort(Comparator.comparing(category::get_Title));
                break;
            default:
                System.out.println("Защита: неверный фильтр.");
                return;
        }
        product.showAllProducts();
    }

    private static void compareProducts(Scanner scanner) {
        try {
            System.out.print("Введите ID первого товара: ");
            int id1 = Integer.parseInt(scanner.nextLine());
            System.out.print("Введите ID второго товара: ");
            int id2 = Integer.parseInt(scanner.nextLine());

            product p1 = findById(id1);
            product p2 = findById(id2);

            if (p1 != null && p2 != null) {
                if (p1.get_Price() > p2.get_Price()) {
                    System.out.println(p1.get_Title() + " дороже, чем " + p2.get_Title());
                } else if (p1.get_Price() < p2.get_Price()) {
                    System.out.println(p2.get_Title() + " дороже, чем " + p1.get_Title());
                } else {
                    System.out.println("Цены товаров равны.");
                }
            } else {
                System.out.println("Товар с таким ID не найден.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Вводите только числа!");
        }
    }

    private static product findById(int id) {
        return product.productList.stream()
                .filter(p -> p.get_id() == id)
                .findFirst().orElse(null);
    }
}

// TODO - этап 5:
//  5.1 - создать интерфейс payble(помогает объектам товарам, 3 абстрактных метода:
//   5.1.1 - double(getFinalPrice),
//   5.1.2 - void(pay принимает double, можно назвать amount) - оплата товаров)
//   5.1.3 - возвращает boolean(itsPayt)
//  5.2 создать интерфейс finansable(помогает объектам-клиентам), принимет 3 метода:
//   5.2.1 CheckBalance - double
//   5.2.2 HasAmountMoney - boolean
//   5.2.3 GetFinanStatus - String
//  5.3 Необходимо все классы расширять с помощью equals, hashCode, toString, insteadof, DON'T REPEAT
//  5.4 создаете объекты в классах и сравниваете

// TODO - 6
//  1) добавить сортировку категорий товаров, comparable по возрастанию или по убыванию
//  2) надо реализовать компаратор (фильтр по цене / по сезону / по памяти и т.д.)
//  3) создать пользовательское меню 4 кнопки к примеру: (проверить категории (саб категории и категории)) проверить товары, критерии сортировки (Убывание, возрастание и т.д.), сравнение товаров
//  4) добавить защиту фильтров









