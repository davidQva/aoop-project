# AOOP Tile Games Framework

## Overview

This project was developed as part of the Advanced Object-Oriented Programming (AOOP) course at Halmstad University. The objective was to create a reusable framework for tile-based games, such as **Sokoban** and **Snake**, using the **Model-View-Controller (MVC)** design pattern to ensure ease of use and reusability. The framework allows for the development of additional tile games by reusing existing code components.

## Features

- **MVC Architecture**: The framework is based on the MVC pattern to separate concerns and improve maintainability.
- **Observer Pattern for Input**: Allows for decoupled input handling, ensuring flexibility in handling multiple types of inputs.
- **Strategy Pattern for Input**: Encapsulates different input strategies, making it easy to switch between keyboard, mouse, or other inputs.
- **Prototype Pattern for Tiles**: Reusable tile instances are created using this pattern, improving memory management and simplifying tile creation.
- **Memento Pattern for Save/Load**: Enables saving and loading game states, with a flexible method for handling variable-sized game boards.

## Design Patterns Used

1. **MVC Pattern**: The core architecture for organizing the game logic, user interface, and input handling.
2. **Observer Pattern**: Used for managing inputs and other events, decoupling components of the game.
3. **Strategy Pattern**: Employed for handling different types of inputs such as keyboard and mouse.
4. **Prototype Pattern**: Simplifies the creation of tile objects by cloning prototype instances.
5. **Memento Pattern**: Facilitates saving and restoring game states during gameplay.

## Games Supported

- **Sokoban**: A classic puzzle game where players push boxes to specific locations.
- **Snake**: A game where players control a snake that grows longer as it consumes food.

## How to Use

1. Clone the repository:
2. Run src/application/StartGames.java
