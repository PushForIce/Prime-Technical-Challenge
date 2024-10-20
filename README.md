# Prime-Technical-Challenge
Usage: When running the program you will first be prompted for a username. A username can contain letters and/or numbers but
not special characters (e.g. '@', '#', etc.). After inputting a valid username, you will then be prompted for a numeric sequence.
A numeric sequence should contain only numbers, with no whitespace characters (e.g. '12345' is valid, '1 2 3 4 5' in invalid.)
and be a maximum of 8 digits long. The program will then split the numeric sequence and generate all possible sequential numbers 
from the numeric sequence (e.g. 12345 -> '1, 2, 3, 4, 5' -> '1, 2, 3, 4, 5, 12, 123, 1234, 12345, 23, 234, 2345, 34, 345, 45). 
After this, the program then calculates all prime numbers from the generated list, then saves them to a file and cache, 
and prints the result to the console.