Compile project using "mvn compile" to generate Employee and Salary class.
Schema : src/main/avro
Employee Entity has 6 fields : name(String), id(int), sex(String), age(int), address(String), salary:netSalary(int), tax(int).
2 class:
    Serialize : write to schema.
    Deserialize : read from schema and print.