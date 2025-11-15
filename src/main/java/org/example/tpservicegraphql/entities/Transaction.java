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

    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    // Relation Many-to-One
    @ManyToOne
    @JoinColumn(name = "compte_id")
    private Compte compte;
}