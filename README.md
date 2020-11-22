# Console interface for university

Console application for obtaining information about departments and lecturers <br>
from the database.

### Technologies used:

+ Java 8
+ Spring Boot
+ H2
+ JUnit 5, Mockito
+ Lombok
+ Maven 
+ Checkstyle plugin

### To start the project you need: 

##### Fast start
$ git clone https://github.com/aleksandr-hrankin/test-task-university-console.git
$ cd test-task-university-console
$ mvn spring-boot:run

##### Start in IDE
1) *Download and install* the [JDK](https://www.oracle.com/java/technologies/javase-downloads.html, "Download JDK") <br>
2) *Download and install* the [IDE Intellij Idea](https://www.jetbrains.com/ru-ru/idea/download/#section=windows, "Download IDE") <br>
3) Clone this project
4) Add Run/Debug Configurations or File -> Invalidate Caches / Restart ...
4) Run a project

### Command list
[h] - help <br>
[avg] - show average salary for the department <br>
[d-head] - show head of department <br>
[d-stats] - show department statistic <br>
[s] - global search by template <br>
[d-num] - show count of employees for department <br>
[back] - come back <br>
[q] - quit <br>

### Data
Data about departments and lecturers is created in the ua.antibyte.application.service.inject.DefaultDataInject class. <br>
5 departments are created by default:
+ [math] - department of mathematics
+ [physic] - department of physics
+ [chem] - department of chemistry
+ [program] - department of programming
+ [bio] - Department of Biology

Lecturers for departments are created randomly
