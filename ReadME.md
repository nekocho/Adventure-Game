# First Text-Based Adventure Game
A game project developed using Java, Springboot and Thymeleaf.

## The progress so far...

- Unpacked Spring Infrastructure
- Created a Story, Player and Weapons class
- Created HomeController and GameController
- Created HTML and CSS templates
- Build Story for the Game
- Build Player Stats
- Build Weapons directory with multiple weapons
- Fixed bugs mentioned in issues
- Added images resource folder with images for title and first scenario


## Things to add:

- Change button based options to player input instead
- Add storylines and expand
- Add feature to change weapons or pick up weapons
- Add buying/selling options

## Resolved issues

**Choice Buttons Not Working**

- Buttons wouldn't load the next page
- Initially thought that the issue was with the choice mapping
- Debugging showed that the "Talk to baker" button was going to the "Look around" method and vice versa
- Created a HashMap and List for choice mapping
- Had switch case with setText and setChoices together with a List of choices
- Replaced this with a switch case with case referring to methods instead for each choice. Each method had its own setText and setChoices
- Removed the HashMapping and altered Thymeleaf template
- Thymeleaf template showed th:value="${choiceIdentifier[choiceStat.index]}" instead of th:value="${choices[choiceStat.index]}" which caused an issue
- Didn't need choiceIdentifier at all or HashMapping, had to change the way switch case was written to resolve issue
 

**Player HP not changing Frontend, only backend**

- When player is attacked, HP did not change or go down
- Debugging showed that HP is changing but not being displayed
- Player class wasn't linked to story class for the changes to take place
- Altered Story class to include Player stats which resolved issue

Previously showed:
```css
    public Story() {
        this.choices = new ArrayList<>();
    }
```
Edited to:
```css
    public Story(Player player) {
        this.player = player;
        this.choices = new ArrayList<>();
    }
```


