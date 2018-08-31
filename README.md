Charles Habermehl
CS 351
8/31/18
Pinball

Runs from executable jar file.
GameManager.java is the entry point.

Features:
    Play button - begins game, launching the ball in a random direction at a constant velocity
    Reset button - resets game with new board
    When a button is active it is yellow.
    Point tile - each point tile is 10 points

    Ball automatically resets after hitting the wall 3 times
    Point totals can only be reset by restarting the game

Bugs:
    Passing over points squares gives points but does not repaint the passed over tile.
    the tiles are either painted at the right coordinates, on the side of the board, off
    off the playing surface, or on already painted tiles.

Features Missing from Project Scope:
    Being able to select starting location with the mouse.