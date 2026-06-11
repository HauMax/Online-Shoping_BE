public class ProductNotFoundException extends Exception{
    public ProductNotFoundException (String text){
        super("Ошибка: не найден продукт");
    }
}
