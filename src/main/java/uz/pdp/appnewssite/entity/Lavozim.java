package uz.pdp.appnewssite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.appnewssite.entity.enums.Huquq;
import uz.pdp.appnewssite.entity.template.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Lavozim extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String name;

//    @Enumerated(value = EnumType.STRING)
//    private LavozimTurlari lavozimTurlari;
//

    @Enumerated(value = EnumType.STRING)
    @ElementCollection()
    private List<Huquq> huquqList;

    @Column(columnDefinition = "text")
    private String description;

}
