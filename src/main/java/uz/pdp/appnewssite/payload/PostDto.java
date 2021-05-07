package uz.pdp.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private String title;

    private String text;

    private String url;


}
