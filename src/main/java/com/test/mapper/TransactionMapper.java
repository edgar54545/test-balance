package com.test.mapper;

import com.test.dto.TransactionDto;
import com.test.dto.TransactionRequest;
import com.test.entity.Balance;
import com.test.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = Balance.class)
public interface TransactionMapper {

    TransactionDto toDto(Transaction transaction);

    @Mapping(target = "balance", expression = "java(Balance.builder().id(balanceId).build())")
    Transaction toEntity(TransactionRequest transactionRequest, UUID balanceId);
}
