# TP Hibernate JPA + H2

Projet Maven avec Hibernate 5.6, JPA, base H2 en mémoire.

## Fonctionnalités implémentées
- Entité `Produit`
- Configuration `persistence.xml`
- Insertion, lecture, mise à jour prix, suppression
- Entité `Categorie` + relation @ManyToOne
- Recherche de produits par plage de prix

## Résultats attendus (captures console)

### Insertion et lecture initiale
<img width="1566" height="308" alt="Capture d&#39;écran 2026-02-17 193132" src="https://github.com/user-attachments/assets/cbc996f0-17d3-4a3a-aea9-ef149536fe4a" />
<img width="1764" height="801" alt="Capture d&#39;écran 2026-02-17 193318" src="https://github.com/user-attachments/assets/687803f4-1c55-49fa-bb17-f0d21057f210" />
<img width="1725" height="274" alt="Capture d&#39;écran 2026-02-17 193402" src="https://github.com/user-attachments/assets/d6843215-cb82-4752-82b3-5f086d2c173d" />


### Mise à jour du prix
<img width="1757" height="637" alt="Capture d&#39;écran 2026-02-17 221938" src="https://github.com/user-attachments/assets/ded95f87-bb19-4e06-bc59-50c6f95df2ad" />


### Suppression
<img width="1752" height="528" alt="Capture d&#39;écran 2026-02-17 222005" src="https://github.com/user-attachments/assets/7755e754-f577-4849-b498-c1ceb7b933c5" />


### Recherche par plage de prix
<img width="1735" height="465" alt="Capture d&#39;écran 2026-02-17 222032" src="https://github.com/user-attachments/assets/ccb0198d-8b99-44e0-8ec4-09743eb48dbe" />


## Technologies
- Java 8+
- Maven
- Hibernate 5.6.5.Final
- H2 Database (in-memory)
- SLF4J + slf4j-simple
