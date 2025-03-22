Selenium TestNG Automation - Demoblaze
------------------------------------------
Project Overview
--------------------------------------------
This project automates testing of the Demoblaze e-commerce website using Selenium WebDriver,
TestNG, Page Object Model (POM), Page Factory, and BDD Cucumber . The framework
supports data-driven testing, exception handling, and reporting.

Project Structure
-------------------------------------------------------
📂 Capstone_Project  
│── 📂 src/main/java  
│   ├── 📂 Base  
│   │   ├── 📄 BaseClass.java  
│   ├── 📂 Data  
│   │   ├── 📄 config.properties  
│   ├── 📂 feature  
│   │   ├── 📄 Login.feature  
│   ├── 📂 pages  
│   │   ├── 📄 CartPage.java  
│   │   ├── 📄 CheckoutPage.java  
│   │   ├── 📄 HomePage.java  
│   │   ├── 📄 LoginPage.java  
│   │   ├── 📄 ProductPage.java  
│   │   ├── 📄 SignupPage.java  
│   ├── 📂 Utility  
│   │   ├── 📄 ExtentReportManager.java  
│   ├── 📂 Hook  
│   │   ├── 📄 Hook.java  
│── 📂 src/test/java  
│   ├── 📂 MyRunner  
│   │   ├── 📄 TestRunner.java  
│   ├── 📂 StepDef  
│   │   ├── 📄 LoginSteps.java  
│   ├── 📂 Testng  
│   │   ├── 📄 AddToCartTest.java  
│   │   ├── 📄 CheckoutTest.java  
│   │   ├── 📄 LoginTest.java  
│   │   ├── 📄 SignupTest.java  
│   │   ├── 📄 TestCases.java  
│   ├── 📂 screenshot  ✅ (Newly Added)  
│── 📂 target  
│   ├── 📂 surefire-reports  
│   │   ├── 📄 cucumber-reports.html  
│   │   ├── 📄 ExtentReports-DemoBlaze.html  
│   ├── 📂 test-output  
│   │   ├── 📄 index.html  
│   │   ├── 📄 emailable-report.html  
│   │   ├── 📄 testng-results.xml  
│── 📂 xml  
│   ├── 📄 TestRunner.xml  
│   ├── 📄 Testcase.xml  
│── 📄 pom.xml  

Technologies Used in the Project
----------------------------------------------------------
Programming Language: Java 

Automation & Testing Frameworks: Selenium WebDriver, TestNG, Cucumber (BDD)

Build & Dependency Management: Maven

Design Patterns: Page Object Model (POM), Page Factory, Hooks and Tags in Cucumber

Reporting & Logging: Extent Reports, Cucumber Reports

Exception Handling & Debugging: Try-Catch Blocks

Data-Driven Testing: Properties File (config.properties)




