import curses

def init_screen():
    """Initializes the curses screen and settings."""
    screen = curses.initscr()
    curses.curs_set(0)
    curses.noecho()
    curses.cbreak()
    screen.keypad(True)
    return screen

def end_screen():
    """Restores the terminal to its original state."""
    curses.nocbreak()
    curses.keypad(False)
    curses.echo()
    curses.endwin()

def draw_menu(screen, selected_option):
    """Draws the main menu."""
    screen.clear()
    h, w = screen.getmaxyx()
    
    title = "Snake CLI"
    screen.addstr(h // 2 - 4, w // 2 - len(title) // 2, title)

    options = ["Play", "Leaderboard", "Quit"]
    for i, option in enumerate(options):
        x = w // 2 - len(option) // 2
        y = h // 2 - 1 + i
        if i == selected_option:
            screen.attron(curses.A_REVERSE)
            screen.addstr(y, x, option)
            screen.attroff(curses.A_REVERSE)
        else:
            screen.addstr(y, x, option)
    screen.refresh()

def draw_leaderboard(screen, scores):
    """Draws the leaderboard screen."""
    screen.clear()
    h, w = screen.getmaxyx()
    
    title = "Leaderboard"
    screen.addstr(1, w // 2 - len(title) // 2, title)
    
    if not scores:
        screen.addstr(3, w // 2 - len("No scores yet!") // 2, "No scores yet!")
    else:
        for i, score in enumerate(scores):
            score_text = f"{i + 1}. {score['name']}: {score['score']}"
            screen.addstr(3 + i, w // 2 - len(score_text) // 2, score_text)
            
    screen.addstr(h - 2, w // 2 - len("Press any key to return...") // 2, "Press any key to return...")
    screen.refresh()
    
    screen.nodelay(False)
    screen.getch()
    screen.nodelay(True)

def draw_game_elements(screen, snake_body, food, score):
    """Draws all game elements in one go."""
    screen.clear()
    screen.border(0)
    
    # Draw score
    h, w = screen.getmaxyx()
    score_text = f"Score: {score}"
    screen.addstr(0, w // 2 - len(score_text) // 2, score_text)

    # Draw snake
    if snake_body:
        screen.addch(snake_body[0][0], snake_body[0][1], '@') # Head
        for part in snake_body[1:]:
            screen.addch(part[0], part[1], '#') # Body

    # Draw food
    food.display()
    
    screen.refresh()

def draw_game_over(screen, score):
    """Draws the game over screen."""
    h, w = screen.getmaxyx()
    text1 = "GAME OVER"
    text2 = f"Your Score: {score}"
    text3 = "Press 'r' to play again or 'q' to return to menu."
    
    screen.addstr(h // 2 - 2, w // 2 - len(text1) // 2, text1)
    screen.addstr(h // 2 - 1, w // 2 - len(text2) // 2, text2)
    screen.addstr(h // 2 + 1, w // 2 - len(text3) // 2, text3)
    screen.refresh()

def get_player_name(screen):
    """Prompts the user to enter their name."""
    h, w = screen.getmaxyx()
    screen.nodelay(False)
    curses.echo()
    
    prompt = "Enter your name: "
    screen.addstr(h // 2 + 2, w // 2 - len(prompt) // 2, prompt)
    
    # Create a small window for input to control text position
    input_win = curses.newwin(1, 16, h // 2 + 2, w // 2 + len(prompt) // 2)
    input_win.bkgd(' ', curses.A_REVERSE)
    input_win.keypad(True)
    
    name = input_win.getstr(0, 0, 15).decode('utf-8').strip()

    curses.noecho()
    screen.nodelay(True)
    
    return name if name else "Player"
