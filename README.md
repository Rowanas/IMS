Coverage: 64.7%
# Week 5 IMS Project

This is my week 5 solo project on the QA training course. It involved setting up a database with tables for customers, items and orders, and necessitated a go-between table, order_items to handle an items-orders many to many relationship. Code to produce a variety functions was needed through Java, and connections required to link the two.

## Getting Started

I have compiled my code into a FatJar for your convenience. You should only need to open up the file wherever you have downloaded it, and double click on the .jar file.

### Prerequisites

You will require SQL Workbench to visualise the information in my sql-schema and sql-data files, though you can open these in your preferred IDE if you prefer. For testing, Maven will be required. gitBash required to build in bash or console.

### Building

To build my code, you should be able to enter the IMS-Starter project, and open a gitBash terminal. Type in "mvn clean package" to build a new fatjar, and run with only the commands "java -jar ims-0.0.1-jar-with-dependencies".  This will open my java executable in a console for you to interact with. All methods function, and all options are supported by robust user-error capture and return.

## Running the tests

In order to run tests for my code, you can open the code in your preferred IDE and navigate to the top level of the src/test/java file. In Eclipse, you can then "Launch java" and it will run automated tests. For coverage details, you will also need to view coverage history, which in Eclipse, is in the Run dropdown.

### Unit Tests 

I tested all of my core files. There are basic tests for my Customer, Order and Item classes, plus further tests for the CRUD functions in the Controllers and additional tests for each of my methodes in the DAOs for each class.

In each case, they test that the output of my method matches the expected result in order to check that the method is performing its function correctly.

## Built With

* [Maven](https://maven.apache.org/) - Dependency and Build Management
* [Jira](https://atlassian.net/jira/) - Kanban board and epic/stories visualisation
* [Git](https://gitforwindows.org/) - Gitbash functionality for command line interface
* [Github](https://github.com/) - Repository management and integration with Jira for automatic Jira update

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Rowan Baker** - *Finished Project and additional resources* - [Rowanas](https://github.com/Rowanas)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

## Acknowledgments

* As will likely be the case for any of my work going forward, a huge tip of the hat to the stackExchange community, whose years of questions provided many useful fixes.
* To Earl for teaching me many of the background technologies I used, and Pawel, whose skill and patience has turned Java from a language I despise into a language I can use.
* To the companionship and good humour of the rest of the 22AprEnable2 cohort, without which I would not have been able to push on to the project's end.
