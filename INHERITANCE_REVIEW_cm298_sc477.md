# INHERITANCE REVIEW

CALVIN MA AND SUSIE CHOI

* Part 1
There are some methods are not needed by other classes when this current class is called, so we are planning to 
encapsulate these methods. For example, when checking for neighbors, this method will be private within the class 
because other classes will not need the results. 

We are planning on using different cell classes and a superclass for cells to consider repeated methods that will
be used across all of the cells. My partner decided on this design implementation because they decided they only
needed to differenciate between game within the cell class. To determine the rules, my partner's design is based on
the cell classes passing arguments that will determine the game type. 

In partner's grid class, the only thing that is open is the constructor and the update grid method. The grid method
for setting neighbors for each cell is closed because the GUI doesn't need to know anything from that method. Polymorphism is helping both of our groups to minimize duplicated methods (lots of getters and setters are repeated). 

Errors in checking movement, trying to stay within the bounds of our "grid" because the cells class doesn't contain a coordinate location. To fix this, we would either have to give the cell class a private instance variable(s) indication a coordinate, or add extra methods to indicate to the game class of coordinates. Partner issue: Is there no available empty cells, which would hinder movement. To handle this, there can be pop-up for the user to change starting size or a method made to self-fix the coordinates. 

The design seems pretty good - with the polymorphism, there is a lot of flexibility within both of our projects to make changes and help debug! Only need to create cell subclass and then the properties are easy to create. The overall design makes it very simplified and my partner's group would only have to mess around with a few classes. 

*Part 2

Main needs the splash screen class. Main needs grid class to display the cells. The grid is composed of a 2-d cell array, and the cell are what are displayed on the screen. 

The grid class depends on cells to properly update themselves, but it doesn't call on the cell methods besides the update method. This is good because there will not be a lot of dependencies between classes. 






