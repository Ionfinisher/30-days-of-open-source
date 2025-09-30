# Quiz Interactif Java

Une application console interactive de quiz développée en Java. L'application permet de poser des questions aux utilisateurs, de saisir leurs réponses, de vérifier les résultats et d'afficher le score final. Elle offre également des fonctionnalités avancées comme la gestion des questions par un administrateur.

## 🎯 Fonctionnalités

Vous trouverez les issues relatives à ces fontionnalités avec le label `java`

### Moteur de Quiz de Base

- Pose des questions stockées dans un fichier
- Saisie des réponses utilisateur
- Vérification automatique des réponses
- Affichage du score final

### Chargement / Sauvegarde des Questions

- Lecture des questions depuis un fichier local (JSON / CSV)
- Sauvegarde de nouvelles questions

### Questions à Choix Multiples

- Support des questions avec plusieurs choix
- Ne se limite pas aux questions vrai/faux
- Interface intuitive pour la sélection

### Mode Administrateur

- **Ajouter** de nouvelles questions
- **Modifier** des questions existantes
- **Supprimer** des questions obsolètes
- Interface dédiée pour la gestion du contenu

### Fonctionnalités Avancées

- **Timer** : Limite de temps pour chaque question
- **Système de scores** : Sauvegarde des meilleurs scores des joueurs localement
- **Historique** : Suivi des performances des utilisateurs

## 🚀 Installation et Utilisation

### Prérequis

- Java JDK 8 ou supérieur
- Un IDE Java (VSCode, IntelliJ IDEA, Eclipse) ou un terminal

### Compilation et Exécution

```bash
# Naviguer vers le dossier du projet
cd src/java/quiz

# Executer directement depuis votre IDE Java

# Ou

# Compiler le projet
javac -d bin src/*.java

# Exécuter l'application
java -cp bin App
```

## 📁 Structure du Projet

```
quiz/
├── src/
│   └── App.java          # Classe principale
├── lib/                  # Bibliothèques externes (si nécessaire)
├── data/                 # Fichiers de questions (JSON/CSV)
├── scores/               # Sauvegarde des scores
└── README.md            # Ce fichier
```

## 🎮 Comment Jouer

1. **Démarrer le quiz** : Lancez l'application
2. **Répondre aux questions** : Tapez votre réponse ou sélectionnez un choix
3. **Gérer le temps** : Répondez avant la fin du timer
4. **Consulter le score** : Votre performance s'affiche à la fin
5. **Mode Admin** : Accédez au mode administrateur pour gérer les questions

## 🔧 Mode Administrateur

Le mode administrateur permet de :

- Créer de nouvelles questions avec différents types de réponses
- Modifier le contenu des questions existantes
- Supprimer les questions obsolètes ou incorrectes

## 🤝 Contribution

Les contributions sont les bienvenues ! N'hésitez pas à participer

## 📄 Licence

Ce projet est sous licence [Apache 2.0 license](https://github.com/Ionfinisher/30-days-of-open-source/blob/main/LICENSE). Voir le fichier `LICENSE` pour plus de détails.

## 🎓 À Propos

Ce projet fait partie du défi "30 Days Of Open Source" et vise à créer une application de quiz complète et interactive en Java.
