package com.kyurao.atio.domain.user;

import com.kyurao.atio.domain.common.IdHolder;
import com.kyurao.atio.domain.lot.Bet;
import com.kyurao.atio.domain.lot.Lot;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User extends IdHolder {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Setter(AccessLevel.PROTECTED)
    private Account account = new Account();

    private ContactInfo contactInfo = new ContactInfo();

    @OneToMany(mappedBy = "seller")
    private Set<Lot> myLots = new HashSet<>();
}
