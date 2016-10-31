#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Produit
#------------------------------------------------------------

CREATE TABLE Produit(
        idProduit          int (11) Auto_increment  NOT NULL ,
        nomProduit         Varchar (50) ,
        descriptionProduit Text ,
        prixProduit        Float ,
        quantiteProduit    Int NOT NULL ,
        idCategorie        Int ,
        PRIMARY KEY (idProduit )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Categorie
#------------------------------------------------------------

CREATE TABLE Categorie(
        idCategorie  int (11) Auto_increment  NOT NULL ,
        nomCategorie Varchar (50) ,
        PRIMARY KEY (idCategorie )
)ENGINE=InnoDB;

ALTER TABLE Produit ADD CONSTRAINT FK_Produit_idCategorie FOREIGN KEY (idCategorie) REFERENCES Categorie(idCategorie);
