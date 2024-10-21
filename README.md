# audio reversal tool
 
This program takes an input file (.dat) and reverses it using both a stack (ArrayList implementation and LinkedList implementation).

The input file is converted using SoX converter and running these commands on the terminal (example uses file.wav as the input file):

``
sox file.wav inputfile.dat
``

inputfile.dat is then used in the program, through command line arguments.

SoX can be downloaded using Homebrew on Mac by running: 
``
brew install sox
``


