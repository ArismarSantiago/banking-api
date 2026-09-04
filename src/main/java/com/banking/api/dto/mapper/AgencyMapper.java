package com.banking.api.dto.mapper;

import com.banking.api.dto.response.AgencyResponse;
import com.banking.api.entity.Agency;

public class AgencyMapper {


    // por enquanto essa agencia so vai conter o response
    //pois quero que a agencia seja padronizada, sem opção de request ou update

    public static AgencyResponse toResponse(Agency entity){
        AgencyResponse response = new AgencyResponse();

        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setCity(entity.getCity());
        response.setState(entity.getState());
        return response;
    }

}
