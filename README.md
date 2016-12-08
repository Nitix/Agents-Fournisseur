# Projet Agents
## Fournisseur

### Développeurs :
- Alexandre GINGEMBRE
- Mathieu MOUROT
- Arthur MOUREY
- Guillaume PIERSON



### BDD :
   - MySQL / MariaDB

### Structures :
   - Deux agents :
      - Un agent producteur : produit les produits selon le besoin
      - Un agent magasinier : gère les stocke et vend les produits

Deux instances du couple ci-dessus.

### TODO
- Deux champs dans la BDD : un pour le nom et l'autre pour la description
- Catalogue produits à faire en premier pour les autres groupes

La suite sera ajoutée lors du développement...

### Installation
- ```git clone git@github.com:Nitix/Agents-Fournisseur.git```
- ```mvn clean install```
- ```java jade.Boot -gui -port 12344 -agents producteur:fr.miage.agents.fournisseur.producteur.AgentProducteur;database:fr.miage.agents.fournisseur.database.AgentDatabase;fournisseur:fr.miage.agents.fournisseur.fournisseur.AgentFournisseur;magasinier:fr.miage.agents.fournisseur.magasinier.AgentMagasinier; ```
