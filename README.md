# multiplayerUNO
Java Implementation of UNO Card game with GUI and TCP multiplayer functionality.
Please read the game instructions pdf if you're unfamiliar with the card game rules.
Enjoy!

## Download
To run this game on your local machine, open command line and navigate to where you want to save this project. Then run this command:

`$ git clone https://github.com/anthonyc1/multiplayerUNO.git`

## Run
For Mac/Linux users, here's a neat trick you can do on command line to compile multiple java files with a few short commands:
*Note: make sure you're in the directory will all the java files you need to compile*
`$ find . -name "*.java" > sources.txt`
`$ javac @sources.txt`

For Windows users, the equivalent commands are:
`> dir /s /B *.java > sources.txt`
`> javac @sources.txt`

And presto! You can now run the main file:
`$ java Game`

----

Copyright © 2017 Anthony Chan. All rights reserved.

You need written permission if you wish to modify any part of the code and include it in your own work.
