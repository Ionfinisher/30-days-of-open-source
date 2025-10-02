# Snake CLI – Terminal Game (Python)

A terminal-based Snake game, programmed in Python, where the snake grows as it eats food, and the game ends if it collides with walls or itself. The goal: achieve the highest possible score.

## 🎯 Planned Features

Issues related to this project will carry the `python` label.

### Basic Game (Essential)

- The snake moves in response to arrow keys (↑ ↓ ← →)
- Food appears at random positions
- The snake grows when it eats
- The game ends upon collision (wall or self)

### Dynamic Acceleration / Difficulty

- Game speed gradually increases based on score or length

### Local Leaderboard

- Recording of best scores in a local file (e.g., JSON or text)
- Display of best scores after the game

### Internal Obstacles (Advanced Option)

- Fixed wall(s) or obstacle(s) inside the playing field to avoid
- Collision with these obstacles = game over

### Menu / Launch Interface

- Start menu: "Play", "View Scores", "Quit"
- Option to restart after game over

## 🚀 Installation & Execution

### Prerequisites

- Python 3.x
- `curses` module (on Windows, install `windows-curses`)

### Launch the game

```bash
# Clone the repository
git clone <REPO_URL>

# Navigate to the project folder
cd <LOCAL_PROJECT_PATH>/src/python/snake

# Install dependencies (if windows-curses required)
pip install -r requirements.txt

# Launch the game
python main.py
```

📁 Project Structure

```
python/
├── snake/                       # main code
│   ├── main.py                  # game logic
│   ├── food.py                  # food logic
│   ├── display.py               # terminal rendering (via curses)
│   └── leaderboard.py           # high score management
├── requirements.txt
└── README.md
```

## 🤝 Contribution

Contributions are welcome! Feel free to participate.

## 📄 License

This project is under the [Apache 2.0 license](https://github.com/Ionfinisher/30-days-of-open-source/blob/main/LICENSE). See the `LICENSE` file for more details.

## 🎓 About

This project is part of the "30 Days Of Open Source" challenge and aims to create a playable Snake game in the terminal using Python.
