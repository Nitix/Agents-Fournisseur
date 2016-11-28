CREATE TABLE produit
(
    nomProduit VARCHAR(30),
    marqueProduit VARCHAR(30),
    descriptionProduit VARCHAR(250),
    prixProduit FLOAT(10),
	categorieProduit VARCHAR(100)
);

INSERT INTO produit (nomProduit, marqueProduit, descriptionProduit, prixProduit, categorieProduit)
VALUES
 
('Carotte','Bonduel','L�gume orange riche en prot�ines',1.00,'L�gume'),
('Poireau','G�ant Vert','L�gume vert plein de fibre',1.20,'L�gume'),
('Citrouille','Cascina Belvedere','L�gume orange parfait pour les f�tes d\'Halloween',1.95,'L�gume'),
('Brie','Pr�sident','Fromage de caract�re au lait de vache',16.50,'Produit laitier'),
('Comt�','Entremont','Fromage au lait cru',15.05,'Produit laitier'),
('Lait','Candia','Produit laitier semi-�cr�m�',0.70,'Produit laitier'),
('Bi�re','Chouffe','Boisson alcoolis�e � base d\'orge et d\'houblon',3.78,'Boisson'),
('Eau','Evian','Eau de source',0.57,'Boisson'),
('Coca','Coca cola','Boisson gazeuse sucr�e',1.10,'Boisson'),
('Javel','Monsieur Propre','Solution liquide oxydante d�sinfectante et d�colorante',7.92,'Produit entretien'),
('Lave vitre','K�rcher','Solution nettoyante sp�ciale fen�tre',3.71,'Produit entretien'),
('Liquide vaisselle','Paic','Liquide vaisselle super d�graissant',9.23,'Produit entretien'),
('Parfum','Channel','Fragrance d�licate et bois�e',56.48,'Cosm�tique'),
('Rouge � l�vre','Velvet','Produit de cosm�tique permettant de souligner les l�vres',34.50,'Cosm�tique'),
('Fond de teint','Maybelline','Produit cosm�tique permettant d\'unifier et de prot�ger l\'�piderme',28.90,'Cosm�tique'),
('Television HD','Samsung','Poste de t�l�vision permettant l\'emission et la reception de s�quences audiovisuelles',299.99,'High-tech'),
('Smartphone','Sony','GSM nouvelle g�n�ration',149.90,'High-tech'),
('Rasoir �l�ctrique','Phillips','Rasoir performant et plus ergonomique pour un meilleur rasage de la moustache',72.04,'High-tech'),
('Carotte','Grand Frais','L�gume orange riche en prot�ines',1.20,'L�gume'),
('Poireau','Cassegrin','L�gume vert plein de fibre',1.04,'L�gume'),
('Citrouille','Nos r�gions ont du talent','L�gume orange parfait pour les f�tes d\'Halloween',2.25,'L�gume'),
('Brie','Coeur de lion','Fromage de caract�re au lait de vache',14.99,'Produit laitier'),
('Comt�','Juraflore','Fromage au lait cru',12.50,'Produit laitier'),
('Lait','Lactel','Produit laitier semi-�cr�m�',0.98,'Produit laitier'),
('Bi�re','Kronenbourg','Boisson alcoolis�e � base d\'orge et d\'houblon',1.25,'Boisson'),
('Eau','Vittel','Eau de source',0.48,'Boisson'),
('Coca','Pepsi','Boisson gazeuse sucr�e',1.02,'Boisson'),
('Javel','Saint Marc','Solution liquide oxydante d�sinfectante et d�colorante',6.54,'Produit entretien'),
('Lave vitre','Ecovacs','Solution nettoyante sp�ciale fen�tre',2.60,'Produit entretien'),
('Liquide vaisselle','Mir','Liquide vaisselle super d�graissant',12.60,'Produit entretien'),
('Parfum','Dior','Fragrance d�licate et bois�e',46.38,'Cosm�tique'),
('Rouge � l�vre','L\'Oreal','Produit de cosm�tique permettant de souligner les l�vres',41.20,'Cosm�tique'),
('Fond de teint','Bourjois','Produit cosm�tique permettant d\'unifier et de prot�ger l\'�piderme',32.50,'Cosm�tique'),
('Television HD','LG','Poste de t�l�vision permettant l\'emission et la reception de s�quences audiovisuelles',199.90,'High-tech'),
('Smartphone','Apple','GSM nouvelle g�n�ration',780.00,'High-tech'),
('Rasoir �l�ctrique','Wilkinson','Rasoir performant et plus ergonomique pour un meilleur rasage de la moustache',68.22,'High-tech'),
('Petits pois','Bonduel','Le must des petits pois',1.60,'L�gume'),
('Pommes de terre','G�ant Vert','Des patates. toujours des patates',1.90,'L�gume'),
('Artichaux','Cascina Belvedere','Superbe pi�ce murit � souhait',1.30,'L�gume'),
('Petits pois','Cascina Belvedere','Le must des petits pois',1.30,'L�gume'),
('Pommes de terre','G�ant Vert','Des patates. toujours des patates',1.70,'L�gume'),
('Artichaux','Cascina Belvedere','Superbe pi�ce murit � souhait',2.00,'L�gume'),
('Tomates','Bonduel','Bien ronde et bien rouge',1.80,'L�gume'),
('Brocolis','G�ant Vert','Beau brocolis vert',1.60,'L�gume'),
('Haricots Verts','Cascina Belvedere','Haricots vert made in France',1.90,'L�gume'),
('Tomates','Cascina Belvedere','Bien ronde et bien rouge',1.60,'L�gume'),
('Brocolis','G�ant Vert','Beau brocolis vert',1.30,'L�gume'),
('Haricots Verts','Cascina Belvedere','Haricots vert made in France',1.80,'L�gume'),
('Choux fleur','G�ant Vert','Choux fleur de nos r�gions',1.90,'L�gume'),
('Choux de bruxelles','G�ant Vert','Choux belges plusieurs fois prim�s',1.20,'L�gume'),
('Flageolets','Cascina Belvedere','Flageolets bien ferme et de taille suffisante',1.10,'L�gume'),
('Choux fleur','Cascina Belvedere','Choux fleur de nos r�gions',1.90,'L�gume'),
('Choux de bruxelles','G�ant Vert','Choux belges plusieurs fois prim�s',1.90,'L�gume'),
('Flageolets','Cascina Belvedere','Flageolets bien ferme et de taille suffisante',1.60,'L�gume'),
('Emmentale','Pr�sident','Emmentale de montagne',2.50,'Produit laitier'),
('Brie','Entremont','Brie de nos r�gions',2.20,'Produit laitier'),
('Lait chocolat en brique','Candia','Id�al pour le go�ter',3.00,'Produit laitier'),
('Cr�me fraiche','Pr�sident','Id�ale pour la cuisine',2.50,'Produit laitier'),
('Fromage en portion','Entremont','Petites portions � emmener partout',3.00,'Produit laitier'),
('Gruy�re','Candia','Gruy�re � d�guster ou � ajouter dans vos plats',2.50,'Produit laitier'),
('Fromage � raclette','Pr�sident','Id�al pour une raclette en famille ou amis',2.70,'Produit laitier'),
('Fromage � tartiner','Entremont','Fromage � tartiner id�al pour l\'ap�ro',2.70,'Produit laitier'),
('Emmentale','Candia','Emmentale de montagne',2.20,'Produit laitier'),
('Brie','Entremont','Brie de nos r�gions',2.30,'Produit laitier'),
('Lait chocolat en brique','Candia','Id�al pour le go�ter',2.70,'Produit laitier'),
('Cr�me fraiche','Pr�sident','Id�ale pour la cuisine',3.00,'Produit laitier'),
('Fromage en portion','Entremont','Petites portions � emmener partout',2.40,'Produit laitier'),
('Gruy�re','Entremont','Gruy�re � d�guster ou � ajouter dans vos plats',2.20,'Produit laitier'),
('Fromage � raclette','Pr�sident','Id�al pour une raclette en famille ou amis',2.60,'Produit laitier'),
('Fromage � tartiner','Candia','Fromage � tartiner id�al pour l\'ap�ro',2.40,'Produit laitier'),
('Fondue','Candia','Fondue savoyarde',2.10,'Produit laitier'),
('Lait de ch�vre','Candia','Pour ceux qui aiment le ch�vre',3.00,'Produit laitier'),
('Fondue','Entremont','Fondue savoyarde',2.20,'Produit laitier'),
('Lait de ch�vre','Entremont','Pour ceux qui aiment le ch�vre',3.00,'Produit laitier'),
('Eau de vie','Arthurus','Le meilleur pour les fin de soir�e',2.30,'Boisson'),
('Mirabelle','Moureyus','Id�al pour donner un peu de go�t � vos repas',2.30,'Boisson'),
('Calva','Alcolus','Pour les hommes. les vrais',3.00,'Boisson'),
('Marc','PuRienBus','Marc m�rit en f�t pendant 7 mois',2.70,'Boisson'),
('Whisky','Jack Daniels','Le seul. l\'unique',2.80,'Boisson'),
('Vin','Veuve cliquot','Vin de grande qualit�',2.10,'Boisson'),
('Snaps','Deutshus','Artoung �a d�coiffe',2.70,'Boisson'),
('Jus de raisin','Materne','Jus de raisin 100% pur fruit',2.20,'Boisson'),
('Jus de pomme','Normalus','Pommes issue de l\'agriculture biologique',2.60,'Boisson'),
('Eau de vie','Asbach','Le meilleur pour les fin de soir�e',2.80,'Boisson'),
('Mirabelle','Adam','Id�al pour donner un peu de go�t � vos repas',3.00,'Boisson'),
('Calva','Deutshus','Pour les hommes. les vrais',2.10,'Boisson'),
('Marc','Alcolus','Marc m�rit en f�t pendant 7 mois',2.20,'Boisson'),
('Whisky','Shivas','Le seul. l\'unique',2.30,'Boisson'),
('Vin','Saint �milion','Vin de grande qualit�',3.00,'Boisson'),
('Snaps','Moureyus','Artoung �a d�coiffe',2.80,'Boisson'),
('Jus de raisin','Normalus','Jus de raisin 100% pur fruit',2.60,'Boisson'),
('Jus de pomme','Materne','Pommes issue de l\'agriculture biologique',3.00,'Boisson'),
('D�odorisant WC','Monsieur Propre','Id�al pour sentir bon o� �a ne sent pas bon',3.90,'Produit entretien'),
('Nettoyant WC','K�rcher','Nettoyage supr�me des zones de guerres',3.60,'Produit entretien'),
('Eponge','Paic','A utiliser avec de l\'huile de coude',3.60,'Produit entretien'),
('Nettoyant sol','Monsieur Propre','Mettre un bouchon dans 5 litres d\'eau',3.30,'Produit entretien'),
('D�boucheur canalisation','K�rcher','Id�al pour le transit de vos canalisations. Ne pas ing�rer',3.10,'Produit entretien'),
('Chiffon vitre','Paic','Ne fonctionne pas avec les �crans d\'ordinateur',3.50,'Produit entretien'),
('Bombe d�poussi�rante','Monsieur Propre','Id�al pour d�poussi�rer',3.10,'Produit entretien'),
('Balai','K�rcher','Ne sert pas � voler. mais � nettoyer',3.90,'Produit entretien'),
('Serpill�re','Paic','Serpill�re 100% pure coton',3.90,'Produit entretien'),
('D�odorisant WC','K�rcher','Id�al pour sentir bon o� �a ne sent pas bon',3.20,'Produit entretien'),
('Nettoyant WC','Paic','Nettoyage supr�me des zones de guerres',3.10,'Produit entretien'),
('Eponge','Monsieur Propre','A utiliser avec de l\'huile de coude',3.10,'Produit entretien'),
('Nettoyant sol','K�rcher','Mettre un bouchon dans 5 litres d\'eau',3.10,'Produit entretien'),
('D�boucheur canalisation','Paic','Id�al pour le transit de vos canalisations. Ne pas ing�rer',3.80,'Produit entretien'),
('Chiffon vitre','Monsieur Propre','Ne fonctionne pas avec les �crans d\'ordinateur',3.80,'Produit entretien'),
('Bombe d�poussi�rante','K�rcher','Id�al pour d�poussi�rer',4.00,'Produit entretien'),
('Balai','Paic','Ne sert pas � voler. mais � nettoyer',3.80,'Produit entretien'),
('Serpill�re','Monsieur Propre','Serpill�re 100% pure coton',3.80,'Produit entretien'),
('Poudre BB','Channel','Poudre pour jouer au ninja dans un nuage de fum�',60.90,'Cosm�tique'),
('Eye liner','Velvet','Pour des maquillages r�ussis',57.70,'Cosm�tique'),
('Mascarat waterproof','Maybelline','Ne r�siste pas � la piscine',73.90,'Cosm�tique'),
('Gloss','Channel','Pour augmenter la taille de vos l�vres',75.90,'Cosm�tique'),
('Phare � paupi�re','Velvet','Ne sert pas � attirer les bateaux',6.70,'Cosm�tique'),
('Pince � �piler','Maybelline','Sert �galement � tirer les vers du nez',176.60,'Cosm�tique'),
('Bande de cire','Channel','100% avec douleurs',119.10,'Cosm�tique'),
('Coton tige','Velvet','100% pure fibre de coton',123.90,'Cosm�tique'),
('Cr�me hydrante','Maybelline','Hydrate la peau en profondeur',173.50,'Cosm�tique'),
('Eau miscellaire','Channel','Id�al pour se d�maquiller',55.10,'Cosm�tique'),
('Anti cernes','Velvet','Pour les lendemains de soir�e',131.50,'Cosm�tique'),
('Poudre BB','Channel','Poudre pour jouer au ninja dans un nuage de fum�',48.20,'Cosm�tique'),
('Eye liner','Velvet','Pour des maquillages r�ussis',58.90,'Cosm�tique'),
('Mascarat waterproof','Maybelline','Ne r�siste pas � la piscine',178.20,'Cosm�tique'),
('Gloss','Channel','Pour augmenter la taille de vos l�vres',158.50,'Cosm�tique'),
('Phare � paupi�re','Velvet','Ne sert pas � attirer les bateaux',82.70,'Cosm�tique'),
('Pince � �piler','Maybelline','Sert �galement � tirer les vers du nez',199.70,'Cosm�tique'),
('Bande de cire','Channel','100% avec douleurs',69.60,'Cosm�tique'),
('Coton tige','Velvet','100% pure fibre de coton',105.20,'Cosm�tique'),
('Cr�me hydrante','Maybelline','Hydrate la peau en profondeur',114.70,'Cosm�tique'),
('Eau miscellaire','Channel','Id�al pour se d�maquiller',75.50,'Cosm�tique'),
('Anti cernes','Maybelline','Pour les lendemains de soir�e',78.30,'Cosm�tique'),
('Four � microonde','Samsung','Four � microonde haut de gamme',94.40,'High-tech'),
('Aspirateur','Sony','Aspirateur sans fil et sans sac',24.30,'High-tech'),
('Grille pain','Phillips','Id�al pour vos tartines du matin',80.70,'High-tech'),
('Ordinateur','Samsung','Ordinateur top moumoute plus',77.00,'High-tech'),
('Tablette','Sony','Tablette id�al pour vos envies de mobilit�',66.20,'High-tech'),
('Chaine HiFI','Phillips','Chaine HiFi avec son HD',122.30,'High-tech'),
('Radio','Samsung','Capter toutes vos radios pr�f�r�es',194.80,'High-tech'),
('Ecran PC','Sony','Ecran 15 pouces LED',37.10,'High-tech'),
('Four � microonde','Sony','Four � microonde haut de gamme',148.80,'High-tech'),
('Aspirateur','Phillips','Aspirateur sans fil et sans sac',61.10,'High-tech'),
('Grille pain','Samsung','Id�al pour vos tartines du matin',130.20,'High-tech'),
('Ordinateur','Sony','Ordinateur top moumoute plus',54.50,'High-tech'),
('Tablette','Phillips','Tablette id�al pour vos envies de mobilit�',23.50,'High-tech'),
('Chaine HiFI','Samsung','Chaine HiFi avec son HD',35.90,'High-tech'),
('Radio','Sony','Capter toutes vos radios pr�f�r�es',21.30,'High-tech'),
('Ecran PC','Phillips','Ecran 15 pouces LED',59.80,'High-tech'),
('Montre connect�e','Apple','Montre connect�e r�pondant � tous vos besoins',177.60,'High-tech')
