@functional
Feature: Game Records
    It is the game records acceptance test.

    Background: standard game
        Given the secret is: APPLE
            And the tried at start is: AE
            And the chance at start is: 5

    Scenario: Record game numbers
        Given an empty game history
            And player played a game: HM
            And player played a game: LP
            And player played a game: XYZBC
            And player played a game: XYZBC
        When admin view the game records
        Then the Played game counts is: 4
            And the Won game counts is: 1
            And the Lost game counts is: 2
