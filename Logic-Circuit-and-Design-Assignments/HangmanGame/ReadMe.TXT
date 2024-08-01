

   For the hangman game,the requirements are as follows:

    1. The user can start a game with pushing start button. 

    2. The word is taken as 64-bit input.

    3. Each 8 bit refers to the ascii code of a character. Therefore, the word is 8 characters at most. 
       For instance “hello” is represented as: 00000000 00000000 00000000 01101000 01100101 01101100 
       01101100 01101111. The user can enter ascii code equivalent of letters of word in 8 bit pins.

    4. The machine will show the number of characters in the word on 7-segment display.

    5. The user can enter guess character in 8 bit pin which is named select_char.

    6. The user must indicate that he has entered the guess character with pushing selected button.

    7. If the selected character is absent in the word a LED is turned ON. If ten LEDs are turned ON 
       the user loses, otherwise if the user can find all letters in the word, he wins.

    8. The machine will show the found letters in the word using a TTY display in Logisim.

    9. The machine shows the score as user vs machine.

   10. When the game ends the user can restart a new game with pushing restart button.