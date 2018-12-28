# RC - 2017/2018
Computer Networks Project

## Requirements
You must have installed the following tool:
- Java Development Kit 7+ (JDK 7+)
The code was written in Java and is compatible with JVM 1.7.

## How to run
Execution is done in the current directory as specified by the project:

### Getting Started
Compiling is done upon calling the command. Executables are deleted automatically upon exit. Makefiles inside the three applications can also be used to get an executable Jar.

Additionally, it might be required to set the premissions of the scripts with the following command:
```
	chmod u+x user CS WS
```
All input and output files in each application are located inside their respective folders.
This means that input files for the user application should be placed inside (or with a path relative to) the UserApplication directory.

### User Application:
```
	./user [-n CSname] [-p CSport]
```
### Central Server:
```
	./CS [-p CSport]
```
### Working Server:
```
	./WS PTC1 ... PTCn [-p WSport] [-n CSname] [-e CSport]
```
