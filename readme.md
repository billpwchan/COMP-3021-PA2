# COMP3021 PA2: Sokoban
In PA2, you will be implementing a GUI version of the Sokoban game from PA1. The core logic of the game remains more or less the same, but there are some differences that you should be aware of.

## Changes from PA1

* The input for PA2 program is a folder, which should only contain maps of different difficulty levels. The difficulty levels of the maps are indicated by their file name. Sokoban in PA2 will sort the map files in alphabetical order. You may refer to src/assets/maps as an example input.
* Crates no longer have unique IDs. Any crate can go on any destination. Therefore, all crates are now represented using the lowercase letter `c` in the map file.
* The ASCII characters in the map have now changed, to allow for more features. You don't need to know it to complete PA2.
* The `Game` class has been renamed `GameLevel`, to account for the fact that there can be multiple levels in a game. Now, the `LevelManager` class is responsible for managing the different levels.
* Graphics and audio

## Completed demo
A working PA2_obf.jar has been provided for your reference. Check out the materials from the PA2 introduction lab (lab7) for screenshots and instructions on how to run PA2_obf.jar.

## What you need to do
Your task is to complete the TODOs in the project.

**The detailed instructions and hints have been provided in the Javadoc comments of each method.**

Make sure you import the project in Intellij, don't just open it directly.

| TODO                                                                                            | Important Concepts                                |
|-------------------------------------------------------------------------------------------------|---------------------------------------------------|
| LevelManager::loadLevelNamesFromDisk                                                            | Streams, Predicates, Consumers                    |
| LevelManager::setLevel                                                                          | JavaFX properties                                 |
| LevelManager::startLevelTimer                                                                   | Timer, Threads                                    |
| LevelManager::getNextLevelName                                                                  | JavaFX properties                                 |
| GameplayInfoPane::bindTo                                                                        | JavaFX properties, bindings, lambdas              |
| *Pane::*Pane, *Pane::connectComponents, *Pane::styleComponents                                  | JavaFX                                            |
| *Pane::setCallbacks                                                                             | Lambdas, events, singletons, exceptions           |
| GameplayPane::doQuitToMenuAction, doRestartAction, createDeadlockedPopup, createLevelClearPopup | JavaFX, singletons                                |
| GameplayPane::renderCanvas                                                                      |                                                   |
| LevelSelectPane::promptUserForMapDirectory                                                      | DirectoryChooser                                  |
| AudioManager::playFile                                                                          | MediaPlayer, lambdas, threads                     |
| LevelEditorCanvas::LevelEditorCanvas, resetMap, renderCanvas                                    |                                                   |
| LevelEditorCanvas::setTile                                                                      |                                                   |
| LevelEditorCanvas::saveToFile                                                                   | File IO                                           |
| LevelEditorCanvas::getTargetSaveDirectory                                                       | FileChooser                                       |
| LevelEditorCanvas::isInvalidMap                                                                 | Lambda, streams, method references, JavaFX        |
| MapRenderer::render(Canvas, Brush[][]), render(Canvas, Cell[][])                                | Inheritance, polymorphism, Optional class, JavaFX |

## Final notes
* You may use your own earlier PA1 code or our model PA1 solution for PA2. But you cannot use the PA1 code of your classmates.
* You may reference our PA2_obf.jar for unspecified behavior in the provided documentation. You are welcome to further improve the reference program's GUI while preserving its functionalities.

## Bonus points policy
We will release the grading scheme of PA2 on our course webpage including specific bonus part of PA2.
Please stay tuned to the updates on our course webpage.

**Bonus scheme:** Be used as a "safety blanket" on the assignments capped by the maximum score (e.g. 100).

**Examples:**

* If a student got 95 out of 100 for the basic tasks, but he got 6 bonus points, the final score will be: MIN(95+6, 100) = 100
* If a student got 100 out of 100 and got 0 bonus points, the final score will be: MIN(100+0, 100) = 100
