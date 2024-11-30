public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount; 
    }

    public double getBalance() {
        return balance;
    }
}


public class BankAccountTest {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(100.0);

        System.out.println("Начальный баланс: " + account.getBalance());

        account.deposit(50.0); 
        System.out.println("После депозита 50.0: " + account.getBalance());

        account.withdraw(30.0); 
        System.out.println("После снятия 30.0: " + account.getBalance());

        account.deposit(-20.0); 
        System.out.println("После депозита -20.0: " + account.getBalance());

        account.withdraw(200.0); 
        System.out.println("После снятия 200.0: " + account.getBalance());
    }
}

