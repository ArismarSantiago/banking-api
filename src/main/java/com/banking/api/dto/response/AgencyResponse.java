package com.banking.api.dto.response;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;


public class AgencyResponse extends RepresentationModel<AgencyResponse> {

    private Long id;
    private String code;
    private String name;
    private String city;
    private String state;

    public AgencyResponse() {
    }

    private List<AccountResponse> accounts;

    public AgencyResponse(Long id, String code, String name, String city, String state) {
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

    public List<AccountResponse> getAccounts() {
        return accounts;
    }

}
