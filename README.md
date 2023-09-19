# Gomoku
Gomoku is a classic board game played by two people on a 20x20 grid, similar in concept to tic-tac-toe. In this version, each player takes turns placing their pieces on the board, with the objective of being the first to achieve 5 in a row. There are two types of games available: the Short Game, which concludes when one player achieves victory, and the Long Game, where the competition continues until the entire board is filled.

## AI Strategies
Currently, our Gomoku game employs the Minimax algorithm with alpha-beta pruning to make strategic decisions. This AI strategy aims to find the optimal move by exploring various game states, evaluating their potential outcomes, and minimizing the opponent's chances of winning.

Our future goal is to implement the Monte Carlo Tree Search (MCTS) algorithm integrated with neural networks. This advanced approach will enhance the AI's decision-making abilities and make the game even more challenging and enjoyable.

## Getting Started
To run the Gomoku game, follow these steps:
1. Execute GraderGUI.java, which will run all the files ending with FP.java in the folder.
2. If you want to play against a specific FP (Finite Player), use TicTacToeGUI.java.
