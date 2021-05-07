package uz.pdp.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull(message = "fullName must not be null")
    private String fullName;

    @NotNull(message = "username must not be null")
    private String username;

    @NotNull(message = "password must not be null")
    private String password;

    @NotNull(message = "lavozim must not be null")
    private Integer lavozimId;
}
