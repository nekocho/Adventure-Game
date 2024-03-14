# First Adventure Game
A game developed using Java, Springboot and Thymeleaf.

## The progress

- Unpacked Spring Infrastructure
- Created a Story, Player and Weapons class
- Created HomeController and GameController
- Created HTML and CSS templates
- Build Story for the Game
- Build Player Stats
- Build Weapons directory with multiple weapons
- Create Monsters and Enemies directory

## Issues

**Choice Buttons**

- Buttons would not load the next page
- Initially thought that the issue was with the choice mapping
- Created a HashMap and List for choice mapping
- Had switch case with setText and setChoices together with a List of choices
- Replaced this with a switch case with case referring to methods instead for each choice. Each method had its own setText and setChoices
- Removed the HashMapping and altered Thymeleaf template
- Thymeleaf template showed th:value="${choiceIdentifier[choiceStat.index]}" instead of th:value="${choices[choiceStat.index]}" which caused an issue
- Didn't need choiceIdentifier at all or HashMapping
 