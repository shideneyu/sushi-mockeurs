package fr.esgi.demo;

/**
 * Created by hugo on 16/03/2015.
 */
public class Account {

    private int money;
    private boolean blocked;

    // constructor
    public Account(int money) {
        super();
        this.money = money;
    }

    // simple one
    public Account(){
        this(0);
    }

    /***** Getters ******/

    public int getMoney() {
        return money;
    }

    public boolean isBlocked() {
        return blocked;
    }

    /***** Setters ******/

    public void setMoney(int money) {
        this.money = money;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
