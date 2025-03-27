package com.example.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public class ContaCorrente extends Conta {

    public ContaCorrente() {}

    public ContaCorrente(String titular, double saldo, int numeroConta) {
        super(titular, saldo, numeroConta);  // Passa os parâmetros para o construtor da superclasse
    }
}

