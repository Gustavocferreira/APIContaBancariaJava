package com.example.services;

import com.example.viewmodels.DepositoViewModel;
import com.example.viewmodels.SaqueViewModel;
import com.example.models.ContaPoupanca;
import com.example.repositories.ContaPoupancaRepository;
import com.example.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContaPoupancaService {
    private final ContaPoupancaRepository contaPoupancaRepository;

    @Autowired
    public ContaPoupancaService(ContaPoupancaRepository contaPoupancaRepository) {
        this.contaPoupancaRepository = contaPoupancaRepository;
    }

    public ResponseEntity<ContaPoupanca> createContaPoupanca(ContaPoupanca contaPoupanca) {
        contaPoupancaRepository.save(contaPoupanca);

        return ResponseEntity.status(HttpStatus.CREATED).body(contaPoupanca);
    }

    public ResponseEntity<ContaPoupanca> getContaPoupancaById(Long id) {
        if (!contaPoupancaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(contaPoupancaRepository.findById(id).get());
    }

    public ResponseEntity<GenericResponse> realizarSaque(SaqueViewModel saqueViewModel) {
        if (!contaPoupancaRepository.existsById(saqueViewModel.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse("Conta não encontrada"));
        }

        ContaPoupanca conta = contaPoupancaRepository.findById(saqueViewModel.getId()).get();

        if (conta.getSaldo() < saqueViewModel.getQuantidade()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse("Saldo insuficiente"));
        }

        conta.setSaldo(conta.getSaldo() - saqueViewModel.getQuantidade());

        contaPoupancaRepository.save(conta);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public ResponseEntity<GenericResponse> realizarDeposito(DepositoViewModel depositoViewModel) {
        if (!contaPoupancaRepository.existsById(depositoViewModel.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse("Conta não encontrada"));
        }

        ContaPoupanca conta = contaPoupancaRepository.findById(depositoViewModel.getId()).get();

        conta.setSaldo(conta.getSaldo() + depositoViewModel.getQuantidade());

        contaPoupancaRepository.save(conta);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
