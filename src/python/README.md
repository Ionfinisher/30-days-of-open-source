# Snake CLI – Jeu en terminal (Python)

Un jeu Snake en mode terminal, programmé en Python, où le serpent grandit lorsqu’il mange de la nourriture, et la partie se termine s’il entre en collision avec les murs ou lui-même. Le but : obtenir le plus haut score possible.

## 🎯 Fonctionnalités envisagées

Les issues liées à ce projet porteront le label `python`.

### Jeu de base (Essentiel)

- Le serpent se déplace en réponse aux touches fléchées (↑ ↓ ← →)
- La nourriture apparaît à des positions aléatoires
- Le serpent grandit lorsqu’il mange
- La partie se termine lors d’une collision (mur ou soi-même)

### Accélération / difficulté dynamique

- La vitesse du jeu augmente progressivement selon le score ou la longueur

### Leaderboard local

- Enregistrement des meilleurs scores dans un fichier local (par exemple JSON ou texte)
- Affichage des meilleurs scores après partie

### Obstacles internes (Option avancée)

- Mur(s) ou obstacle(s) fixes à l’intérieur du terrain à éviter
- Collision avec ces obstacles = fin de partie

### Menu / interface de lancement

- Menu de démarrage : “Jouer”, “Voir les scores”, “Quitter”
- Option pour recommencer après game over

## 🚀 Installation & Exécution

### Prérequis

- Python 3.x
- Module `curses` (sur Windows, installer `windows-curses`)

### Lancer le jeu

```bash
# Cloner le dépôt
git clone <URL_DU_REPO>

# Naviguer vers le dossier du projet
cd <ROUTE_DU_PROJET_LOCAL>/src/python/snake

# Installer les dépendances (si windows-curses requis)
pip install -r requirements.txt

# Lancer le jeu
python main.py
```

📁 Structure du projet

```
python/
├── snake/                       # code principal
│   ├── main.py                  # logique du jeu
│   ├── food.py                  # logique de la nourriture
│   ├── display.py               # rendu terminal (via curses)
│   └── leaderboard.py           # gestion des meilleurs scores
├── requirements.txt
└── README.md
```

## 🤝 Contribution

Les contributions sont les bienvenues ! N'hésitez pas à participer

## 📄 Licence

Ce projet est sous licence [Apache 2.0 license](https://github.com/Ionfinisher/30-days-of-open-source/blob/main/LICENSE). Voir le fichier `LICENSE` pour plus de détails.

## 🎓 À Propos

Ce projet fait partie du défi "30 Days Of Open Source" et vise à créer un jeu de Serpent jouable dans le terminal en Python.
