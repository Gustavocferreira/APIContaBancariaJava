package com.example.viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaqueViewModel {
    private float quantidade;
    private long id;

    public float getQuantidade() {  // Confirme que este método existe
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public long getId() { // Definindo manualmente
        return id;
    }
}