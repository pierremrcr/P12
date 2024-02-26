CREATE TABLE Adresse (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rue VARCHAR(255),
    ville VARCHAR(255),
    code_postal VARCHAR(10),
    pays VARCHAR(255)
);

CREATE TABLE Utilisateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    adresse_mail VARCHAR(255),
    mot_de_passe VARCHAR(255),
    adresse_id INT,
    FOREIGN KEY (adresse_id) REFERENCES Adresse(id)
);

CREATE TABLE Producteur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    nom_entreprise VARCHAR(255),
    telephone VARCHAR(20),
    email VARCHAR(255),
    adresse_id INT,
    FOREIGN KEY (adresse_id) REFERENCES Adresse(id)
);

CREATE TABLE Miel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    description VARCHAR(3000),
    type_miel VARCHAR(50),
    prix FLOAT,
    stock INT,
    producteur_id INT,
    FOREIGN KEY (producteur_id) REFERENCES Producteur(id)
);

CREATE TABLE Commande (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dateCommande DATE,
    statut VARCHAR(50),
    client_id INT,
    FOREIGN KEY (client_id) REFERENCES Utilisateur(id)
);

CREATE TABLE Detail_Commande (
    id INT AUTO_INCREMENT PRIMARY KEY,
    miel_id INT,
    commande_id INT,
    quantite INT,
    FOREIGN KEY (miel_id) REFERENCES Miel(id),
    FOREIGN KEY (commande_id) REFERENCES Commande(id)
);
