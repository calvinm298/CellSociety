# cellsociety 

August Ning, Marcus Oertle, Brandon Dalla Rosa

### Inheritance Review Discussion

I worked with Brandon and Marcus about the best ways to parse XMLs.

* Part 1
1. What is an implementation decision that your design is encapsulating (i.e., hiding) for other areas of the program?
Other classes cannot access the XML documents, the XML parser will have getter methods that will pass the information to other classes.
1. What inheritance hierarchies are you intending to build within your area and what behavior are they based around?
The XML parser for each simulation type will extend the XMLParser super class which will already parse basic information common between each kind of XML file for the simulation.
1. What parts within your area are you trying to make closed and what parts open to take advantage of this polymorphism you are creating?
The XML file is open, but the parser is closed since the parser depends on the structure of the XML file
1. What exceptions (error cases) might occur in your area and how will you handle them (or not, by throwing)?
A file not found, but also if the user improperly structures the XML file so the parser can't parse the data out of a node
1. Why do you think your design is good (also define what your measure of good is)?
My design uses inheritance and keeps code DRY, since we know every simulation has to some amount of the same information, so you can call super to get that information for you, and the specific information can be coded only once in the subclass for each kind of simulation.
* Part 2
1. How is your area linked to/dependent on other areas of the project?
XML files import the information the user has specified, and passes the initial configuration to the game class.
Are these dependencies based on the other class's behavior or implementation?
They depend mostly on the XML files, and sometimes on the getter methods that pass the information into the game.
1. How can you minimize these dependencies?
Most of the dependencies are due to user errors setting up the XML file.
1. Go over one pair of super/sub classes in detail to see if there is room for improvement. 
Keep code more DRY, and make the code more readable. Throw better errors
1. Focus on what things they have in common (these go in the superclass) and what about them varies (these go in the subclass).
Superclass reads the information that is common among all file configurations, and then the subclass parses specfic information for each simulation.
* Part 3
1. Come up with at least five use cases for your part (most likely these will be useful for both teams).

1. What feature/design problem are you most excited to work on?
Throwing good errors for other teammates so they can understand what when wrong.
1. What feature/design problem are you most worried about working on?
Getting all the information to to "talk" with other parts of the game.