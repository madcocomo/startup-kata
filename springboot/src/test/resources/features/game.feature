@functional
Feature: Game
    It is the game scenarios acceptance test.
    It uses mockMVC and mock object to test the integration between outside dependencies like DB and UI.
    Using mock makes test much easier to setup, as well as much faster.

    Scenario: Start game
        Given the secret is: APPLE
            And the tried at start is: AE
            And the chance at start is: 10
        When player start a new game
        Then the question is: A___E
            And the tried is: AE
            And chance is: 10

    Scenario: Play game
        Given the secret is: APPLE
            And the tried at start is: AE
            And the chance at start is: 10
            And player start a new game
        When player input: L
        Then the question is: A__LE
            And the tried is: AEL
            And chance is: 10
