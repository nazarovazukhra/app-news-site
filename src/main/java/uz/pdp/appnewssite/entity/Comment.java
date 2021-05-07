package uz.pdp.appnewssite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.appnewssite.entity.template.AbstractEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment extends AbstractEntity {


    @Column(nullable = false, columnDefinition = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;


}
