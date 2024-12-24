# Money Calculator

**Money Calculator** is an application that performs real-time currency conversions using data from two external APIs: one for exchange rates and another for available currencies. The application follows the **Adapter design pattern** and the **MVC (Model-View-Controller)** architecture to ensure modularity, scalability, and separation of concerns.

## Technologies Used
- **Language**: Java
- **APIs**:
  - [Exchange Rates API](http://data.fixer.io/api/symbols?access_key=)
  - [Currencies API](https://api.exchangeratesapi.io/v1/latest?access_key=)
- **Design Pattern**: Adapter
- **Architecture**: MVC (Model-View-Controller)


## User Interface

The application features a user-friendly interface with the following elements:

- **Currency Fields**: 
  - **From Currency**: Select or enter the source currency (e.g., USD, EUR).
  - **To Currency**: Select or enter the target currency (e.g., JPY, GBP).
  
- **Numeric Keypad**: A numeric keypad is available for entering the amount of money you want to convert. This allows for easy input using number buttons (0-9) and supports decimal entry.
  
- **Swap Button**: An intuitive **Swap** button, represented by an arrow icon, allows the user to quickly switch the source and target currencies without reselecting them manually.

- **Calculate Button**: Once the currencies and amount are entered, the user can click the **Calculate** button to perform the conversion and display the result on the screen.

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
