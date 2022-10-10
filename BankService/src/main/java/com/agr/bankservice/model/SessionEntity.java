package com.agr.bankservice.model;

import com.agr.bankservice.model.enums.SessionState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "session")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionEntity extends BaseEntity {

    @Column(name = "token", length = 100)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private SessionState state;
    @OneToOne(mappedBy = "session")
    private CardEntity card;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionEntity)) return false;
        if (!super.equals(o)) return false;

        SessionEntity session = (SessionEntity) o;

        if (!Objects.equals(token, session.token)) return false;
        if (state != session.state) return false;
        return Objects.equals(card, session.card);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (card != null ? card.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SessionEntity{" +
                "token='" + token + '\'' +
                ", state=" + state +
                ", card=" + card +
                "} " + super.toString();
    }
}
