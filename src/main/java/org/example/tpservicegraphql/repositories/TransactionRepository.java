package org.example.tpservicegraphql.repositories;


import  org.example.tpservicegraphql.entities.Transaction ; // Assurez-vous d'importer l'entité Transaction
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Spring Data JPA fournira automatiquement l'implémentation de .save(transaction)
}