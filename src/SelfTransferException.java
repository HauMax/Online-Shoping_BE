public class SelfTransferException extends Exception{
    public SelfTransferException (String text){
        super("Ошибка: перевод самому себе");
    }
}
