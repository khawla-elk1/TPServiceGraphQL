package org.example.tpservicegraphql.repositories;


import org.example.tpservicegraphql.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité Compte.
 * Étend JpaRepository pour les opérations CRUD de base.
 */
@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

    /**
     * Calcule la somme de tous les soldes des comptes.
     * Cette méthode est utilisée dans la Query totalSolde() du contrôleur.
     *
     * @return La somme totale des soldes (double).
     */
    @Query("SELECT SUM(c.solde) FROM Compte c")
    double sumSoldes();
}
