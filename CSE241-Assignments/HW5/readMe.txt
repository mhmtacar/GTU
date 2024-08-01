

   This program works with movements like A 1, B 7, C 10 and works with commands like "SAVE FILE.TXT" , "LOAD FILE.TXT" , OP 1 , OP 2 , OP 3 , OP 4
   that the user will enter. If the user enters OP 1 , program resets the board to the beginning. If the user enters OP 2 , program sets the board 
   size to the given values and the board is reset after this operation. If the user enters OP 3 , program prints the last move on the board. If the
   user enters OP 4 , program prints number of moves on the board.


   At the beginning of the game , I asked to user that which type of game object do you want to create? If the user enters 1 ,  I created two HexVector
   game object. If the user enters 2 , I created two HexArray1D game object. If the user enters 3 , I created two HexAdapter game object whose types are
   vector. If the user enters 4 , I created two HexAdapter game object whose types are deque.


   Because of I couldn't play 5 games at the same time, I created 2 game objects. When both games are over, by comparing 2 games , I printed that these 
   two games are same or not.


   I added a faulty game file (which has invalid characters) named "GAME.TXT" to zip so you can test the global function with loading this file. After 
   loading the faulty game file, I print faulty game on the screen and finish the current game.

