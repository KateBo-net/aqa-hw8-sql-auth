package data;

public class AuthInfo {
    private final String login;
    private final String password;

    public AuthInfo(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
}
