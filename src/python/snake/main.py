import curses
import time

def main(stdscr):
    stdscr.clear()
    
    height, width = stdscr.getmaxyx()
    
    start_y = height // 2
    start_x = (width - len("Snake Game")) // 2
    
    stdscr.addstr(start_y, start_x, "Snake Game")
    
    stdscr.refresh()
    
    stdscr.timeout(3000)  # 3 secondes
    stdscr.getch()

if __name__ == "__main__":
    curses.wrapper(main)