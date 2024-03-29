package com.nekocho.adventuregame.model;

import java.util.*;

import org.springframework.stereotype.Component;

@Component
public class Story {

    // Get player stats
    private Player player;

    // Access Set up mainText value
    private String mainText;

    // Display choices as a list
    private List<String> choices;

    // Get image name from resources/static/image directory
    private String imagePath;

    // Display User input as a List
    private String userInput;

    // Previous state fields
    private String previousMainText;
    private List<String> previousChoices;
    private String previousImagePath;

    // Construct the Story with player stats and choices
    public Story(Player player) {
        this.player = player;
        this.choices = new ArrayList<>();
    }

    // SETTERS

    // Method to set the main text
    public void setMainText(String mainText) {
        // Save previous state
        this.previousImagePath = this.imagePath;
        this.previousMainText = this.mainText;
        this.previousChoices = this.choices;

        // Set new state
        this.mainText = mainText;

    }

    // Method to set the choices
    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    // Method to set image path
    public void setImagePath(String imagePath){
        System.out.println("Setting image path to: " + imagePath);
        System.out.println("Previous image path: " + this.previousImagePath);
        this.imagePath = imagePath;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
        handleUserInput(userInput);
    }


    // GETTERS

    // Method to get the main text
    public String getMainText() {
        return mainText;
    }

    // Method to get the current choices
    public List<String> getCurrentChoices() {
        return choices;
    }

    // Method to get image path
    public String getImagePath() {
        return imagePath;
    }

    public String getUserInput() {
        return userInput;
    }




    // Handle user input choices
    public void handleUserInput(String userInput) {
        if (userInput != null) {
            // Tokenize the user input by splitting it into words
            String[] words = userInput.toLowerCase().split("\\s+"); // Split by whitespace and convert to lowercase

            // Check for keywords in the tokenized input
            if (containsKeyword(words, "look") && containsKeyword(words, "around") || containsKeyword(words, "area") || containsKeyword(words, "oakridge")) {
                lookAroundOakridge();
            } else if (containsKeyword(words, "talk") || (containsKeyword(words, "speak")) && containsKeyword(words, "baker")) {
                talkToBaker();
            } else if (containsKeyword(words, "attack") && containsKeyword(words, "baker")) {
                attackBaker();
            } else if (containsKeyword(words, "enter") && containsKeyword(words, "tavern")) {
                enterTavern();
            } else if (containsKeyword(words, "back") || containsKeyword(words, "return")) {
                goBack();
            } else {
                noScenario();
            }

        }
    }
    private boolean containsKeyword(String[] words, String keyword) {
        return Arrays.asList(words).contains(keyword);
    }

    // Method to map choices to methods
    public String selectPosition(String nextPosition) {
        switch (nextPosition) {
            case "Back":
                goBack();
                break;
            case "Restart":
                return "redirect:/";
            case "enterOakridge", "Leave", "Thank him and leave":
                enterOakridge();
                break;
            case "Talk to the Baker":
                talkToBaker();
                break;
            case "Attack Baker":
                attackBaker();
                break;
            case "Look Around Oakridge":
                lookAroundOakridge();
                break;
            case "Go to the Tavern":
                enterTavern();
                break;

        }
        return nextPosition;
    }
            //TO FIX

//            case "talkToBarkeep":
//                talkToBarkeep();
//                break;
//            case "sitAtTable":
//                sitAtTable();
//                break;
//            case "missingPeople":
//                missingPeople();
//                break;
//            case "bookRoom":
//                bookRoom();
//                break;
//            case "buyAle":
//                buyAle();
//                break;
//            case "findWeapon":
//                findWeapon();
//                break;
//            case "searchRoom":
//                searchRoom();
//                break;
//            case "chillRoom":
//                chillRoom();
//                break;
//            case "leaveRoom":
//                leaveRoom();
//                break;
//            case "endDay":
//                endDay();
//                break;

//        }
//
//    }

