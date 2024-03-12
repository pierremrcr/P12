# Guide de déploiement pour l'Application Spring Boot

Ce guide explique comment déployer et configurer votre application Spring Boot, qui se compose d'une API, d'une application web Spring Boot , d'une base de données H2, et d'un script de jeu de données pour initialiser la base.

## Prérequis

- JDK 8 ou plus récent
- Maven 3.6 ou plus récent
- Git (optionnel, pour cloner le projet)

## Installation

### 1. Cloner le projet 

Commencez par cloner le projet.
ouvrez un terminal puis executer les commandes suivantes :
git clone https://github.com/pierremrcr/P12.git
cd P12

## Construire l'application

Dans le répertoire racine de chaque application (API et application web), exécutez la commande suivante pour construire l'application :

mvn clean install

Cela générera un fichier .jar dans le dossier target de chaque projet

## Démarrer l'API

Naviguez dans le répertoire de l'API et démarrez l'application :

java -jar target/projet12-0.0.1-SNAPSHOT.jar

## Démarrer l'application web

Ouvrez un nouveau terminal, naviguez dans le répertoire de l'application web, et démarrez l'application :

java -jar target/webapp-0.0.1-SNAPSHOT.jar

## Configuration de la base de données

L'application utilise une base de données H2. La configuration par défaut se trouve dans le fichier application.properties du projet api.

Un script SQL est fourni pour initialiser la base de données avec des données de démonstration. 
Pour exécuter ce script, accédez à la console H2 de votre application :

http://localhost:9000/h2

puis renseignez les champs suivants :

Driver Class : org.h2.Driver
JDBC URL : jdbc:h2:mem:testdb2
User Name : sa
Password : password

puis cliquez sur l'onglet "Connect".

Dans la console de l'interface, collez le script se trouvant dans le fichier donnees.sql puis cliquez sur "Run" pour executer le script.

## Accès à l'application
L'application web après démarrage de l'API et de l'application web est accessible à l'url suivante :
http://localhost:9001
