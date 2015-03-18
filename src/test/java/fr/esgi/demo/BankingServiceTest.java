package fr.esgi.demo;

import fr.esgi.demo.exceptions.CreditNotAuthorizedException;
import fr.esgi.demo.exceptions.NotAuthorizedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BankingServiceTest {

    private BankService bankService = new BankService();
    @Mock
    private IAuthorizationService authorizationService;
    @Spy
    private Account account;
    @Captor
    private ArgumentCaptor<Integer> amountCaptor;

    @Before
    public void setUp() {
        account.setBlocked(false);
        when(authorizationService.isAuthorized(account)).thenReturn(true);
        // this line is the same as the one just before, the syntax is inverted
//        doReturn(true).when(authorizationService.isAuthorized((any(Account.class))));
        bankService.setIAuthorizationService(authorizationService);
    }

    @Test
    public void should_AddMoneyToAccount_Nominal() {
        account = bankService.pushMoney(account, 1000);

        assertThat(account.getMoney(), is(1000));
        verify(account, times(1)).setMoney(anyInt());
    }

    @Test
    public void should_RemoveMoneyToAccount_Nominal() {
//        account.setMoney(1000);
        when(account.getMoney()).thenReturn(1000);

        account = bankService.pushMoney(account, -1000);

        verify(account, times(1)).setMoney(amountCaptor.capture());
        assertThat(amountCaptor.getValue(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNot_AddMoney_WhenAccountIsNull() {
        Account account = null;

        account = bankService.pushMoney(account, 1000);
    }

    @Test(expected = CreditNotAuthorizedException.class)
    public void shouldNot_RemoveMoney_WhenAccountUnderZero() {
        account.setMoney(0);

        account = bankService.pushMoney(account, -1000);
    }

    @Test(expected = NotAuthorizedException.class)
    public void shouldNot_AddMoney_WhenAccountIsBlocked() {
        account.setBlocked(true);
        when(authorizationService.isAuthorized(account)).thenReturn(false);

        bankService.pushMoney(account, 1000);
    }

}
