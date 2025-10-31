import curses
import time
from display import (
    init_screen, end_screen, draw_menu, draw_leaderboard,
    draw_game_elements, draw_game_over, get_player_name
)
from food import Food
from leaderboard import save_score, get_top_scores

def game_loop(screen):
    """The main game loop."""
    h, w = screen.getmaxyx()
    screen.nodelay(True)
    
    # Initial snake
    snake_x = w // 4
    snake_y = h // 2
    snake_body = [
        (snake_y, snake_x),
        (snake_y, snake_x - 1),
        (snake_y, snake_x - 2)
    ]
    
    direction = curses.KEY_RIGHT
    
    food = Food(screen, char='*')
    food.respawn(snake_body)

    score = 0
    speed = 150  # ms delay

    while True:
        key = screen.getch()

        # Determine new direction
        new_direction = key if key != -1 else direction
        if new_direction == curses.KEY_UP and direction != curses.KEY_DOWN:
            direction = new_direction
        elif new_direction == curses.KEY_DOWN and direction != curses.KEY_UP:
            direction = new_direction
        elif new_direction == curses.KEY_LEFT and direction != curses.KEY_RIGHT:
            direction = new_direction
        elif new_direction == curses.KEY_RIGHT and direction != curses.KEY_LEFT:
            direction = new_direction
        
        # Calculate new head position
        head = snake_body[0]
        if direction == curses.KEY_DOWN:
            new_head = (head[0] + 1, head[1])
        elif direction == curses.KEY_UP:
            new_head = (head[0] - 1, head[1])
        elif direction == curses.KEY_LEFT:
            new_head = (head[0], head[1] - 1)
        elif direction == curses.KEY_RIGHT:
            new_head = (head[0], head[1] + 1)
            
        snake_body.insert(0, new_head)
        
        # Check collisions
        if (new_head[0] in [0, h - 1] or
            new_head[1] in [0, w - 1] or
            new_head in snake_body[1:]):
            break  # Game Over

        # Check food collision
        if new_head == food.position:
            score += 1
            food.respawn(snake_body)
            if score % 5 == 0 and speed > 60:
                speed -= 15  # Increase speed
        else:
            snake_body.pop()
            
        draw_game_elements(screen, snake_body, food, score)
        curses.napms(speed)

    return score

def main_menu(screen):
    """Handles the main menu logic."""
    selected_option = 0
    options = ["Play", "Leaderboard", "Quit"]
    screen.nodelay(False) # Wait for user input in menu

    while True:
        draw_menu(screen, selected_option)
        key = screen.getch()

        if key == curses.KEY_UP:
            selected_option = (selected_option - 1) % len(options)
        elif key == curses.KEY_DOWN:
            selected_option = (selected_option + 1) % len(options)
        elif key == curses.KEY_ENTER or key in [10, 13]:
            if selected_option == 0:  # Play
                return "play"
            elif selected_option == 1:  # Leaderboard
                scores = get_top_scores()
                draw_leaderboard(screen, scores)
            elif selected_option == 2:  # Quit
                return "quit"
        elif key == ord('q'):
             return "quit"

def main_wrapper(stdscr):
    """Main function to run the game, wrapped for curses."""
    while True:
        menu_choice = main_menu(stdscr)

        if menu_choice == "play":
            while True:
                score = game_loop(stdscr)
                
                draw_game_over(stdscr, score)
                
                player_name = get_player_name(stdscr)
                if player_name: # Only save if a name was entered
                    save_score(player_name, score)

                # Ask to play again
                choice = -1
                while choice not in [ord('r'), ord('q')]:
                    choice = stdscr.getch()
                
                if choice == ord('q'):
                    break 
        
        elif menu_choice == "quit":
            break

if __name__ == '__main__':
    try:
        screen = init_screen()
        main_wrapper(screen)
    except Exception as e:
        end_screen()
        print(f"An error occurred: {e}")
    finally:
        end_screen()
