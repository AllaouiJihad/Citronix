# Citronix - Lemon Farm Management System 🍋

## Présentation du Projet

Citronix est une application de gestion complète pour les exploitations de citrons, conçue pour aider les fermiers à suivre et optimiser leur production agricole.

## Fonctionnalités Principales

### 1. Gestion des Fermes
- Création, modification et consultation des informations de fermes
- Recherche multicritère avancée
- Support pour gérer plusieurs attributs (nom, localisation, superficie)

### 2. Gestion des Champs
- Association des champs aux fermes
- Validation stricte des superficies
- Contraintes :
  * Superficie minimale : 0.1 hectare
  * Superficie maximale : 50% de la ferme
  * Maximum 10 champs par ferme

### 3. Gestion des Arbres
- Suivi détaillé par arbre (date de plantation, âge)
- Calcul automatique de la productivité selon l'âge :
  * Jeune arbre (< 3 ans) : 2,5 kg/saison
  * Arbre mature (3-10 ans) : 12 kg/saison
  * Arbre vieux (> 10 ans) : 20 kg/saison
- Limite de productivité : 20 ans

### 4. Gestion des Récoltes
- Suivi saisonnier (hiver, printemps, été, automne)
- Une récolte par saison
- Enregistrement des détails par arbre

### 5. Gestion des Ventes
- Enregistrement complet des ventes
- Calcul automatique des revenus

## Contraintes Techniques

### Architecture
- Spring Boot
- Architecture en couches (Controller, Service, Repository, Entity)
- Validation des données avec annotations Spring

### Outils et Technologies
- Framework : Spring Boot
- Mapping : MapStruct
- Tests : JUnit, Mockito
- Développement : Lombok, Builder Pattern

## Prérequis

- JDK 17+
- Maven ou Gradle
- Base de données PostgreSQL/MySQL

## Installation

1. Cloner le repository
```bash
git clone https://github.com/AllaouiJihad/Citronix.git
```

2. Configurer la base de données
3. Installer les dépendances
```bash
mvn clean install
```

4. Lancer l'application
```bash
mvn spring-boot:run
```

## Tests

Exécuter les tests unitaires :
```bash
mvn test
```