    public void noScenario() {
        setMainText("Sorry, I don't know what you mean?");
        setChoices(Arrays.asList("Back"));
    }

    public void enterOakridge() {
        // Check if player is still alive
        if (player.getHp() <= 0) {
            player.setHp(10);
        }
        setImagePath("/images/village-pixel-art.png");
        setMainText("It's a bright sunny day when you enter Oakridge, a small humble village.\nThe smell of fresh bread fills the air as you walk past the baker. \nLooking around you notice that everyone looks really tense... \n\nWhat do you do?");
        setChoices(null);
    }

    public void talkToBaker() {
        System.out.println(this.previousMainText);
        setImagePath("/images/baker.png");
        setMainText("You approach the baker and he looks up. You notice a Tavern next door. You ask him why everyone is so tense, and he responds: \n'Someone's been causing trouble, people going missing... got everyone scared' \n\nWhat do you do?");
        setChoices(Arrays.asList("Thank him and leave", "Go to the Tavern", "Attack Baker"));
    }

    public void attackBaker() {
        System.out.println(this.previousMainText);
        setImagePath("/images/guard.png");
        setMainText("As you draw your weapon, a guard nearby attacks you. \n\n(You take 3 damage)");
        // Reduce player health
        int currentHealth = player.getHp();  // Log current health
        player.setHp(currentHealth - 3);     // Reduce health by 3

        // Check if player is still alive
        if (player.getHp() <= 0) {
            setImagePath("/images/game-over.png");
            setMainText("You have been defeated...");
            setChoices(Collections.singletonList("Restart"));
            // You can implement logic here for what happens when the player is defeated
            // For example, you might trigger a game over screen or reset the game.
        } else {
            // Player is still alive, continue with the story
            setChoices(Arrays.asList("Leave"));
        }

    }

    public void lookAroundOakridge() {
        setImagePath("/images/village-pixel-art.png");
        setMainText("You see a few people buying bread from the baker, next to the bakery is a Tavern. \nNothing else really stands out.");
        setChoices(Arrays.asList("Leave", "Go to the Tavern"));
    }

    public void enterTavern() {
        setImagePath("/images/tavern.png");
        setMainText("The Tavern is small but friendly, a barkeep is quietly observing you while cleaning a glass at the bar. \nThere are a few tables and chairs around. \n\nWhat do you do?");
        setChoices(Arrays.asList("Talk to the Barkeep", "Grab a seat at the table", "Leave"));
    }

    public void goBack() {
        this.imagePath = previousImagePath;
        this.mainText = previousMainText;
        this.choices = previousChoices;

    }

