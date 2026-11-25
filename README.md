# Tic-Tac-Toe in Java

A two-player terminal Tic-Tac-Toe game ported from Python to Java. The port separates board rendering, move validation, win and draw checks, and the replay prompt. [Game source](src/TicTacToe.java)

## Run locally

    mkdir build
    javac -d build src/TicTacToe.java
    java -cp build TicTacToe

Choose X or O, then play by entering numbers 1-9 for board squares starting in
the top-left corner.
