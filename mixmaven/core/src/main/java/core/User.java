package core;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

    private String username;
    private String password;
    private List<Drink> drinks = new ArrayList<>();

    /**
     * @param username
     * @param password
     * @throws IllegalArgumentException if not valid password
     */
    public User(String username, String password) throws IllegalArgumentException {
        if (!validatePassword(password)) {
            System.out.println("Password needs to be minimum 8 characters long, at least one letter and one number");
            throw new IllegalArgumentException();
        }
        this.username = username;
        this.password = password;
    }

    /**
     * Checks if password matches the following criteria:
     * Minimum 8 characters long.
     * Minimum 1 letter.
     * Minimum 1 number.
     * @param password
     * @return whether or not the password is valid
     */
    private boolean validatePassword(String password) {
        // Minimum eight characters, at least one letter and one number:
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    /**
     * Checks if a password matches this users password.
     * @param password
     * @return whether or not the password is correct
     */
    public boolean checkPassword(String password) {
        if (!validatePassword(password)) {
            return false;
        }
        return this.password.equals(password);
    }

    /**
     * @return a copy of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return a copy of this users drinks
     */
    public List<Drink> getUserDrinks() {
        return new ArrayList<>(drinks);
    }

    /**
     * @return a readable string describing this user
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Username: ");
        sb.append(username);
        sb.append(drinks.toString());
        return sb.toString();
    }

}
