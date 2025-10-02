import curses
import random

def main(stdscr):
    # Initialize curses
    curses.curs_set(0)  # Hide cursor
    stdscr.nodelay(1)   # Non-blocking input
    stdscr.timeout(100) # Refresh every 100 ms

    sh, sw = stdscr.getmaxyx()
    
    # Create a window for the game
    game_window = curses.newwin(sh, sw, 0, 0)
    game_window.keypad(1) # Enable keypad for special keys

    # Initial snake position and direction
    snake = [[sh // 2, sw // 2 + 1], [sh // 2, sw // 2], [sh // 2, sw // 2 - 1]]
    direction = curses.KEY_RIGHT

    # Obstacles
    obstacles = generate_obstacles(sh, sw, snake, num_obstacles=5)

    # Initial food position
    food = generate_food(sh, sw, snake, obstacles)

    score = 0

    while True:
        next_key = game_window.getch()
        direction = direction if next_key == -1 else next_key

        # Calculate new head position
        head = snake[0][:]
        if direction == curses.KEY_DOWN:
            head[0] += 1
        elif direction == curses.KEY_UP:
            head[0] -= 1
        elif direction == curses.KEY_LEFT:
            head[1] -= 1
        elif direction == curses.KEY_RIGHT:
            head[1] += 1

        # Game over conditions
        if (
            head[0] < 0
            or head[0] >= sh
            or head[1] < 0
            or head[1] >= sw
            or head in snake[1:]
            or head in obstacles
        ):
            game_over(stdscr, score)
            break

        snake.insert(0, head)

        # Check if snake ate food
        if head == food:
            score += 1
            food = generate_food(sh, sw, snake, obstacles)
        else:
            tail = snake.pop()
            game_window.addch(tail[0], tail[1], ' ')

        # Draw snake
        for y, x in snake:
            game_window.addch(y, x, '#')

        # Draw food
        game_window.addch(food[0], food[1], '*')

        # Draw obstacles
        for y, x in obstacles:
            game_window.addch(y, x, 'X') # 'X' for obstacles

        # Display score
        stdscr.addstr(0, 0, f'Score: {score}')
        stdscr.refresh()
        game_window.refresh()

def generate_food(sh, sw, snake, obstacles):
    while True:
        food = [random.randint(1, sh - 2), random.randint(1, sw - 2)]
        if food not in snake and food not in obstacles:
            return food

def generate_obstacles(sh, sw, snake, num_obstacles):
    obstacles = []
    while len(obstacles) < num_obstacles:
        obstacle = [random.randint(1, sh - 2), random.randint(1, sw - 2)]
        if obstacle not in snake and obstacle not in obstacles:
            obstacles.append(obstacle)
    return obstacles

def game_over(stdscr, score):
    stdscr.clear()
    sh, sw = stdscr.getmaxyx()
    msg = f"Game Over! Score: {score}"
    stdscr.addstr(sh // 2, (sw - len(msg)) // 2, msg)
    stdscr.addstr(sh // 2 + 1, (sw - len("Press any key to exit")) // 2, "Press any key to exit")
    stdscr.refresh()
    stdscr.nodelay(0) # Blocking input
    stdscr.getch()

if __name__ == "__main__":
    curses.wrapper(main)
