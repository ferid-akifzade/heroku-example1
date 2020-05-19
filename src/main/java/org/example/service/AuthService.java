package org.example.service;

import org.example.dao.UsersDAO;
import org.example.libs.User;
import org.example.libs.UserNotFoundException;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;


public class AuthService {
    private final UsersDAO usersDAO = new UsersDAO();

    public Optional<User> login(User user) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashed = digest.digest(user.getPassword().getBytes());
            String hashedPass = DatatypeConverter.printHexBinary(hashed);
            User databaseUser = usersDAO.getUserByUsername(user.getUsername());
            if(databaseUser.getPassword().equals(hashedPass))
                return Optional.of(databaseUser);
            return Optional.empty();
        } catch (UserNotFoundException | NoSuchAlgorithmException e) {
            return Optional.empty();
        }
    }

    public boolean loginPlain(String name, String password) throws UserNotFoundException {
        User databaseUser = usersDAO.getUserByUsername(name);
        return databaseUser.getPassword().equals(password);
    }

}
