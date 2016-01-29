CREATE TABLE Utilisateur (
	idUtilisateur varchar(50) PRIMARY KEY, 
	pseudo varchar (50),
	mdp varchar (20)
	);

CREATE TABLE Agent (
	idAgent varchar (50) PRIMARY KEY ,
	prenomAgent varchar (20) NOT NULL,
	nomAgent varchar(20) NOT NULL,
	pseudo varchar (20) NOT NULL,
	mdp varchar(20) NOT NULL
);


CREATE TABLE Client (
	idClient varchar (50) PRIMARY KEY ,
	prenom varchar (20) NOT NULL,
	nom varchar(20) NOT NULL,
	dateNaissance TEXT,
	lieuNaissance varchar (50),
	PermisConduire varchar (50) 
	
);

CREATE TABLE Contrat (

	idClient varchar(50),
	idAgent varchar(50),
	idvoiture varchar (50),
	dateDebut TEXT,
	dateFin TEXT,
	kmDebut int,
	kmFin int,
	prixFinale REAL,
	penalite REAL,
	idContrat varchar (50) PRIMARY KEY
);

CREATE TABLE PermisConduire(
	num int,
	date TEXT,
	lieu varchar(50),
	typePermis varchar(50),
	idPermis varchar(50) PRIMARY KEY
);

CREATE TABLE Voiture(
	idVoiture varchar(50) PRIMARY KEY,
	plaqueImm varchar (20),
	marque varchar (20),
	km int,
	couleur varchar (20),
	prixJour REAL

);

	