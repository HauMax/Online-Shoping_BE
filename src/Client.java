public class Client extends Person implements finansable {
    private double balance;

    public Client(String name, double balance) {
        super(name);
        this.balance = balance;
    }

    @Override
    public double checkBalance() {
        return balance;
    }

    @Override
    public boolean hasAmountMoney(double amount) {
        return balance >= amount;
    }

    @Override
    public String getFinanStatus() {
        return String.format("Клиент: %s, Баланс: %.2f руб.", getName(), balance);
    }

    // Метод для зачисления средств (нужен для переводов)
    public void deposit(double amount) {
        this.balance += amount;
    }

    public void buyProduct(product p) throws StoreException {
        if (p.isPaid()) {
            try {
                throw new ProductAlreadyPaidException("Товар '" + p.get_Title() + "' уже оплачен.");
            } catch (ProductAlreadyPaidException e) {
                throw new RuntimeException(e);
            }
        }
        if (!p.isInStock()) {
            try {
                throw new ProductNotInStockException("Товар '" + p.get_Title() + "' временно отсутствует.");
            } catch (ProductNotInStockException e) {
                throw new RuntimeException(e);
            }
        }

        double price = p.getFinalPrice();

        if (!hasAmountMoney(price)) {
            try {
                throw new InsufficientFundsException("Недостаточно средств! Нужно: " + price + ", у вас: " + balance);
            } catch (InsufficientFundsException e) {
                throw new RuntimeException(e);
            }
        }

        balance -= price;
        p.pay(price);
        System.out.println("Успешно! Остаток на счету: " + balance);
    }

    public void transferMoney(Client target, double amount) throws StoreException {
        if (this == target) {
            try {
                throw new SelfTransferException("Попытка перевода денег самому себе запрещена.");
            } catch (SelfTransferException e) {
                throw new RuntimeException(e);
            }
        }
        if (amount <= 0) {
            throw new StoreException("Сумма перевода должна быть больше нуля.");
        }
        if (!hasAmountMoney(amount)) {
            try {
                throw new InsufficientFundsException("Недостаточно средств для перевода. Ваш баланс: " + balance);
            } catch (InsufficientFundsException e) {
                throw new RuntimeException(e);
            }
        }

        this.balance -= amount;
        target.deposit(amount);
        System.out.println("Перевод успешно выполнен.");
    }
}





