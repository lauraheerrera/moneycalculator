# Money Calculator

**Money Calculator** is an application that performs real-time currency conversions using data from two external APIs: one for exchange rates and another for available currencies. The application follows the **Adapter design pattern** and the **MVC (Model-View-Controller)** architecture to ensure modularity, scalability, and separation of concerns.

## Features
- Real-time currency conversion.
- Integration with external APIs to fetch updated exchange rates and available currencies.
- Implementation of the Adapter pattern to simplify integration with various data sources.
- MVC-based architecture for better organization and maintainability.
- Modular and extensible code to allow easy addition of new features and services.

## Technologies Used
- **Language**: Java
- **APIs**:
  - [Exchange Rates API](http://data.fixer.io/api/symbols?access_key=)
  - [Currencies API](https://api.exchangeratesapi.io/v1/latest?access_key=)
- **Design Pattern**: Adapter
- **Architecture**: MVC (Model-View-Controller)

## How to Run the Program

1. Download the ZIP file containing the project.
2. Extract the contents of the ZIP file to a directory of your choice.
3. Open the project in your preferred Java IDE or run it directly from the command line.
4. Execute the `Main` class to launch the application.
5. The program will prompt you to enter the following details:
   - **From Currency**: Enter the currency code you want to convert from.
   - **To Currency**: Enter the currency code you want to convert to.
   - **Amount**: Enter the amount of money you wish to convert (you can also use the numeric keypad to select the amount).
6. The program will fetch the latest exchange rates and perform the conversion, displaying the result in the console.


## Dependencies

This project uses the following external libraries:

### Jsoup (org.jsoup:jsoup)
- **Version**: 1.18.1
- **Description**: Jsoup is a powerful library for working with HTML in Java. It provides an easy-to-use API for parsing, extracting, and manipulating HTML documents. It is particularly useful for web scraping and handling HTML content, even when the HTML is malformed.
- **Usage**: It is used primarily for processing and extracting information from web pages.

### Gson (com.google.code.gson:gson)
- **Version**: 2.10.1
- **Description**: Gson is a library from Google that simplifies the conversion between Java objects and their JSON representation. It allows you to serialize Java objects into JSON and deserialize JSON back into Java objects, making it ideal for working with JSON data in Java applications.
- **Usage**: It is used for serializing and deserializing JSON data, whether for exchanging information with web services or storing configuration and data.
