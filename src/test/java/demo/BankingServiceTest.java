package demo;

import org.aspectj.lang.annotation.Before;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Benoit on 16/03/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class BankingServiceTest {

    private BankService bankService = new BankService();
    private int countSetMoney =0;
    @Mock
    private  IAuthorizationService authorizationService;

    @Spy
    private Account account;

    @Captor
    private ArgumentCaptor<Integer> amountCaptor;

    @org.junit.Before
    public void setUp() {
        account.setBlocked(false);

        when(authorizationService.isAuthorized(account)).thenReturn(true);

        bankService.setAuthorizationService(authorizationService);


    }

    @Test
    public void should_AddMoneyToAccount() {

        //Given
            account.setMoney(0);
        //verify(account, times(1)).setMoney(anyInt());

        //When
        account = bankService.pushMoney(account, 1000);

        //Then
        assertThat(account.getMoney(), is(1000));


    }
    @Test
    public void should_RemoveMoneyToAccount() {
        //Given
        when(account.getMoney()).thenReturn(1000);
        //account.setMoney(1000);


        //When
        account = bankService.pushMoney(account, -1000);

        //Then
        verify(account, times(1)).setMoney(amountCaptor.capture());
        assertThat(amountCaptor.getValue(), is(0));
        //assertThat(account.getMoney(), is(0));
    }
    @Test(expected = IllegalArgumentException.class )
    public void shouldNot_AddMoneyWhenAccountIsNull() {
        //Given
       Account account = null;

        //When
        account = bankService.pushMoney(account, 1000);
    }

    @Test(expected = CreditNotAuthorizedException.class)
    public void shouldNot_RemoveMoney_WhenAccountUnderZero(){
        //Given
            account.setMoney(0);
        //verify(account, times(1)).setMoney(anyInt());
        //When
        account = bankService.pushMoney(account, -1000);

    }

    @Test(expected = NotAuthorizedException.class)
    public void shouldNot_AddMoney_WhenAccountIsBlocked(){
        account.setBlocked(true);
        when(authorizationService.isAuthorized(account)).thenReturn(false);

       // bankService.setAuthorizationService(authorizationService);



        bankService.pushMoney(account, 1000);
    }
}
