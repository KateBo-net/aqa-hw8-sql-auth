package data;

import lombok.Value;

@Value
public class AuthInfo {
    private String login;
    private String password;
}
