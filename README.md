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