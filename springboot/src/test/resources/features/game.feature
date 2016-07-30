@functional
Feature: Game
    It is the game scenarios acceptance test.
    It uses mockMVC and mock object to test the integration between outside dependencies like DB and UI.
    Using mock makes test much easier to setup, as well as much faster.

    Background: standard game
        Given the secret is: APPLE
            And the tried at start is: AEIOU
            And the chance at start is: 12

    Scenario: Start game
        When player start a new game
        Then the question is: A___E
            And the tried is: AEIOU
            And chance is: 12

    Scenario: guess wrong
        Given player start a new game
            And player input: L
        When player input: X
        Then the question is: A__LE
            And the tried is: AEIOULX
            And chance is: 11

    Scenario: Win a game
        When player start a new game
        Then game in progress:
        | Guess | Question | Tried      | Chance | State    |
        | L     | A__LE    | AEIOUL     | 12     | playing  |
        | P     | APPLE    | AEIOULP    | 12     | win  |

    Scenario: Lose a game
        Given the chance at start is: 5
        When player start a new game
        Then game in progress:
        | Guess | Question | Tried      | Chance | State    |
        | X     | A___E    | AEIOUX     | 4      | playing  |
        | Y     | A___E    | AEIOUXY    | 3      | playing  |
        | Z     | A___E    | AEIOUXYZ   | 2      | playing  |
        | P     | APP_E    | AEIOUXYZP  | 2      | playing  |
        | B     | APP_E    | AEIOUXYZPB | 1      | playing  |
        | C     | APP_E    | AEIOUXYZPBC| 0      | lose     |
