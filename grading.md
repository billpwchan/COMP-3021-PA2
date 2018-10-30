# COMP3021 Assignment 2 Submission Requirement and Grading Scheme

## Submission Requirement:
1. Project Code (.zip file). **Note: Please submit your whole project. Do not submit only single files that you have modified.**
2. An url.txt file, containing Your GitHub private repo link. We will also ask you to add TAs' accounts as collaborators. Any commit after the deadline will be regarded as invalid. **Note: Please use a repository different from the one that you used for assignment1**
3. A report (PDF) if you implement bonus. In the report, you should specify: (1) which bonus tasks you implemented, (2) in which part of your code (i.e., classes and methods) you implemented the bonus tasks. **NOTE: In this assignment, we will let you propose bonus tasks. We will select tasks from your proposal as valid bonus tasks. Please read further for more details about bonus tasks.**

## Grading:
|   |  Percentage  | Remark  |
|---|---|---|
|  GitHub push number >=5  |  5% |  = max( # / 5 * 5%, 5%) |
|  Compilable |  10% | 0 or 10% only  |
|  Demonstration | 85%  |  Detailed Demo Procedure is in Section Demonstration  |
|  Bonus | Determined on the bonus tasks you implemented| = sum of the points you get for each bonus task|

## Demonstration
The demonstration of PA2 consists of two parts: compulsory demo tasks and random demo tasks.
* **Compulsory Demo Tasks: (80 pts)** You must demo these tasks to the TAs. Compulsory demo tasks include those tasks that are related to the main logic of the game, i.e., creating maps with Level Edit Pane and playing the game.
Detailed demo procedure:
    * Creating new maps:
    From the level editor pane
        1. Select each of the 7 brushes in the list view, and ensure they each correctly draw the element on the canvas (+7)
        2. Select `Player on Destination` brush, and click somewhere on the grid. Then, select `Player on Tile` brush, and click elsewhere. Ensure that:
            * The player is drawn in the new location (+1)
            * The player is removed from the old location (+1)
            * The old location now shows the destination tile dot (+3)
        3. Select `Player on Tile` brush, and click somewhere on the grid. Then, using the same brush, click elsewhere. Ensure that:
            * The player is drawn in the new location (+1)
            * The player is removed from the old location (+1)
            * The old location is now a normal tile (+3)
        4. Select `Player on Tile` brush, and click the top left location.
        5. Without exiting the level editor, change map dimensions to 4 by 4, and click new grid. Ensure that
            * The map is resized appropriately (+3)
            * The map is reset (+1)
        6. Select `Crate on Tile` brush, and click the top left location (where the player used to be). Now, select `Player on Tile` brush, and place player on bottom right corner.
            * Ensure that the crate does not disappear (+3)
        7. Have the student demonstrate that the following map conditions cannot be violated when trying to save the map
            * Map dimensions smaller than 3x3 (+2)
            * Map without a player (+2)
            * Imbalanced number of crates and destinations. Ensure that `Crate on Destination`, `Player on Destination`, `Crate on Tile`, and `Destination` are all used (+3)
            * Having fewer than 1 crate/destination pair (+2)
    * Playing the game:
     From the level selection pane 
        1. Click `Choose map directory`, but cancel without selecting a directory, and ensure program has no errors (+2)
        2. Click `Choose map directory`, and choose the map directory. Ensure the maps are loaded into the list view (+3)
        3. Click around on a few of the maps, and ensure that the preview of the map is displayed on the right (+5)
        4. Click `Play` on one `02-easy.txt`, and ensure that it launches the gameplay pane (+3)
            1. Move around a few times, and ensure the following:
                * Level name is correct (+1)
                * Timer is working correctly (+3)
                * Moves counter is working correctly (+1)
                * The map elements are updated correctly in accordance with user input (+3)
                * The move sound is played (+1)
                * You cannot push crates into walls, or two crates at once (+3)
            2. Restart the game, and ensure:
                * The map has been reset (+1)
                * The timer has been reset and is functioning normally (+4)
                * The moves counter has been reset (+2)
                * The number of restarts has been incremented (+1)
            3. Deadlock the level, and ensure:
                * The deadlock popup shows (+1)
                * The deadlock sound is played (+1)
                * The restart option works as described above (+1)
            4. Win the level, and ensure
                * The level clear popup shows (+1)
                * Click `Next Level`, and ensure:
                    * The map has been reset (+1)
                    * The timer has been reset and is functioning normally (+4)
                    * The moves counter has been reset (+2)
                    * The number of restarts has been reset (+1)
                * Click `Quit to Menu`, and ensure:
                    * A popup shows, asking if you're sure about returning to the menu (+1)
                    * Clicking `OK`  brings you back to the menu (+1)
    

* **Random Demo Tasks: (5 pts)** In the demo, the TAs will randomly pick up one of the simple tasks and let you demo.

    * Task 1:
    From the main menu pane
        1. Ensure `Play` button leads to correct pane, go back (+1)
        2. Ensure `Level Editor` button leads to correct pane, go back (+1)
        3. Ensure `About / Settings` button leads to correct pane, go back (+1)
        4. Ensure `Quit` button terminates the program. Double check that the task is no longer running in the Intellij window! If it's still running, they may have forgotten to set certain threads as daemons (+2)
        
    * Task 2:
    From the about/settings pane
        * Disable the sounds FX, and ensure:
            * The button text updates correctly (+1)
            * There are no more move, win, and deadlock sounds (+4)
            
    * Task 3:
    Create a valid new map (specified by the TA) from the level editor pane, load it and win it. (+5) 

## Bonus Tasks

In this assignment, we are encouraging you to find the limitations of the provided jar file and propose bonus tasks by yourself. You are welcome to submit proposals of bonus tasks that implement functionalities that are not provided by our provided PA2_obf.jar. From your proposals, we will approve a certain number of tasks as valid bonus tasks and assign bonus points to them. You can choose to implement bonus tasks that you are interested in.

### Proposing Bonus Tasks:
**Please send your proposal of bonus tasks by email to BOTH TAs (Lili Wei and Victor Tian) by November 11th 23:55 using your CSD or ITSC account.**

In your bonus task proposal, please include:
1. A detailed introduction to the task you would like to propose.
2. The Java concepts and techniques that you think will be used to implement the task.
3. Some testing procedures to test correctness of the implemented task.

We will evaluate your proposals and approve several bonus tasks from the proposals collected. The approved bonus tasks with their associated bonus points will be released by **Nov 13th**. Please stay tuned with the updates on our course website. You may also implement the approved bonus tasks proposed by your classmates to get bonus.

**NOTE: If any of your proposed bonus tasks are approved by the TAs, you may get 1 bonus point for each approved task and each classmate may get up to 3 bonus points for this. In case there are two very similar bonus tasks proposed, we will give credit only to the classmate who propose the task first.**

### Submitting Bonus Tasks
Your code of the bonus tasks should be included in your project code. In your report, you should describe:
1. Which bonus tasks did you implement.
2. In which part of your code (i.e., classes and methods) did you implement the bonus tasks.
3. The procedures to start your bonus tasks (Note: different students may implement bonus tasks in different ways so please specify how we can enter/exercise the bonus tasks in your implementation).