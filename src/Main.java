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