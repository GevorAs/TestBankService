package com.agr.bankservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity extends BaseEntity {

    @Column(name = "account_number", length = 20, nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "first_name", length = 30, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 30, nullable = false)
    private String lastName;

    @Column(name = "Phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "email", length = 100, nullable = false)
    private String email;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardEntity> accountCards = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "actual_id", referencedColumnName = "id")
    private AddressEntity actual;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_id", referencedColumnName = "id")
    private AddressEntity registered;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountEntity)) return false;
        if (!super.equals(o)) return false;

        AccountEntity that = (AccountEntity) o;

        if (!Objects.equals(accountNumber, that.accountNumber))
            return false;
        if (!Objects.equals(firstName, that.firstName)) return false;
        if (!Objects.equals(lastName, that.lastName)) return false;
        if (!Objects.equals(phone, that.phone)) return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(accountCards, that.accountCards)) return false;
        if (!Objects.equals(actual, that.actual)) return false;
        return Objects.equals(registered, that.registered);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (accountCards != null ? accountCards.hashCode() : 0);
        result = 31 * result + (actual != null ? actual.hashCode() : 0);
        result = 31 * result + (registered != null ? registered.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "accountNumber='" + accountNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", accountCards=" + accountCards +
                ", actual=" + actual +
                ", registered=" + registered +
                "} " + super.toString();
    }
}
