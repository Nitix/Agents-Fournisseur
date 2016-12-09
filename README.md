# Projet Agents
## Fournisseur

### Développeurs :
- Alexandre GINGEMBRE
- Mathieu MOUROT
- Arthur MOUREY
- Guillaume PIERSON



### BDD :
   - H2 

### Structures :
   - Deux agents :
      - Un agent producteur : produit les produits selon le besoin
      - Un agent magasinier : gère les stocke et vend les produits


### Installation
- ```git clone git@github.com:Nitix/Agents-Fournisseur.git```
- ```cd Agents-Fournisseur/```
- ```mvn clean install```
- ```java jade.Boot -gui -port 12344 -agents producteur:fr.miage.agents.fournisseur.producteur.AgentProducteur;database:fr.miage.agents.fournisseur.database.AgentDatabase;fournisseur:fr.miage.agents.fournisseur.fournisseur.AgentFournisseur;magasinier:fr.miage.agents.fournisseur.magasinier.AgentMagasinier; ```
