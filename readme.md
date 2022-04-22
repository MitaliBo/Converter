# Markdown to HTML converter

This is the command line program which converts small subset of Markdown tags to their corresponding HTML one. The command line arguments are the input and output file location.

The tags that it will take care are :

| Markdown                         | HTML                                              
| ---------------------------------| --------------------------
| `# Heading 1`                    | `<h1>Heading 1</h1>`                               
| `## Heading 2`                   | `<h2>Heading 2</h2>`                               
| `...`                            | `...`                                              
| `###### Heading 6`               | `<h6>Heading 6</h6>`                               
| `Unformatted text`               | `<p>Unformatted text</p>`                          
| `[Link text](https://www.example.com)` | `<a href="https://www.example.com">Link text</a>` 
| `Blank line`                     | `Ignored`                                         


## Build and Run locally

This project was built with `Java 1.8` and `Maven 3.8.5` so make sure that these are installed.
This uses maven assembly plugin which will package the project in executable jar. 

Go to root of the Project and run below command which will build, run test cases and package the project in jar along with dependencies :

```
mvn clean package
```
The above command will create jar file inside **target/converter-0.1.0-SNAPSHOT-jar-with-dependencies.jar**. Run below sample commands to run program as command line by giving the absolute path of file.

```
java -jar ./target/converter-0.1.0-SNAPSHOT-jar-with-dependencies.jar <input file> <output file>

java -jar ./target/converter-0.1.0-SNAPSHOT-jar-with-dependencies.jar ./resources/Sample1.md ./resources/Sample1.html
```

If you want to skip running the test :

```
mvn clean package -DskipTests=true
```

Running tests :

```
mvn test
```

For code coverage jococo plugin is used which outputs the report in /target/site folder on running the target mvn test.
 
