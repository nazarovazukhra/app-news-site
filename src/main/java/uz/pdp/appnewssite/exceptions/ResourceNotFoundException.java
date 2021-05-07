package uz.pdp.appnewssite.exceptions;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@ResponseStatus(HttpStatus.NOT_FOUND)
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private final String resourceName; //lavozim

    private final String resourceField;// name

    private final Object object; //USER,ADMIN,1,500
}
