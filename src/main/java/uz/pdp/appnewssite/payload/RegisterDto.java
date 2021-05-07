package uz.pdp.appnewssite.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterDto {

    @NotNull(message = "fullName must not be null")
    private String fullName;

    @NotNull(message = "username must not be null")
    private String username;

    @NotNull(message = "password must not be null")
    private String password;

    @NotNull(message = "prePassword must not be null")
    private String prePassword;
}
