package com.banking.api.dto.response;

public class AgencySummaryResponse {
    private Long id;
    private String name;

    public AgencySummaryResponse() {
    }

    public AgencySummaryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
