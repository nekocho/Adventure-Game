package com.nekocho.adventuregame.controller;

import com.nekocho.adventuregame.model.Player;
import com.nekocho.adventuregame.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameController {
    // Create a Player object with initial stats
    private final Story story;
    private final Player player;

    private boolean animationsEnabled;

    @Autowired
    public GameController(Story story, Player player) {
        this.story = story;
        this.player = player;
    }

    @GetMapping("/start")
    public String handleStart(@RequestParam(name = "animationsEnabled", required = false) String animationsEnabled, Model model) {
        // Initialize the game and pass initial data to Thymeleaf
        story.selectPosition("enterOakridge"); // Start the story
        model.addAttribute("imagePath", story.getImagePath());
        model.addAttribute("mainTextArea", story.getMainText());
        model.addAttribute("choices", story.getCurrentChoices());
        model.addAttribute("player", player);

        this.animationsEnabled = "on".equals(animationsEnabled);

        if (this.animationsEnabled) {
            model.addAttribute("animationsEnabled", true);
        } else {
            model.addAttribute("animationsEnabled", false);
        }

        return "game";
    }

    @PostMapping("/choice")
    public String handleChoice(@RequestParam("choice") String choice, Model model) {
        // Handle user choice and update the story
        story.selectPosition(choice);

        // Update UI data based on story progression
        model.addAttribute("imagePath", story.getImagePath());
        model.addAttribute("mainTextArea", story.getMainText());
        model.addAttribute("choices", story.getCurrentChoices());
        model.addAttribute("player", player);
        model.addAttribute("animationsEnabled", this.animationsEnabled);
        return "game";
    }
}
