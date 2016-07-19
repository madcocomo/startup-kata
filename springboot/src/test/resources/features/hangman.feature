Feature: Hangman
    Scenario: Open the home page
        When player open home page
        Then the game name is display
            And player can start a new game

    Scenario: Start game
        Given the secret is: APPLE
            And the tried at start is: AEIOU
        When player start a new game
        Then the question is: A___E
            And the tried is: AEIOU
            And chance is: 12

#    Scenario: Play game
#        Given the game started as: APPLE, AEIOU, 12
#        When player input: L
#        Then the question is: A__LE
#            And the tried is: AEIOUL
#            And chance is: 12
