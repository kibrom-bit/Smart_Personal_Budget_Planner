# Smart Personal Budget Planner

## Model-Driven Development (MDD) Process

This project employed a Model-Driven Development (MDD) approach to streamline the development process and ensure a robust system architecture. The process followed these steps, adhering to the MDD principles:

1.  **UML Diagram Creation:**
    * A comprehensive UML Class diagram was created using a software tool to visually represent the system's structure. This diagram defined the classes, their attributes, methods, and the relationships between them.
    * The Class diagram serves as a blueprint for the system, ensuring clarity and consistency in the design.
    * *Class Diagram:*
        * ![Class Diagram](MDD/class diagram/class_diagram.png)

2.  **Code Skeleton Generation (Eclipse EMF):**
    * Eclipse Modeling Framework (EMF) was used to automatically generate initial Java code skeletons directly from the created UML Class diagram.
    * This process leveraged EMF's capability to interpret the model and translate it into corresponding Java interfaces and implementation classes.
    * The generated code skeletons provided a foundational structure, reducing the amount of manual coding required.
    * The following Java classes were generated:
        * `UserManager` (from User Management)
        * `BudgetManager` (from Budget Management)
        * `ExpenseManager` (from Expense Tracking)
        * `SavingsManager` (from Savings Goals)
        * `UserInteraction` (for user input and output)
        * `DateUtils` (for date parsing)
        * `Main` (the application's entry point)

3.  **Business Logic Implementation:**
    * The generated Java classes were then extended with the specific business logic for the Smart Personal Budget Planner application.
    * This involved implementing the methods defined in the interfaces, adding algorithms for calculations, and handling data persistence.
    * The business logic implementation ensured that the application met the functional requirements defined in the initial design.

4.  **Documentation of the MDD Process:**
    * This `README.md` file serves as a comprehensive documentation of the MDD process used in this project.
    * It includes screenshots of the UML Class diagram, a detailed description of the tools used, and an explanation of how the generated code was extended.
    * This documentation aims to provide transparency and facilitate future maintenance or enhancements of the application.

5.  **Version Control (Git):**
    * The project's code, including the generated and implemented classes, was managed using Git.
    * This allowed for effective tracking of changes, collaboration, and version control throughout the development process, ensuring a structured and organized workflow.

## Running the Application and Tests

To successfully run the Smart Personal Budget Planner application and its JUnit 4 tests, ensure the following libraries are included in your project's classpath:

* **JUnit 4:** `junit-4.13.2.jar` - This library is essential for running the unit tests.
* **Hamcrest Core:** `hamcrest-core-1.3.jar` - Hamcrest provides matcher objects used in JUnit assertions.
* **Mockito (Optional):** `mockito-junit-jupiter-4.11.0.jar` - If you intend to use Mockito for mocking dependencies in your tests, include this library.

**How to Add Libraries:**

1.  **Download Libraries:** Download the necessary JAR files from Maven Central or other reputable sources.
2.  **Add to Classpath:**
    * **In an IDE (e.g., Eclipse, IntelliJ IDEA):** Add the JAR files to your project's build path or library dependencies.
    * **Using Ant (build.xml):** Ensure the libraries are placed in the `lib` directory (or the directory specified in your `build.xml` file) and that the `build.xml` file includes them in the classpath.
    * **Command Line:** When compiling and running your Java application and tests from the command line, include the JAR files in the classpath using the `-cp` or `--classpath` option.

**Example (Command Line):**

```bash
# Compile the application
javac -cp "lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mockito-junit-jupiter-4.11.0.jar;build" src/spbpp/*.java -d build

# Run the application
java -cp "lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mockito-junit-jupiter-4.11.0.jar;build" spbpp.Main

# Run JUnit tests
java -cp "lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mockito-junit-jupiter-4.11.0.jar;build;test" org.junit.runner.JUnitCore spbpp.DateUtilsTest