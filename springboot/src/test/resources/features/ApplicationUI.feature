Feature: ApplicationUI
    Background: It is the most outside application test using WebDriver and memory DB.
        It focus on the verification that slowly dependency part works.
        The main parts of business logic is handled in functional acceptance tests.
        TODO: using WebDriver instance of HTML text content

    Scenario: Open the home page
        When player open home page
        Then the game name is display
            And player can start a new game
