package com.nekocho.adventuregame.controller;

import com.nekocho.adventuregame.model.Player;
import com.nekocho.adventuregame.model.Story;
import com.nekocho.adventuregame.model.weapons.Weapon;
import com.nekocho.adventuregame.model.weapons.WeaponKnife;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameController {
    // Create a Player object with initial stats
    private final Story story;
    private final Player player = new Player();

    @Autowired
    public GameController(Story story) {
        player.setHp(10); // Set initial HP
        player.setCoin(100); // Set initial coins
        Weapon WeaponKnife = new WeaponKnife(); // Create new Knife weapon
        player.setCurrentWeapon(WeaponKnife); // Set current weapon to knife
        // You can set other initial stats here
        this.story = story;
    }

    @GetMapping("/start")
    public String index(Model model) {
        // Initialize the game and pass initial data to Thymeleaf
        story.selectPosition("enterOakridge"); // Start the story
        model.addAttribute("mainTextArea", story.getMainText());
        model.addAttribute("choices", story.getCurrentChoices());
        model.addAttribute("choiceIdentifiers", story.getCurrentChoiceIdentifiers());
        model.addAttribute("hp", player.getHp());
        model.addAttribute("coin", player.getCoin());
        model.addAttribute("weapon", player.getCurrentWeapon().getName());
        // Add other necessary attributes
        return "game";
    }

    @PostMapping("/choice")
    public String handleChoice(@RequestParam("choice") String choice, Model model) {
        System.out.println("Received choice: " + choice);
        // Handle user choice and update the story
        story.selectPosition(choice);
        // Update UI data based on story progression
        model.addAttribute("mainTextArea", story.getMainText());
        model.addAttribute("choices", story.getCurrentChoices());
        model.addAttribute("choiceIdentifiers", story.getCurrentChoiceIdentifiers());
        model.addAttribute("hp", player.getHp());
        model.addAttribute("coin", player.getCoin());
        model.addAttribute("weapon", player.getCurrentWeapon().getName());
        return "game";
    }
}
