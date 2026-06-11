public class ProductAlreadyPaidException extends Exception{
    public ProductAlreadyPaidException (String text){
        super("Ошибка: Товар был оплачен ранее");
    }
}
