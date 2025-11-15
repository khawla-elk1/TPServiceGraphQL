package org.example.tpservicegraphql.controllers;

import lombok.AllArgsConstructor;
import org.example.tpservicegraphql.entities.Compte;
import org.example.tpservicegraphql.entities.Transaction;
import org.example.tpservicegraphql.entities.TypeTransaction; // Pour la conversion Enum
import org.example.tpservicegraphql.repositories.CompteRepository;
import org.example.tpservicegraphql.repositories.TransactionRepository;
import org.example.tpservicegraphql.requests.TransactionRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class CompteControllerGraphQL {

    // Injection des deux repositories
    private CompteRepository compteRepository;
    private TransactionRepository transactionRepository;

    @QueryMapping
    public List<Compte> allComptes(){
        return compteRepository.findAll();
    }

    @QueryMapping
    public Compte compteById(@Argument Long id){
        Compte compte = compteRepository.findById(id).orElse(null);
        if(compte == null) throw new RuntimeException(String.format("Compte %s not found", id));
        else return compte;
    }

    @MutationMapping
    public Compte saveCompte(@Argument Compte compte){
        return compteRepository.save(compte);
    }

    @QueryMapping
    public Map<String, Object> totalSolde() {
        long count = compteRepository.count();
        double sum = compteRepository.sumSoldes();
        double average = count > 0 ? sum / count : 0;

        return Map.of(
                "count", count,
                "sum", sum,
                "average", average
        );
    }

    @MutationMapping
    public Transaction addTransaction(@Argument TransactionRequest transactionRequest) {

        // 1. Récupérer le compte
        Compte compte = compteRepository.findById(transactionRequest.getCompteId())
                .orElseThrow(() -> new RuntimeException("Compte not found"));

        // 2. Mapper et convertir
        Transaction transaction = new Transaction();
        transaction.setMontant(transactionRequest.getMontant());
        transaction.setDate(transactionRequest.getDate());

        // Convertir la chaîne (String) de la requête en Enum (TypeTransaction)
        transaction.setType(TypeTransaction.valueOf(transactionRequest.getType()));

        transaction.setCompte(compte);

        // 3. Sauvegarder
        transactionRepository.save(transaction);

        return transaction;
    }
}