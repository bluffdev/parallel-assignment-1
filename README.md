# Assignment 1
The first assignment

## Build Steps

### Gradle
Run one of the scripts in the root directory 
<br /> 
Linux/MacOS: `./gradlew run`
<br />
Windows: `.\gradlew.bat run`

### Without Gradle
Inside the prime directory run:
<br />
`javac *.java`
<br />
`java Prime`

## Summary

### Effeciency
This is an O(sqrt(n)) algorithm for determining if a number is prime. Any input that is either odd or divisible by 3 will return false. A range of numbers is determined by taking the square root of the input value. A loop tests values within the range and increments by 6 each iteration.

The threads share a counter which uses Java's AtomicInteger. This allows for mutual exclusion to be maintained when the threads need to read or write to the counter.