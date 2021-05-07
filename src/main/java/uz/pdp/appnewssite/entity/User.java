package uz.pdp.appnewssite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.appnewssite.entity.enums.Huquq;
import uz.pdp.appnewssite.entity.template.AbstractEntity;

import javax.persistence.*;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sys_user")
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractEntity implements UserDetails {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Lavozim lavozim;

    private boolean enabled;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Huquq> huquqList = this.getLavozim().getHuquqList();

        Set<GrantedAuthority> grantedAuthorities=new HashSet<>();
        for (Huquq huquq : huquqList) {
            grantedAuthorities.add(new SimpleGrantedAuthority(huquq.name()));
        }

//        for (Huquq huquq : huquqList) {
//            grantedAuthorities.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return huquq.name();
//                }
//            });
//        }

        return grantedAuthorities;
    }

    public User(String fullName, String username, String password, Lavozim lavozim, boolean enabled) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.lavozim = lavozim;
        this.enabled = enabled;
    }


}
