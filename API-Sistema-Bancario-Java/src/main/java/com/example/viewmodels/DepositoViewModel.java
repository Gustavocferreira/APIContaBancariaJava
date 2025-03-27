package com.example.viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositoViewModel {
    private float quantidade;
    private long id;

    public long getId() {
        return id;
    }

    public float getQuantidade() {
        return quantidade;
    }
}