# Snake CLI – Terminal game (Python)

A Snake game in terminal mode, programmed in Python, where the snake grows when it eats food, and the game ends if it collides with the walls or itself. The goal: to get the highest score possible.

## 🎯 Planned features

Issues related to this project will be labeled `python`.

### Basic game (Essential)

- The snake moves in response to the arrow keys (↑ ↓ ← →)
- Food appears in random positions
- The snake grows when it eats
- The game ends when it collides (with a wall or itself)

### Acceleration/dynamic difficulty

- Game speed gradually increases based on score or length

### Local leaderboard

- High scores saved to a local file (e.g., JSON or text)
- High scores displayed after the game

### Internal obstacles (Advanced option)

- Fixed wall(s) or obstacle(s) inside the playing area to avoid
- Collision with these obstacles = game over

### Menu / launch interface

- Start menu: “Play,” “View scores,” “Quit”
- Option to restart after game over

## 🚀 Installation & Execution

### Prerequisites

- Python 3.x
- `curses` module (on Windows, install `windows-curses`)

### Launching the game

```bash
# Clone the repository
git clone <URL_OF_THE_REPO>

# Navigate to the project folder
cd <PATH_OF_THE_LOCAL_PROJECT>/src/python/snake

# Install dependencies (if windows-curses is required)

Translated with DeepL.com (free version)
