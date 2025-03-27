package com.example.controllers;

import com.example.viewmodels.DepositoViewModel;
import com.example.viewmodels.SaqueViewModel;
import com.example.models.ContaPoupanca;
import com.example.response.GenericResponse;
import com.example.services.ContaPoupancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/contapoupanca")
public class ContaPoupancaController {
    private final ContaPoupancaService contaPoupancaService;

    @Autowired
    public ContaPoupancaController(ContaPoupancaService contaPoupancaService) {
        this.contaPoupancaService = contaPoupancaService;
    }

    @GetMapping(path="{id}")
    public ResponseEntity<ContaPoupanca> getContaPoupanca(@PathVariable long id) {
        return contaPoupancaService.getContaPoupancaById(id);
    }

    @PostMapping
    public ResponseEntity<ContaPoupanca> cadastrarContaPoupanca(@RequestBody ContaPoupanca contaPoupanca) {
        return contaPoupancaService.createContaPoupanca(contaPoupanca);
    }

    @PostMapping(path = "saque")
    public ResponseEntity<GenericResponse> saque(@RequestBody SaqueViewModel saqueViewModel) {
        return contaPoupancaService.realizarSaque(saqueViewModel);
    }

    @PostMapping(path = "deposito")
    public ResponseEntity<GenericResponse> deposito(@RequestBody DepositoViewModel depositoViewModel) {
        return contaPoupancaService.realizarDeposito(depositoViewModel);
    }
}