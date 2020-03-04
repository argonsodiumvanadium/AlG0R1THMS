# StringSpaceShrinker_BETA
well this was the first idea I had and it worked pretty well, this shrinks a String upto 6 times in memory
### what this does
well it takes a string and re writes the whole String taking way less amount of bits than it did before [UPTO 8 TIMES LESS] but this differs from sentence to sentence <br>

### how to use it<br>
there is a file given with the code named Shrinkable.txt, now enter the String in thst file and then call the program and then let the program do the magic

### how it does it
well I rush typed this algorithm in 3 to 4 hours so I dont expect you to Understand the program but I can tell you what it is at heart<br><br>
##### Now 
Basically this program takes a String then stores each character in an array then it takes out the mode after mode or basically arranges it in desending order, then it creates an array "heirarchy" which stores all the characters from the highest to the lowest occurance like per say my String is<br>"Argon is a noble gas"<br>then the first index will hold (' ') and then the second will hold ('a') and so on then another function will get binary values like this<br>0,1,00,01,10,11 ....<br>now you must be wondering how will I differentiate between 01 and 1 because both have the integer value 1, I will find their area, that is 1 will take 1 bit will 01 will take two so this will be a diffrentiating factor. Now I have the binary values and the hierarchy next I will assign the 1 bit values to the top 2 highest occuring charachters then 2 bit values to the next top 4 occuring characters and 3bit values to the next top 8 occuring values and so on....<br>the bit proccessing and interpreter cannot be build on java due to it being type specific but this is the theoretical part of the code which can be written in a low level language such as C,C++ or RUST.
<br><br>
#### Example<br>
```cmd
Previous String :- Java is a high-level programming language originally developed by Sun Microsystems andreleased in 1995. Java runs on a variety of platforms, such as Windows, Mac OS, and thevarious versions of UNIX. This tutorial gives a complete understanding of Java
Space taken :- 4016 bits


Shrinked String :- 0111.1.011.1.0.10.01.0.1.0.0000.10.101.0000.1100.010.00.011.00.010.0.0100.001.000.101.001.1.0001.0001.10.11.101.0.010.1.11.101.111.1.101.00.0.000.001.10.101.10.11.1.010.010.0101.0.100.00.011.00.010.000.0100.00.100.0.00110.0101.0.1011.111.11.0.1010.10.0010.001.000.01.0101.01.110.00.0001.01.0.1.11.100.001.00.010.00.1.01.00.100.0.10.11.0.1101.1001.1001.1110.1000.0.0111.1.011.1.0.001.111.11.01.0.000.11.0.1.0.011.1.001.10.00.110.0101.0.000.0011.0.0100.010.1.110.0011.000.001.0001.01.0110.0.01.111.0010.0000.0.1.01.0.00100.10.11.100.000.00111.01.0110.0.1010.1.0010.0.00001.1011.0110.0.1.11.100.0.110.0000.00.011.1.001.10.000.111.01.0.011.00.001.01.10.000.11.01.0.000.0011.0.00011.00000.1111.00101.1000.0.00010.0000.10.01.0.110.111.110.000.001.10.1.010.0.101.10.011.00.01.0.1.0.0010.000.0001.0100.010.00.110.00.0.111.11.100.00.001.01.110.1.11.100.10.11.101.0.000.0011.0.0111.1.011.1.
Space taken :-629 bits
```
#### Developer's notes<br>
well the algorithm is gonna be slow it is pretty balanced but I feel it could have been improved but it has insane shrinking capabilities
