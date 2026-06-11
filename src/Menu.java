import java.util.*;

public class Menu {
    private Scanner scanner;
    private Client currentClient;
    private Client secondClient;

    // Конструктор инициализирует все необходимые данные при создании меню
    public Menu() {
        scanner = new Scanner(System.in);
        initClients();
        initProducts();
    }

    private void initClients() {
        currentClient = new Client("ВАЛЕРААА", 100000.0);
        secondClient = new Client("ПАВЕЛПАВЕЛПАВЕЛ", 50000.0);
    }

    private void initProducts() {
        new MobileDevice("iPhone 15", 95000.0, "Смартфон");
        new MobileDevice("Xiaomi 13", 45000.0, "Смартфон");
        new MobileDevice("POCO C4", 45000.0, "Смартфон");
        new electronic("Наушники Sony", 25000.0, "Беспроводные");
        new electronic("Микрофон Fifin", 25000.0, "Беспроводные");
        new gardenItem("Лопата", 1200.0, "Стальная");
        new gardenItem("Лейка", 1000.0, "Стальная");
    }

    // Главный метод запуска цикла меню
    public void start() {
        while (true) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("ПОЛЬЗОВАТЕЛЬСКОЕ МЕНЮ");
            System.out.println("=".repeat(50));
            System.out.println(currentClient.getFinanStatus());
            System.out.println("""
        КАТАЛОГ:
        1. Статистика категорий
        2. Все товары
        3. Только товары в наличии
        
        ФИЛЬТРЫ (StreamAPI):
        4. Фильтр по цене (диапазон)
        5. Поиск по названию
        6. Товары со скидкой
        7. Фильтр по статусу заказа
        
        ДОПОЛНИТЕЛЬНО:
        8. Кастомный фильтр (лямбда)
        9. Сортировка товаров
        10. Сравнение двух товаров
        11. Купить товар
        12. Перевести деньги другому клиенту
        0. Выход
        """);
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> Catalog.printStats();
                case "2" -> product.showAllProducts();
                case "3" -> product.showProducts(product.filterInStock());
                case "4" -> filterByPrice();
                case "5" -> filterByName();
                case "6" -> filterByDiscount();
                case "7" -> filterByStatus();
                case "8" -> customFilterExample();
                case "9" -> sortMenu();
                case "10" -> compareProducts();
                case "11" -> purchaseMenu();
                case "12" -> transferMoneyMenu();
                case "0" -> {
                    System.out.println("До свидания!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Ошибка: Выберите пункт от 0 до 12.");
            }
        }
    }

    // Метод с использованием finally для гарантии снятия блокировки (ресурса)
    private void processPurchase(product p) {
        boolean isLocked = false;
        try {
            System.out.println("[Система] Блокировка товара ID " + p.get_id() + " для покупки...");
            isLocked = true;

            currentClient.buyProduct(p);

            System.out.println("[Система] Покупка успешно завершена.");
        } catch (StoreException e) {
            System.out.println("[Ошибка бизнес-логики] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[Непредвиденная ошибка] " + e.getMessage());
        } finally {
            if (isLocked) {
                System.out.println("[Система] Разблокировка товара ID " + p.get_id() + "...");
                isLocked = false;
            }
        }
    }

    private void sortMenu() {
        System.out.println("1. По возрастанию цены");
        System.out.println("2. По убыванию цены");
        System.out.println("3. По названию (А-Я)");

        String sortChoice = scanner.nextLine();
        ArrayList<product> list = product.productList;

        try {
            switch (sortChoice) {
                case "1":
                    Collections.sort(list);
                    break;
                case "2":
                    list.sort(Comparator.comparing(product::get_Price).reversed());
                    break;
                case "3":
                    list.sort(Comparator.comparing(product::get_Title));
                    break;
                default:
                    System.out.println("Защита: неверный фильтр.");
                    return;
            }
            product.showAllProducts();
        } catch (Exception e) {
            System.out.println("Ошибка сортировки: " + e.getMessage());
        }
    }

    private void compareProducts() {
        try {
            System.out.print("Введите ID первого товара: ");
            int id1 = Integer.parseInt(scanner.nextLine());
            System.out.print("Введите ID второго товара: ");
            int id2 = Integer.parseInt(scanner.nextLine());

            product p1 = findById(id1);
            product p2 = findById(id2);

            if (p1.get_Price() > p2.get_Price()) {
                System.out.println(p1.get_Title() + " дороже, чем " + p2.get_Title());
            } else if (p1.get_Price() < p2.get_Price()) {
                System.out.println(p2.get_Title() + " дороже, чем " + p1.get_Title());
            } else {
                System.out.println("Цены товаров равны.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Вводите только числа!");
        } catch (ProductNotFoundException e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }
    }

    private product findById(int id) throws ProductNotFoundException {
        return product.productList.stream()
                .filter(p -> p.get_id() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Товар с ID " + id + " не существует в каталоге."));
    }

    private void filterByPrice() {
        try {
            System.out.print("Минимальная цена: ");
            double min = Double.parseDouble(scanner.nextLine());
            System.out.print("Максимальная цена: ");
            double max = Double.parseDouble(scanner.nextLine());

            if (min > max) throw new IllegalArgumentException("Минимальная цена не может быть больше максимальной.");

            List<product> result = product.filterByPriceRange(min, max);
            product.showProducts(result);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Введите корректные числа.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }

    private void filterByName() {
        System.out.print("Введите часть названия: ");
        String keyword = scanner.nextLine();
        List<product> result = product.filterByName(keyword);
        product.showProducts(result);
    }

    private void filterByDiscount() {
        try {
            System.out.print("Минимальная скидка (%): ");
            double minDisc = Double.parseDouble(scanner.nextLine());
            List<product> result = product.filterByDiscount(minDisc);
            product.showProducts(result);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Введите число.");
        }
    }

    private void filterByStatus() {
        System.out.println("Статусы: 1-Не куплено, 2-В процессе, 3-Куплено");
        System.out.print("Выберите статус (1-3): ");
        String choice = scanner.nextLine();

        PaymentStatus status = switch (choice) {
            case "1" -> PaymentStatus.ORDERED;
            case "2" -> PaymentStatus.IN_PROGRESS;
            case "3" -> PaymentStatus.RECEIVED;
            default -> null;
        };

        if (status != null) {
            List<product> result = product.filterByStatus(status);
            product.showProducts(result);
        } else {
            System.out.println("Неверный выбор.");
        }
    }

    private void customFilterExample() {
        System.out.println("\nКастомный фильтр (пример лямбды):");
        System.out.println("Фильтр: товары дороже 30000 руб. И со скидкой >10% И в наличии");

        List<product> result = product.filterCustom(p ->
                p.get_Price() > 30000 &&
                        p.getDiscount() > 10 &&
                        p.isInStock()
        );
        product.showProducts(result);

        System.out.println("\nВведите слово для поиска: ");
        String word = scanner.nextLine();
        List<product> result2 = product.filterCustom(p ->
                p.get_Title().toLowerCase().contains(word.toLowerCase()) &&
                        p.get_Price() < 50000
        );
        product.showProducts(result2);
    }

    private void purchaseMenu() {
        try {
            System.out.print("Введите ID товара для покупки: ");
            int id = Integer.parseInt(scanner.nextLine());

            product p = findById(id);
            processPurchase(p);

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Введите числовой ID.");
        } catch (ProductNotFoundException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void transferMoneyMenu() {
        try {
            System.out.println("Доступные получатели:");
            System.out.println("1. ПЕТЯ (Баланс: " + secondClient.checkBalance() + ")");
            System.out.println("2. САМОМУ СЕБЕ");
            System.out.print("Выберите получателя (1-2): ");
            String choice = scanner.nextLine();

            Client target = choice.equals("1") ? secondClient : currentClient;

            System.out.print("Введите сумму перевода: ");
            double amount = Double.parseDouble(scanner.nextLine());

            currentClient.transferMoney(target, amount);
            System.out.println("Операция завершена. Текущий баланс: " + currentClient.checkBalance());

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Введите корректную числовую сумму.");
        } catch (StoreException e) {
            System.out.println("Ошибка перевода: " + e.getMessage());
        }
    }
}