package com.test.mapper;

import com.test.dto.BalanceDto;
import com.test.entity.Balance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BalanceMapper {

    BalanceDto toDto(Balance balance);
}
