package fr.esgi.demo;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by hugo on 16/03/2015.
 */
public class BankingServiceTest {

    private BankService bankService = new BankService();
    private Account account = new Account();

    @Test
    public void should_AddMoneyToAccount_Nominal() {
        //Given
        account.setMoney(0);

        //When
        account = bankService.pushMoney(account, 1000);

        //Then
        assertThat(account.getMoney(), is(1000));
    }

    @Test
    public void should_RemoveMoneyToAccount_Nominal() {
        //Given
        account.setMoney(1000);

        //When
        account = bankService.pushMoney(account, -1000);

        //Then
        assertThat(account.getMoney(), is(0));
    }

}
