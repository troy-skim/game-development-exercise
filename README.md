# Game Development

Develop a game using object-oriented programming in Java and the Processing framework.

The goal of this assignment is to give you hands-on experience writing object-oriented code in Java.

## Authors

This game was created by:

- `Your Name Here` (`Your GitHub ID Here as a link to your GitHub profile page`)

## Requirements

### Collaboration

You may work with up to one other student on this project.

- Edit the [Authors](#authors) section above to clearly the name(s) and GitHub ID(s) of all authors in the bullet-pointed list.

- If you do work with another student, your work must reflect the level of quality one would expect from two people.

### Documentation

You must document your code using [javadoc comments](https://knowledge.kitchen/Documenting_source_code_using_Javadoc) for all classes, properties, and methods.

### Custom classes

Your code must include custom classes that represents each visual object in your game. Each class must be in its own file.

- If your game requires multiple objects instantiated from any single class, you may want to hold those objects in an array.

### Overloaded constructors

One of your custom classes must contain at least 2 overloaded constructor methods.

### Object orientated principles

- Your code must follow the principles of **abstraction** and **encapsulation**.
- Keep data fields **private**, and use **getter** and **setter** methods for any data fields that require external access.
- The setters should perform **validation** to only assign "reasonable" values to instance properties.
- Use the `this` keyword when referring to instance properties or methods within the class they belong to (e.g. `this.foo = 5;`)
- Use the class name when referring to static properties or methods (e.g. `Foo.bar();`)

### Interactive

Users must be able to interact with your game in some way and have the game visualization react meaningfully. Common interactions include clicking, hovering the mouse over a part of the visualization, or typing on the keyboard.

- When a user presses a key, Processing will automatically call any method named `keyPressed()`. The `key` instance property will automatically be assigned the key that was pressed, as as `char` value.
- When a user moves, clicks, or drags the mouse, Processing will automatically call any method named `mouseMoved()`, `mouseClicked()`, or `mouseDragged()`, respectively. The `mouseX` and `mouseY` instance properties will automatically hold the position of the mouse, measured from the top-left origin of the window.

## Example

Imagine you are recreating the classic arcade game, [Asteroids](<https://en.wikipedia.org/wiki/Asteroids_(video_game)>), where asteroids are floating around, and the user moves a spaceship that shoots the asteroids and blows them up.

The following is an example only. You are hereby **forbidden** from making the game asteroids for your own assignment. Pick a game of _your_ choosing.

- You would create a class called something like `PlayAsteroids`, where you include the main logic of the game and where you instantiate all the other objects needed to play the game.

  - this class would inherit from `PApplet`, a class provided in the Processing framework's `core.jar` file
  - this class would override PApplet's `settings()`, `setup()`, and `draw()` methods
  - this class would instantiate all the `Asteroid` objects needed for the game and store them into an array (or better yet, an `ArrayList`).

- You would create a custom `Asteroid` class that is used to instantiate each asteroid in the game

  - each asteroid would have instance variables representing its diameter, speed, x location, y location, etc.
  - each asteroid would have encapsulated methods to update its own position and draw itself to the screen

- You would create a custom `Spaceship` class that represents the spaceship.

  - each spaceship would have instance variables representing its speed, orientation, x location, y location, etc.
  - each spaceship would have methods to rotate, thrust, and shoot.
  - each spaceship would have encapsulated methods to update its own position and draw itself to the screen

- You would create a custom `Bullet` class that represents each bullet that the spaceship might shoot.
  - each bullet would have instance variables representing its speed,
  - each bullet would have encapsulated methods to update its own position and draw itself to the screen
  - each bullet would have an encapsulated method to check whether it has collided with any of the Asteroid objects.

..etc...

## Code hints

### Example of creating an array of custom objects from within your main program

Using a regular array:

```java
//initialize an array of your custom data type
Asteroid[] asteroids = new Asteroid[200];

//loop through the existing array and create an Asteroid object to hold at each position in the array
for (int i=0; i<asteroids.length; i++) {
    //instantiate a Asteroid and store at this position in array
    Asteroid asty = new Asteroid( "put your class's required constructor arguments here" ); //create an Asteroid object
    asteroid[i] = asty; //add it to the array
}
//etc...
```

### Alternate example of creating an ArrayList of custom objects from within your main program

Using an `ArrayList`

```java
//initialize an ArrayList of your custom data type
ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>(); //create an array list

//loop a fixed number of iterations, and create a Asteroid object and at it to the ArrayList with each iteration
for (int i=0; i<200; i++) {
    Asteroid asty = new Asteroid( "put your class's required constructor arguments here" ); //create an Asteroid object
    asteroids.add(asty); //add it to the ArrayList
}
//etc...
```

### Example of looping through a regular Array and making all objects stored there draw themselves to the screen

This example assumes you have a regular array named asteroids that stores Asteroid objects.

```java
//loop through all Asteroid objects and make them appear on the screen
for (int i=0; i<asteroids.length; i++) {
     Asteroid asty = asteroids[i]; //get a pointer to point to the current object
     asty.show(); //make this Asteroid draw itself to the screen... obviously you need this show() method to exist in your Asteroid class
}
```

### Example of looping through an ArrayList and making all objects stored there draw themselves to the screen

This example assumes you have an ArrayList named asteroids that stores Asteroid objects.

```java
//loop through all Asteroid objects and make them appear on the screen
for (int i=0; i<asteroids.size(); i++) {
     Asteroid asty = asteroids.get(i); //get a pointer to point to the current object
     asty.show(); //make this Asteroid draw itself to the screen... obviously you need this show() method to exist in your Asteroid class
}
```

### Extra credit

Have one of your custom classes inherit from another custom class. This inheritance should be used meaningfully such that the object that inherits makes use or overrides the inherited methods or properties in a useful way.

## Folder structure

This project has several important directories:

- `src` - contains the Java source code for the project (i.e. `.java` files)
- `test` - contains code that will help us determine whether the code you have written works correctly. Do not touch this directory!
- `bin` - contains the compiled code (i.e. `.class` files)
- `lib` - contains any dependencies (other libraries of code that the project depends upon to work)

If your project has no dependencies and has not been compiled, you may not see the `lib` or `bin` directories.

## How to submit this assignment

Once you have completed the changes to th assignment, you are ready to submit it. Do this from within Visual Studio Code.

1. Click on the `Source Control` icon in the left activity bar in Visual Studio Code.
1. In the Source Control side bar, you will see a field named `Message` - type in a unique message about what you have done, e.g. "_Finished assignment!_" or whatever you want to write as a short note to yourself.
1. Hover over the words `Source Control`. You will see a `...` icon appear - click it to see a menu. In that menu, click `Commit`->`Commit`. This logs the changes you've made to the Git project - remember Git is used to keep track of changes.
1. Go to the same menu and click `Push` to submit your assignment - this uploads your updated files to the copy of your respository on GitHub.

![Push changes to GitHub](./images/how_to_push_changes_to_github_from_vscode.png)

That's it... you're done.

## Double-check your submission

Prove to yourself that you have correctly submitted by viewing your repository on the GitHub website - you should see your completed README.md file there.

## Resubmit as many times as you want

You can re-submit as many times as you want before the deadline. Just make changes to the files on your own computer and repeat the process outlined above to upload them to GitHub.