    //TO FIX
//    public void talkToBarkeep() {
//        ui.mainTextArea.setText("'How's your day going? new here?' \n\nWhat do you do?");
//        ui.choice1.setText("Ask about the town");
//        ui.choice2.setText("Ask for a room");
//        ui.choice3.setText("Ask for an Ale (5 coins)");
//        ui.choice4.setText("Leave");
//
//        // Sets next positions on button click
//        game.nextPosition1 = "missingPeople";
//        game.nextPosition2 = "bookRoom";
//        game.nextPosition3 = "buyAle";
//        game.nextPosition4 = "enterOakridge";
//
//    }
//
//    public void sitAtTable() {
//        ui.mainTextArea.setText("You sit at the nearest table. The barkeep approaches and asks if you'd like a drink. \n\nWhat do you do?");
//        ui.choice1.setText("Ask about the Town");
//        ui.choice2.setText("Ask for a room");
//        ui.choice3.setText("Ask for an Ale (5 coins)");
//        ui.choice4.setText("Get up and leave");
//
//        // Sets next positions on button click
//        game.nextPosition1 = "missingPeople";
//        game.nextPosition2 = "bookRoom";
//        game.nextPosition3 = "buyAle";
//        game.nextPosition4 = "enterOakridge";
//    }
//
//    public void missingPeople() {
//        ui.mainTextArea.setText("'The people are friendly but something darker is happening, people are going missing... everyone's scared. There's a rumour of someone calling themselves The Illusionist that's causing these disappearances...' \n\nWhat do you do?");
//        ui.choice1.setText("Buy another Ale (5 coins)");
//        ui.choice2.setText("Ask for a room");
//        ui.choice3.setText("Thank him and Leave");
//        ui.choice4.setText("");
//
//        // Sets next positions on button click
//        game.nextPosition1 = "buyAle";
//        game.nextPosition2 = "bookRoom";
//        game.nextPosition3 = "enterOakridge";
//        game.nextPosition4 = "";
//
//    }
//
//    public void buyAle() {
//        ui.mainTextArea.setText("The barkeep brings you your Ale (-5 coins) \n\n What do you want to do?");
//        player.coin = player.coin - 5; // Reduce player coin by 5
//        ui.coinNumberLabel.setText("" + player.coin); // Update coin number display each time
//        ui.choice1.setText("Talk to the barkeep");
//        ui.choice2.setText("Buy another Ale (5 coins)");
//        ui.choice3.setText("Leave");
//        ui.choice4.setText("");
//
//        // Sets next positions on button click
//        game.nextPosition1 = "talkToBarkeep";
//        game.nextPosition2 = "buyAle";
//        game.nextPosition3 = "enterOakridge";
//        game.nextPosition4 = "";
//    }
//
//    public void bookRoom() {
//        ui.mainTextArea.setText("The barkeep takes you to your room. \n\nWhat do you do?");
//        ui.choice1.setText("Search Room");
//        ui.choice2.setText("Leave Room");
//        ui.choice3.setText("Rest for the day");
//        ui.choice4.setText("");
//
//        // Sets next positions on button click
//        game.nextPosition1 = "searchRoom";
//        game.nextPosition2 = "leaveRoom";
//        game.nextPosition3 = "endDay";
//        game.nextPosition4 = "";
//    }
//
//    public void searchRoom() {
//        ui.mainTextArea.setText("Searching a room, you notice a loose floorboard. \n\nWhat do you do?");
//        ui.choice1.setText("Lift it up");
//        ui.choice2.setText("Leave it alone");
//        ui.choice3.setText("");
//        ui.choice4.setText("");
//
//        // Sets next positions on button click
//        game.nextPosition1 = "findWeapon";
//        game.nextPosition2 = "chillRoom";
//        game.nextPosition3 = "";
//        game.nextPosition4 = "";
//
//    }
//
//    public void findWeapon() {
//        ui.mainTextArea.setText("Under the floorboard you find a Long Sword! (Obtained a Long Sword)");
//        player.currentWeapon = new WeaponLongSword(); //change weapon from knife to long sword
//        ui.weaponNameLabel.setText(player.currentWeapon.name); //set weapon name label in UI to new weapon
//        ui.choice1.setText("Next");
//        ui.choice2.setText("");
//        ui.choice3.setText("");
//        ui.choice4.setText("");
//
//        // Sets next positions on button click
//        game.nextPosition1 = "chillRoom";
//        game.nextPosition2 = "";
//        game.nextPosition3 = "";
//        game.nextPosition4 = "";
//    }
//
//    public void chillRoom() {
//        ui.mainTextArea.setText("The room is simple and cosy, there is a bed, a small table and chair and an empty wardrobe to store your clothes. \n\nWhat would you like to do?");
//        ui.choice1.setText("Leave the room");
//        ui.choice2.setText("Rest for the Day");
//        ui.choice3.setText("");
//        ui.choice4.setText("");
//
//        // Sets next positions on button click
//        game.nextPosition1 = "leaveRoom";
//        game.nextPosition2 = "endDay";
//        game.nextPosition3 = "";
//        game.nextPosition4 = "";
//
//    }
//
//    public void leaveRoom() {
//
//    }
//
//    public void endDay() {
//
//    }
//}
//
//
//
}