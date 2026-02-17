package com.example.model;

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Produit {

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private BigDecimal prix;

    // Constructeur par défaut requis par JPA
    public Produit() {
    }

    public Produit(String nom, BigDecimal prix) {
        this.nom = nom;
        this.prix = prix;
    }

    // Getters et Setters

    public Categorie getCategorie(){
        return categorie;
    }
    public void setCategorie(Categorie categorie){
        this.categorie = categorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ",categorie="+(categorie != null ?categorie.getNom() : "aucune")+'}';
    }
}
