package demo;

/**
 * Created by Benoit on 16/03/2015.
 */
public class Account {
    private int money;

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    private  boolean blocked;

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
