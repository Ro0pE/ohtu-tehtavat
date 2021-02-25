package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern p2 = Pattern.compile("([0-9])");
        Pattern p3 = Pattern.compile("([A-Z])", Pattern.CASE_INSENSITIVE);
        Matcher check2 = p2.matcher(password);
        Matcher check3 = p3.matcher(password);
        Matcher check = p.matcher(username);
        boolean foundChar = check3.find();
        boolean foundNumber = check2.find();
        boolean found = check.find();
        // validity check of username and password
        if (found || username.length() < 3 || password.length() < 8 || !foundNumber || !foundChar) {
            return true ;
        }

        return false;
    }
}
