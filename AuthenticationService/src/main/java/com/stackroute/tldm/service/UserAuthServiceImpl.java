package com.stackroute.tldm.service;

import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    private UserAuthRepository userAuth;

    @Autowired
    public UserAuthServiceImpl(UserAuthRepository userAuth) {
        this.userAuth = userAuth;

    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        String info = user.getUserId().trim();
        System.out.println(info);
        try {
            if (!userAuth.existsById(info)
                    && ((findUserByUserEmail(user.getUserMail()) == null)) && (info.trim().length() > 0) && (info.length()==info.replaceAll("\\s", "").length())){

                user.setCreatedAt(new Date());
                userAuth.save(user);
                return user;

            } else {
                throw new UserAlreadyExistsException("User Information already present");
            }

        } catch (NoSuchElementException exception) {
            throw new UserAlreadyExistsException("User Information already present");
        }
    }

    public User findUserByUserEmail(String email) {
        User info = userAuth.findUserByUserMail(email);
        if (info != null) {
            return info;
        } else {
            return null;
        }
    }

    @Override
    public User findUserByUserIdAndPassword(String userId, String password) {
        User fetchedUser = userAuth.findUserByUserIdAndPassword(userId, password);
        return fetchedUser;
    }

}
