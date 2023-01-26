# Assignment 1
The first assignment

## Build Steps

### Gradle
Run one of the scripts in the root directory (it will take a few seconds longer the first time the script is run)
<br /> 
Linux/MacOS: `./gradlew run`
<br />
Windows: `.\gradlew.bat run`
<br />
This will create an output.txt file in the app directory.

### Without Gradle
Inside the directory `app/src/main/java` run:
<br />
`javac prime/*.java`
<br />
`java prime.Prime`
<br />
This will create an output.txt file in the current directory

## Summary

### Effeciency and Correctness
This program uses an O(sqrt(n)) algorithm for determining if a number is prime. Any input that is either odd or divisible by 3 will return false. A range of numbers is determined by taking the square root of the input value. A loop tests values within the range and increments by 6 each iteration. Java's AtomicInteger and AtomicLong are used for sharing the value of the counter, prime count, and the sum of the primes between all of the threads. The AtomicInteger counter allows for mutual exclusion to be maintained between the threads when reading and writing to it. I tested the correctness of my approach by implementing a sequential version first and then comparing their runtimes.