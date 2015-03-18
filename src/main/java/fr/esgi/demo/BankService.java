package fr.esgi.demo;

import fr.esgi.demo.exceptions.CreditNotAuthorizedException;
import fr.esgi.demo.exceptions.NotAuthorizedException;

import static com.google.common.base.Preconditions.checkArgument;

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
