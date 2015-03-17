package fr.esgi.demo.Services;

import fr.esgi.demo.Account;

/**
 * Created by hugo on 17/03/2015.
 */
public interface IAuthorizationService {

    public boolean isAuthorized(Account account);
}
