package org.example.tpservicegraphql.requests;


import lombok.Data;
import java.util.Date;

// Cette classe est utilisée pour recevoir les arguments de la mutation GraphQL
@Data // Lombok génère les getters et setters
public class TransactionRequest {

    // L'ID du compte sur lequel effectuer la transaction
    private Long compteId;

    // Le montant de la transaction (doit correspondre au type Float dans le schéma GraphQL)
    private double montant;

    // Le type de transaction (ex: DEBIT, RETRAIT)
    private String type;

    // La date de la transaction
    private Date date;
}