@functional
Feature: Game Records
    It is the game records acceptance test.

    Background: standard game
        Given the secret is: IDONTCARE
            And the tried at start is: IDC
            And the chance at start is: 1

    Scenario: Record game numbers
        Given an empty game history
            And player start a new game
            And player start a new game
        When admin view the game records
        Then the played game counts is: 2
