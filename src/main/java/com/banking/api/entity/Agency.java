package com.banking.api.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Audited;

import java.util.Objects;

@Entity
@Table(name = "agency_tb")
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 4)
    private String code;
    @Column(length = 150, nullable = false)
    private String name;
    @Column(length = 20, nullable = false)
    private String city;
    @Column(length = 20, nullable = false)
    private String state;

    public Agency() {
    }

    public Agency(Long id, String code, String name, String city, String state) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.city = city;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return  true;
        if (!(o instanceof Agency agency)) return false;
        return Objects.equals(id, agency.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
