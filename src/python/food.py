import random

class Food:
    """
    Represents the food in the Snake game.
    """
    def __init__(self, window, char='*'):
        """
        Initializes the Food object.

        Args:
            window: The curses window object.
            char (str): The character to represent the food.
        """
        self.window = window
        self.char = char
        self.position = self._create_food()

    def _create_food(self):
        """
        Generates a random position for the food within the window boundaries.
        Note: This initial creation doesn't check for snake collision.
              The respawn method should be used for that.
        """
        height, width = self.window.getmaxyx()
        return (random.randint(1, height - 2), random.randint(1, width - 2))

    def display(self):
        """
        Displays the food on the screen.
        """
        if self.position:
            self.window.addch(self.position[0], self.position[1], self.char)

    def respawn(self, snake_body):
        """
        Finds a new position for the food that is not on the snake's body.
        """
        height, width = self.window.getmaxyx()
        while True:
            new_pos = (random.randint(1, height - 2), random.randint(1, width - 2))
            if new_pos not in snake_body:
                self.position = new_pos
                break
