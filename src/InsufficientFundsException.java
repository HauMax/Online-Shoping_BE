public class InsufficientFundsException extends Exception{
    public InsufficientFundsException (String text){
        super("Ошибка: недостаточно средств");
    }
}
