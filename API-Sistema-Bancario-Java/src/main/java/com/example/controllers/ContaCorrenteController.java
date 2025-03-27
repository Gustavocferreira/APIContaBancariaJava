package com.example.controllers;

import com.example.viewmodels.DepositoViewModel;
import com.example.viewmodels.SaqueViewModel;
import com.example.models.ContaCorrente;
import com.example.response.GenericResponse;
import com.example.services.ContaCorrenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/contacorrente")
public class ContaCorrenteController {
    private final ContaCorrenteService contaCorrenteService;

    @Autowired
    public ContaCorrenteController(ContaCorrenteService contaCorrenteService) {
        this.contaCorrenteService = contaCorrenteService;
    }

    @GetMapping
    public ResponseEntity<String> getTeste() {
        return ResponseEntity.ok("Rota funcionando!");
    }

    @GetMapping(path="{id}")
    public ResponseEntity<ContaCorrente> getContaCorrente(@PathVariable long id) {
        return contaCorrenteService.getContaCorrenteById(id);
    }

    @PostMapping
    public ResponseEntity<ContaCorrente> cadastrarContaCorrente(@RequestBody ContaCorrente contaCorrente) {
        return contaCorrenteService.createContaCorrente(contaCorrente);
    }

    @PostMapping(path = "saque")
    public ResponseEntity<GenericResponse> saque(@RequestBody SaqueViewModel saqueViewModel) {
        return contaCorrenteService.realizarSaque(saqueViewModel);
    }

    @PostMapping(path = "deposito")
    public ResponseEntity<GenericResponse> deposito(@RequestBody DepositoViewModel depositoViewModel) {
        return contaCorrenteService.realizarDeposito(depositoViewModel);
    }
}