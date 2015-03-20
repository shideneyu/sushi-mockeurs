package demo;

import com.google.common.base.Preconditions;

import javax.security.sasl.AuthorizeCallback;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Benoit on 16/03/2015.
 */
public class BankService {


    private IAuthorizationService authorizationService;

    public Account pushMoney(Account account, int money)  {
        //if(account == null)  throw new IllegalArgumentException(); // ou utiliser gava avec precondition
        checkArgument(null != account);

        boolean authorized = authorizationService.isAuthorized(account);

        if(!authorized)
            throw new NotAuthorizedException();

        int newBalance = account.getMoney() + money;
        if(newBalance < 0)
            throw new CreditNotAuthorizedException();

        account.setMoney(newBalance);
        return account;
    }

    public void setAuthorizationService(IAuthorizationService authorizationService){
        this.authorizationService = authorizationService;
    }


}
