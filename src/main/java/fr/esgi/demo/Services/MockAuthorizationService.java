package fr.esgi.demo.Services;

import fr.esgi.demo.Account;

/**
 * Created by hugo on 17/03/2015.
 */
public class MockAuthorizationService implements IAuthorizationService {
    @Override
    public boolean isAuthorized(Account account) {
        return false;
    }
}
