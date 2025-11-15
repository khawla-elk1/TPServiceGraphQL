package org.example.tpservicegraphql.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    private double montant;

    // Type de transaction : DEBIT ou RETRAIT
    private String type;

    // Définition de la relation Many-to-One : Plusieurs transactions peuvent appartenir à UN seul compte
    @ManyToOne
    @JoinColumn(name = "compte_id") // Clé étrangère dans la table Transaction
    private Compte compte;

    @Enumerated(EnumType.STRING) // Assure que l'Enum est stocké en tant que String dans la BDD
    private TypeTransaction type1;
}