 
# Project: MultiThreaded Library

* Author: Justin Raver

## Overview

This program creates a Java GUI for a simple Library. This 
library allows you to load a file from csv and view the corresponding 
books. It now supports a serial book processor that shows book word 
stats and allows the user to input a list of invalid words to ignore.

## Compiling and Running

From src directory:
 
From the directory containing the .java source code, compile the driver class:
    $ javac Main.ReaderOfBooks.javac

Run the program
    $ java Main.ReaderOfBooks
 
## Reflection (Project Development and Testing)

This project was originally an assignment for my CS121 class. I have now extended it 
to add functionality. Currently the project supports a GUI that loads books for 
the user to view and has a non-functional Book stats panel. 

My intention is to take the serial book processor that I have created, optimize it, and then 
parallelize it. I will be timing both the serial and parallel portions to model the speedup
that occurs and use it as a learning exercise.

## Testing

In this project version I have implemented unit tests using JUnit to further my knowledge
of the testing framework. So far I have found the framework to be very straight forward  
and helpful especially when running into issues refactoring existing code. These tests
will be very useful while transitioning the code from serial to parallel. 

## Sources used

No outside sources were used