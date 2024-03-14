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


CREATE TABLE Miel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    description VARCHAR(3000),
    type_miel VARCHAR(50),
    prix FLOAT,
    stock INT,
    commande_id INT,
    FOREIGN KEY (commande_id) REFERENCES Commande(id)  
);

CREATE TABLE Commande (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dateCommande DATE,
    statut VARCHAR(50),
    client_id INT,
    FOREIGN KEY (client_id) REFERENCES Utilisateur(id)
);
