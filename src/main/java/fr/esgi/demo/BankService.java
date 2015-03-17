package fr.esgi.demo;

import fr.esgi.demo.Exceptions.CreditNotAuthorizedException;
import fr.esgi.demo.Exceptions.NotAuthorizedException;
import fr.esgi.demo.Services.IAuthorizationService;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by hugo on 16/03/2015.
 */
public class BankService {

    private IAuthorizationService IAuthorizationService;

    public BankService() {
    }

    public Account pushMoney(Account account, int money) {
        checkArgument(null != account);

        boolean authorized = IAuthorizationService.isAuthorized(account);

        if (!authorized)
            throw new NotAuthorizedException();

        int newBalance = account.getMoney() + money;
        if (newBalance < 0)
            throw new CreditNotAuthorizedException();
        account.setMoney(newBalance);
        return account;
    }

    public IAuthorizationService getIAuthorizationService() {
        return IAuthorizationService;
    }

    public void setIAuthorizationService(IAuthorizationService IAuthorizationService) {
        this.IAuthorizationService = IAuthorizationService;
    }
}
