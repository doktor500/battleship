 README
========

## Requisites
- Java 1.7
- Groovy 2.3.1
- Gradle 1.10
- Preferred IDE/Text editor
- Internet connection

## Basic commands
- `$ gradle test`: run tests (Unit & Integration)
- `$ gradle build`: builds the project
- `$ java -jar build/libs/battleship-spring-0.1.0.jar`: runs the server

## Considerations
- A ship can appear on the other side of the board when moving. Example: given a board of (3, 4) when a ship is at (0, 0, S) and moves, it goes to (0, 4, S).
- I have considered that each line does not have white spaces at the begining / end of it.
- See specifications for more documentation.

Point your browser to http://localhost:8080/battleship/exercise to see it working!
