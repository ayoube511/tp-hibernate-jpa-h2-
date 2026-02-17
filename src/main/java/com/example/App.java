package com.example;

import com.example.model.Produit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class App {

    public static void main(String[] args) {
        // Création de l'EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");

        // Insertion de produits
        insererProduits(emf);
        lireProduits(emf);
        mettreAJourPrix(emf, 1L, new BigDecimal("1099.99"));
        lireProduits(emf);// Exemple : Laptop passe à 1099.99
        supprimerProduit(emf, 3L);  // Supprime la Tablette (ID=3)
        lireProduits(emf);  // Relire pour voir qu'il n'y est plus
        rechercherProduitsParPrix(emf, new BigDecimal("400"), new BigDecimal("800"));
    }
        private static void insererProduits (EntityManagerFactory emf){
            EntityManager em = emf.createEntityManager();
            try {
                em.getTransaction().begin();

                // Création de quelques produits
                Produit p1 = new Produit("Laptop", new BigDecimal("999.99"));
                Produit p2 = new Produit("Smartphone", new BigDecimal("499.99"));
                Produit p3 = new Produit("Tablette", new BigDecimal("299.99"));

                // Persistance des produits
                em.persist(p1);
                em.persist(p2);
                em.persist(p3);

                em.getTransaction().commit();
                System.out.println("Produits insérés avec succès !");
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                e.printStackTrace();
            } finally {
                em.close();
            }
        }

    private static void lireProduits(EntityManagerFactory emf){
            EntityManager em = emf.createEntityManager();
            try {
                // Requête JPQL pour récupérer tous les produits
                List<Produit> produits = em.createQuery("SELECT p FROM Produit p", Produit.class)
                        .getResultList();

                System.out.println("\nListe des produits :");
                for (Produit produit : produits) {
                    System.out.println(produit);
                }

            } finally {
                em.close();
            }
        }

    //mettre à jours un produit
    private static void mettreAJourPrix(EntityManagerFactory emf, Long idProduit, BigDecimal nouveauPrix) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Produit produit = em.find(Produit.class, idProduit);
            if (produit != null) {
                produit.setPrix(nouveauPrix);
                // Pas besoin de em.merge() car l'entité est déjà managed (retrouvée via find dans une transaction)
                em.getTransaction().commit();
                System.out.println("Prix mis à jour pour le produit ID=" + idProduit + " → " + nouveauPrix);
            } else {
                System.out.println("Produit ID=" + idProduit + " non trouvé");
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    //supprimer produit
    private static void supprimerProduit(EntityManagerFactory emf, Long idProduit) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Produit produit = em.find(Produit.class, idProduit);
            if (produit != null) {
                em.remove(produit);  // Marque pour suppression
                em.getTransaction().commit();
                System.out.println("Produit ID=" + idProduit + " supprimé avec succès");
            } else {
                System.out.println("Produit ID=" + idProduit + " non trouvé");
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    //recherch de produits par plage
    private static void rechercherProduitsParPrix(EntityManagerFactory emf, BigDecimal prixMin, BigDecimal prixMax) {
        EntityManager em = emf.createEntityManager();
        try {
            List<Produit> produits = em.createQuery(
                            "SELECT p FROM Produit p WHERE p.prix BETWEEN :min AND :max ORDER BY p.prix ASC",
                            Produit.class)
                    .setParameter("min", prixMin)
                    .setParameter("max", prixMax)
                    .getResultList();

            System.out.println("\nProduits entre " + prixMin + " et " + prixMax + " :");
            if (produits.isEmpty()) {
                System.out.println("Aucun produit trouvé dans cette plage.");
            } else {
                for (Produit p : produits) {
                    System.out.println(p);
                }
            }
        } finally {
            em.close();
        }

    }

}