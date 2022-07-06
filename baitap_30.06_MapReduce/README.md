# Hadoop-MapReduce Application
## Structure of folders
> WordCount_Job
1. Requirement
   - Write MapReduce job to count the number of occurrences of a word in a text file.
   - Save a text file in each line in the format: <word>,<frequency>
   - Note :  each word is separated by " ". Remove marks and special characters.
2. Structure of folders
   - Main file : src/main/java/com/huyvv20/WordCount.java
   - Jar file : out/artifacts/WordCount_jar/WordCount.jar
3. Running steps
   ```
    Step 1: Edit Configurations
    Step 2: Edit Build and run 
      - Java 8 
      - Main class : com.huyvv20.WordCount
      - Program arguments : <input file> <output file>
    Step 3: Save and Run WordCount
   ```
> CountDistinct_Job
1. Requirement
   - Have a set of integers > 0
   - Write MapReduce job to distinctness of this integer set.
   - Note : The input is a text file with numbers separated by the line down sign.
            Output in the form (0,count), with count being the number of numbers included 
            in the integer set after it has been distinct.
2. Structure of folders
   - Main file : src/main/java/com/huyvv20/CountDistinct.java
   - Jar file : target/CountDistinct-1.0-SNAPSHOT.jar
3. Running steps
   ```
    Step 1: Edit Configurations
    Step 2: Edit Build and run 
      - Java 8 
      - Main class : com.huyvv20.CountDistinct
      - Program arguments : <input file(csv file)> <output file1> <output file2>
    Step 3: Save and Run CountDistinct
   ```
> JoinTable_Job
 1. Requirement
   - Have 2 tables as a .csv file. Use mapreduce to join 2 table people and salary through the "job" field.
2. Structure of folders
   - Main file : src/main/java/com/huyvv20/JoinTable.java
   - Jar file : out/artifacts/JoinTable_jar/JoinTable.jar
3. Running steps
   ```
    Step 1: Edit Configurations
    Step 2: Edit Build and run 
      - Java 8 
      - Main class : com.huyvv20.JoinTable
      - Program arguments : <input file1(csv file)> <input file2(csv file)> <output file>
    Step 3: Save and Run JoinTable
   ```
