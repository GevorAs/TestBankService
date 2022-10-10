package com.agr.bankservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity extends BaseEntity {

    @Column(name = "countryCode")
    private String countryCode;

    @Column(name = "building")
    private String building;

    @Column(name = "flat")
    private String flat;

    @Column(name = "post")
    private String post;

    @Column(name = "region")
    private String region;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "county")
    private String county;

    @OneToOne(mappedBy = "actual")
    private AccountEntity actual;
    @OneToOne(mappedBy = "registered")
    private AccountEntity registered;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressEntity)) return false;
        if (!super.equals(o)) return false;

        AddressEntity that = (AddressEntity) o;

        if (!Objects.equals(countryCode, that.countryCode)) return false;
        if (!Objects.equals(building, that.building)) return false;
        if (!Objects.equals(flat, that.flat)) return false;
        if (!Objects.equals(post, that.post)) return false;
        if (!Objects.equals(region, that.region)) return false;
        if (!Objects.equals(street, that.street)) return false;
        if (!Objects.equals(city, that.city)) return false;
        if (!Objects.equals(county, that.county)) return false;
        if (!Objects.equals(actual, that.actual)) return false;
        return Objects.equals(registered, that.registered);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (building != null ? building.hashCode() : 0);
        result = 31 * result + (flat != null ? flat.hashCode() : 0);
        result = 31 * result + (post != null ? post.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (county != null ? county.hashCode() : 0);
        result = 31 * result + (actual != null ? actual.hashCode() : 0);
        result = 31 * result + (registered != null ? registered.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "countryCode='" + countryCode + '\'' +
                ", building='" + building + '\'' +
                ", flat='" + flat + '\'' +
                ", post='" + post + '\'' +
                ", region='" + region + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", actual=" + actual +
                ", registered=" + registered +
                "} " + super.toString();
    }
}
