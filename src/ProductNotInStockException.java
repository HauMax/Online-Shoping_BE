public class ProductNotInStockException extends Exception{
    public ProductNotInStockException (String text){
        super("Ошибка: товара нет в наличии");
    }
}
