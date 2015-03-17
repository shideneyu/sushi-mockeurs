package fr.esgi.demo;

/**
 * Created by hugo on 16/03/2015.
 */
public class BankService {

    public BankService() {
    }

    public Account pushMoney(Account account, int money){
        int newBalance = account.getMoney() + money;
//        if (money > 0) {
            account.setMoney(newBalance);
            return account;
//        } else {
//            return null;
//        }
    }

}
