@UI
Feature: UI
    It is the most outside application test using WebDriver and memory DB.
    It focus on the verification that slowly dependency part works.
    The main parts of business logic is handled in functional acceptance tests.

    Scenario: Open the home page
        In order to play the game, as a player I want to know what the game is and see the entry
        When player open home page
        Then player can see the game name
            And player can start a new game

    Scenario: Open the game page
        In order to guess the word, as a player I need someway to input letters
        When player open game page
        Then player could guess the word

    Scenario: Open the game record page
        Int order to see the game record, as a admin I want to see played game info in a page
        When admin open record page
        Then admin can see the played game counts

