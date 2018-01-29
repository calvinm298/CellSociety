CS 308 Cell Society Team 12 Design Plan
===================

### Introduction
Our goal is to make a Cellular Automata simulation program that can run various type of CA rules such as Conway’s Game of Life, Schelling’s Model of Segregation, Wa-Tor, etc. We want to create an interface that allows users to run all these types of simulations through XML input files specifying the type of simulation. 

The user has access to a GUI that allows him or her to upload the configuration files, effectively altering the grid and the placement of the cells, and can control the speed of the simulation after the simulation has started. He or she can also reset the level from the simulation screen by uploading a configuration file. The simulation logic will be controlled by each of the block classes’ logic, but the user will not be able to access any of the methods used to determine the rules of each CA simulation - that is predetermined based on the type of CA that user wants to run. 

### Overview

### User Interface
The user will mostly be interfacing with the splash screen, as the other parts are Cellular Automata simulation that the user can control the step speed of. The splash screen will allow the user to upload a data file which will contain the necessary information to run the simulation (Title, author, grid dimension, initial configuration). Once you start the simulations, the user will have speed control, a stop, start, and reset button. Users can reset from the same configuration or a different data file.

Error checking can be added to make sure that the grid size is valid, and that the cell data is correct for the game type specified, and that they can properly constructed for the game. The simulation will be mostly hands off from the user.

![Interface figure](interface.png)
### Design Details

### Design Considerations
Where we will put methods to move cells, update simulation frame, either in the superclass or in the subclass for the game. How we plan to extend each class to implement each different type of simulation. We also need to consider how to most efficiently implement cells either as their own abstract class and implement them in the simulation class, or as a specific cell type class for each simulation. We will have to see how we want to implement the cell and what methods we would want each type of cell to have, and then consider if each type of cell is similar/different enough to warrant a superclass or not.

What the correct rules for each game are - for each game, we will have to determine the rules of how objects move around and how they interact with each other. Basically, what the objects DO between each step. This will vary between each game. We will have a superclass of games which will include an abstract method for rules, letting us edit the rules of each game without interfering with the other games. 

How we are going to scale each cell, since the user gets to define the grid size but the window size remains the same. We want to use a cell class that will take care of displaying the simulation pieces (game of life piece, predator, prey, fish, shark, etc), but then when creating the cells, we will have to make them the right size, and make the displayed cells scale. 

What the best way will be to parse the information from the XML file, and what data we should put in the XML that the user can specify, and what we should put in a file manager class that will set up the simulation. We will want to decide what parameters we will set by default, and makes it easy for the user to set up the game without have to specify each block in a large grid. We also have to decide how much control the user will have to control the parameters of the simulation (if they can decide speed, default block type, etc) from the XML file.


### Team Responsibilities
All members of the group will constructively work together to implement the three cellular automata simulations described in the link assignments of the project page: Schelling’s model of segregation, Wa-Tor World model of predator-prey relationships, and Spreading of Fire. Work will be divided up among the members based on class implementation. This approach for the first three CA simulations ensures that the basic requirements are met. To exceed these requirements, we will extend the plan to include at least 2-3 additional simulations. After breaking down the class structure and concepts for each of these simulations, including inheritance relationships between superclasses and subclasses, grid cell object parameters, motion control, and neighbor checking requirements, each member will be responsible for implementing one of the simulations independently. Throughout the project, collaboration and collective consent will be key, especially for high-level implementation of multiple class dependencies.
