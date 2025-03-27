package com.example.services;

import com.example.viewmodels.DepositoViewModel;
import com.example.viewmodels.SaqueViewModel;
import com.example.models.ContaCorrente;
import com.example.repositories.ContaCorrenteRepository;
import com.example.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContaCorrenteService {
    private final ContaCorrenteRepository contaCorrenteRepository;

    @Autowired
    public ContaCorrenteService(ContaCorrenteRepository contaCorrenteRepository) {
        this.contaCorrenteRepository = contaCorrenteRepository;
    }

    public ResponseEntity<ContaCorrente> createContaCorrente(ContaCorrente contaCorrente) {
        contaCorrenteRepository.save(contaCorrente);

        return ResponseEntity.status(HttpStatus.CREATED).body(contaCorrente);
    }

    public ResponseEntity<ContaCorrente> getContaCorrenteById(Long id) {
        if (!contaCorrenteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(contaCorrenteRepository.findById(id).get());
    }

    public ResponseEntity<GenericResponse> realizarSaque(SaqueViewModel saqueViewModel) {
        if (!contaCorrenteRepository.existsById(saqueViewModel.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse("Conta não encontrada"));
        }

        ContaCorrente conta = contaCorrenteRepository.findById(saqueViewModel.getId()).get();

        if (conta.getSaldo() < saqueViewModel.getQuantidade()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse("Saldo insuficiente"));
        }

        conta.setSaldo(conta.getSaldo() - saqueViewModel.getQuantidade());

        contaCorrenteRepository.save(conta);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public ResponseEntity<GenericResponse> realizarDeposito(DepositoViewModel depositoViewModel) {
        if (!contaCorrenteRepository.existsById(depositoViewModel.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse("Conta não encontrada"));
        }

        ContaCorrente conta = contaCorrenteRepository.findById(depositoViewModel.getId()).get();

        conta.setSaldo(conta.getSaldo() + depositoViewModel.getQuantidade());

        contaCorrenteRepository.save(conta);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
