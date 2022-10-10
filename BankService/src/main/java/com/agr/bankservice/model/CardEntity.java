package com.agr.bankservice.model;


import com.agr.bankservice.model.enums.CardAuthMethodeEnum;
import com.agr.bankservice.model.enums.CardStateEnum;
import com.agr.bankservice.model.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "card")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardEntity extends BaseEntity {

    @Column(name = "card_number", length = 30, unique = true, nullable = false)
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_methode", length = 20, nullable = false)
    private CardAuthMethodeEnum authMethode;

    @Column(name = "pin", length = 100)
    private String pin;

    @Column(name = "fingerprint", length = 220)
    private String fingerprint;
    @Column(name = "balance")
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Column(name = "wrong_attempts")
    private int wrongAttempts = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private CardStateEnum state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private AccountEntity account;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private SessionEntity session;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardEntity)) return false;
        if (!super.equals(o)) return false;

        CardEntity card = (CardEntity) o;

        if (wrongAttempts != card.wrongAttempts) return false;
        if (!Objects.equals(cardNumber, card.cardNumber)) return false;
        if (authMethode != card.authMethode) return false;
        if (!Objects.equals(pin, card.pin)) return false;
        if (!Objects.equals(fingerprint, card.fingerprint)) return false;
        if (!Objects.equals(balance, card.balance)) return false;
        if (currency != card.currency) return false;
        if (state != card.state) return false;
        if (!Objects.equals(account, card.account)) return false;
        return Objects.equals(session, card.session);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (authMethode != null ? authMethode.hashCode() : 0);
        result = 31 * result + (pin != null ? pin.hashCode() : 0);
        result = 31 * result + (fingerprint != null ? fingerprint.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + wrongAttempts;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (session != null ? session.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CardEntity{" +
                "cardNumber='" + cardNumber + '\'' +
                ", authMethode=" + authMethode +
                ", pin='" + pin + '\'' +
                ", fingerprint='" + fingerprint + '\'' +
                ", balance=" + balance +
                ", currency=" + currency +
                ", wrongAttempts=" + wrongAttempts +
                ", state=" + state +
                ", account=" + account +
                ", session=" + session +
                "} " + super.toString();
    }
}
