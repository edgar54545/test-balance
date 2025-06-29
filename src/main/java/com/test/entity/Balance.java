package com.test.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "balance")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private BigDecimal amount;

    @OneToMany(mappedBy = "balance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    public void calculateBalance(Transaction transaction) {
        BigDecimal usdAmount = transaction.getAmount()
                .multiply(BigDecimal.valueOf(transaction.getCurrency().getExchangeRate()));
        amount = amount.add(transaction.getType() == TransactionType.DEPOSIT ? usdAmount : usdAmount.negate());
    }
}
