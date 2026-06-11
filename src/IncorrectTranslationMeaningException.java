public class IncorrectTranslationMeaningException extends Exception{
    public IncorrectTranslationMeaningException (String text){
        super("Неверное значение перевода");
    }
}
