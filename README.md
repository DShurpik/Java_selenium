# 🚀 Java Selenium Test Automation Framework

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Selenium](https://img.shields.io/badge/Selenium-4.38.0-green?style=for-the-badge&logo=selenium)
![TestNG](https://img.shields.io/badge/TestNG-7.11.0-red?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-3.x-blue?style=for-the-badge&logo=apache-maven)
![Allure](https://img.shields.io/badge/Allure-2.29.1-pink?style=for-the-badge)

**A robust, enterprise-grade test automation framework built with modern Java and Selenium WebDriver**

[Features](#-key-features) • [Architecture](#-architecture) • [Getting Started](#-getting-started) • [Usage](#-usage) • [Project Structure](#-project-structure)

</div>

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Key Features](#-key-features)
- [Architecture](#-architecture)
- [Technology Stack](#-technology-stack)
- [Getting Started](#-getting-started)
- [Usage](#-usage)
- [Project Structure](#-project-structure)
- [Best Practices Implemented](#-best-practices-implemented)
- [Reporting](#-reporting)
- [Configuration](#-configuration)

---

## 🎯 Overview

This is a comprehensive, production-ready test automation framework designed for web application testing. Built with modern Java 17 and Selenium 4.38.0, it follows industry best practices and design patterns to ensure maintainability, scalability, and reliability.

The framework provides a solid foundation for automated testing with features like parallel execution, advanced reporting, retry mechanisms, and flexible configuration management.

---

## ✨ Key Features

### 🏗️ **Enterprise Architecture**
- **Page Object Model (POM)** - Clean separation of page logic and test code
- **Factory Pattern** - Flexible driver creation for multiple browsers
- **Builder Pattern** - Elegant test data construction
- **Strategy Pattern** - Extensible link clicking strategies
- **ThreadLocal** - Thread-safe parallel test execution

### 🔧 **Advanced Capabilities**
- ✅ **Multi-Browser Support** - Chrome and Firefox with easy extensibility
- ✅ **Multi-Environment Configuration** - Dev, Test, and Production environments
- ✅ **Parallel Test Execution** - Thread-safe driver management
- ✅ **Retry Mechanism** - Automatic retry for flaky tests (configurable attempts)
- ✅ **Screenshot on Failure** - Automatic screenshots for failed tests
- ✅ **Network Interception** - Chrome DevTools integration for API testing
- ✅ **Excel Data Integration** - Read test data from Excel files
- ✅ **Dynamic Test Data Generation** - Faker and Instancio for realistic test data

### 📊 **Reporting & Logging**
- 📈 **Allure Reports** - Beautiful, interactive test reports
- 📝 **Comprehensive Logging** - Log4j2 with file and console output
- 🎯 **Allure Step Annotations** - Detailed step-by-step test execution tracking
- 📸 **Screenshot Attachments** - Automatic screenshots in reports

### 🛡️ **Reliability & Maintainability**
- 🔄 **Smart Retry Logic** - Handles StaleElementReferenceException and ElementClickInterceptedException
- ✅ **Configuration Validation** - Validates configuration before test execution
- 📚 **TestNG Listeners** - Custom listeners for enhanced test lifecycle management

---

## 🏛️ Architecture

### Design Patterns

```
┌─────────────────────────────────────────────────────────┐
│                    Test Layer                            │
│  (LinksTests, TextBoxTests, CheckBoxTests, etc.)        │
└────────────────────┬────────────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────────────┐
│              Page Object Layer                           │
│  (LinksPage, TextBoxPage, CheckBoxPage, etc.)           │
└────────────────────┬────────────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────────────┐
│              Base Page Layer                             │
│  (BasePage - Common WebDriver operations)               │
└────────────────────┬────────────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────────────┐
│            Driver Management Layer                       │
│  (DriverManager, DriverFactory, DriverCreator)          │
└─────────────────────────────────────────────────────────┘
```

### Key Components

- **BasePage**: Centralized WebDriver operations with smart retry logic
- **DriverManager**: ThreadLocal-based driver management for parallel execution
- **DriverFactory**: Factory pattern for creating browser-specific drivers
- **ConfigProvider**: Environment-aware configuration management
- **TestData**: Data providers with Excel and dynamic data generation
- **Retry**: Thread-safe retry analyzer for flaky tests

---

## 🛠️ Technology Stack

| Category | Technology | Version |
|----------|-----------|---------|
| **Language** | Java | 17 |
| **Test Framework** | Selenium WebDriver | 4.38.0 |
| **Testing** | TestNG | 7.11.0 |
| **Build Tool** | Maven | 3.x |
| **Reporting** | Allure | 2.29.1 |
| **Logging** | Log4j2 | 2.24.3 |
| **Data Generation** | JavaFaker | 1.0.2 |
| **Object Generation** | Instancio | 5.5.1 |
| **Excel Processing** | Apache POI | 5.4.1 |
| **Configuration** | Typesafe Config | 1.4.3 |
| **Utilities** | Lombok | 1.18.36 |
| **JSON Processing** | Gson | 2.11.0 |

---

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- Chrome/Firefox browser installed
- Git

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd Java_selenium
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Verify installation**
   ```bash
   mvn -version
   java -version
   ```

---

## 💻 Usage

### Basic Test Execution

Run tests with default configuration:
```bash
mvn clean test -DsuiteXml="test" -Dconfig="test"
```

### Advanced Options

#### 1. **Specify Browser**
```bash
mvn clean test -DsuiteXml="test" -Dconfig="test" -Dbrowser="firefox"
```
Available browsers: `chrome`, `firefox`

#### 2. **Select Environment**
```bash
mvn clean test -DsuiteXml="test" -Dconfig="test" -Denv="prod"
```
Available environments: `dev`, `test`, `prod`

#### 3. **Generate Allure Report**
```bash
# Generate report
mvn allure:report

# Serve report (opens in browser)
mvn allure:serve
```

#### 4. **Complete Example**
```bash
mvn clean test -DsuiteXml="test" -Dconfig="test" -Dbrowser="chrome" -Denv="dev"
```

### Test Suites

The framework supports multiple test suites defined in XML files:
- `test.xml` - Main test suite
- `test1.xml` - Additional suite
- `test2.xml` - Additional suite

---

## 📁 Project Structure

```
Java_selenium/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── basePages/          # Base classes for pages and tests
│   │       │   ├── BasePage.java   # Common page operations
│   │       │   └── BaseTest.java   # Test base class
│   │       │
│   │       ├── configLoader/       # Configuration management
│   │       │   └── ConfigProvider.java
│   │       │
│   │       ├── dataGenerator/      # Test data generation
│   │       │   ├── DataUserGenerator.java
│   │       │   ├── UserBuilder.java
│   │       │   └── RandomStringGenerator.java
│   │       │
│   │       ├── driver/             # Driver management
│   │       │   ├── DriverFactory.java
│   │       │   ├── DriverManager.java
│   │       │   ├── DriverCreator.java
│   │       │   ├── ChromeDriverCreator.java
│   │       │   └── FirefoxDriverCreator.java
│   │       │
│   │       ├── listeners/          # TestNG listeners
│   │       │   ├── InvokedMethodListener.java
│   │       │   └── ListenerForProperty.java
│   │       │
│   │       ├── models/             # Data models
│   │       │   └── ResponseData.java
│   │       │
│   │       ├── pageObjects/        # Page Object classes
│   │       │   ├── LinksPage.java
│   │       │   ├── TextBoxPage.java
│   │       │   ├── CheckBoxPage.java
│   │       │   └── ... (14 page objects)
│   │       │
│   │       ├── testData/           # Test data providers
│   │       │   ├── TestData.java
│   │       │   ├── ExcelDataLoader.java
│   │       │   └── FormData.java
│   │       │
│   │       └── utils/              # Utility classes
│   │           ├── Retry.java
│   │           ├── ConfigValidator.java
│   │           ├── TestConstants.java
│   │           └── PropertyReader.java
│   │
│   └── test/
│       ├── java/                   # Test classes
│       │   ├── LinksTests.java
│       │   ├── TextBoxTests.java
│       │   ├── CheckBoxTests.java
│       │   └── ... (10 test classes)
│       │
│       └── resources/              # Test resources
│           ├── test.properties     # Test configuration
│           ├── dev.properties      # Dev environment
│           ├── prod.properties      # Prod environment
│           ├── test_sheet.xlsx     # Test data
│           └── test.xml            # Test suite
│
├── target/                         # Build output
│   ├── allure-results/            # Allure results
│   └── logs/                      # Log files
│
└── pom.xml                         # Maven configuration
```

---

## 🎓 Best Practices Implemented

### ✅ **Design Patterns**
- **Page Object Model**: Clean separation of concerns
- **Factory Pattern**: Extensible driver creation
- **Builder Pattern**: Fluent test data construction
- **Strategy Pattern**: Flexible link clicking strategies
- **Singleton Pattern**: Configuration management

### ✅ **Code Quality**
- **SOLID Principles**: Single Responsibility, Open/Closed, etc.
- **DRY (Don't Repeat Yourself)**: Reusable components
- **Clean Code**: Meaningful names, small methods
- **Thread Safety**: ThreadLocal for parallel execution
- **Exception Handling**: Comprehensive error handling

### ✅ **Testing Best Practices**
- **Data-Driven Testing**: Excel and data providers
- **Retry Mechanism**: Handles flaky tests
- **Screenshot on Failure**: Debugging support
- **Parallel Execution**: Faster test runs
- **Environment Management**: Multiple environments support

### ✅ **Maintainability**
- **Configuration Management**: Centralized config
- **Logging**: Comprehensive logging strategy
- **Reporting**: Detailed test reports
- **Documentation**: Well-structured code
- **Modularity**: Easy to extend and maintain

---

## 📊 Reporting

### Allure Reports

The framework generates beautiful Allure reports with:
- Test execution timeline
- Step-by-step test flow
- Screenshots for failed tests
- Environment information
- Test history and trends

**Generate Report:**
```bash
mvn allure:report
```

**View Report:**
```bash
mvn allure:serve
```

### Logging

Logs are written to:
- **Console**: Real-time test execution logs
- **File**: `target/logs/log.out` for detailed analysis

Log levels: `DEBUG`, `INFO`, `WARN`, `ERROR`

---

## ⚙️ Configuration

### Environment Configuration

The framework supports multiple environments with separate property files:

- `dev.properties` - Development environment
- `test.properties` - Test environment
- `prod.properties` - Production environment

### Configuration Properties

Key configuration options:
- Browser settings (name, timeouts, options)
- WebDriver wait timeouts
- Application URLs (web and API)
- Retry attempts
- Screenshot settings

### Example Configuration

```properties
# Browser Configuration
web.browser.name=chrome
web.browser.webdriverwait=10

# Application URLs
url.web=http://85.192.34.140:8081/
url.api=http://85.192.34.140/api/
```

---

## 🌟 Advanced Features

### Network Interception

The framework includes Chrome DevTools integration for network interception:

```java
LinksPage linksPage = new LinksPage();
linksPage.enableNetworkInterceptor();
linksPage.addRequestListener("endpoint");
linksPage.addResponseListener("endpoint");
// ... perform actions
List<ResponseData> responses = linksPage.getInterceptedResponses();
```

### Smart Retry Logic

Automatic retry for common Selenium exceptions:
- `StaleElementReferenceException`
- `ElementClickInterceptedException`
- Custom retry analyzer for test methods

### Dynamic Test Data

Multiple data generation strategies:
- **JavaFaker**: Realistic fake data
- **Instancio**: Object generation
- **Excel**: External test data
- **Builder Pattern**: Fluent data construction

---

## 📈 Test Coverage

The framework includes comprehensive test coverage for:

- ✅ Text Box forms
- ✅ Check Boxes
- ✅ Radio Buttons
- ✅ Web Tables
- ✅ Buttons
- ✅ Links (with network interception)
- ✅ Broken Links
- ✅ Dynamic Properties
- ✅ Alerts
- ✅ Elements interactions

---

## 🤝 Contributing

This framework demonstrates best practices in test automation. Key areas for contribution:

1. Adding new page objects
2. Extending browser support
3. Enhancing reporting features
4. Adding new utility functions
5. Improving documentation

---

## 🎯 Summary

This test automation framework represents a **production-ready solution** with:

- 🏗️ **Solid Architecture** - Industry-standard design patterns
- 🔧 **Advanced Features** - Network interception, retry mechanisms, parallel execution
- 📊 **Rich Reporting** - Allure integration with detailed insights
- 🛡️ **Reliability** - Thread-safe, error-handling, configuration validation
- 📚 **Maintainability** - Clean code, modular design, comprehensive logging
- 🚀 **Scalability** - Easy to extend with new pages, browsers, and features

---

<div align="center">

**⭐ If you find this framework useful, consider emailing me with job propositions ⭐**

Made with Java ☕ and Selenium 🚗

</div>