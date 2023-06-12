# WUAV app
![wuav](https://user-images.githubusercontent.com/114875545/233788102-64074661-6f97-422f-b7c2-10619a99ad90.gif)

## Project Description
The WUAV Documentation System is a desktop application developed for WUAV, a company that delivers and installs AV (Audio/Video) solutions for businesses, public institutions, and private individuals in the southern Denmark region. The application aims to streamline the documentation process for WUAV's mobile technicians by providing a comprehensive system for recording and organizing installation details.

## Problem
WUAV's technicians need to document their work, specifically the installation process, in order to provide customers with necessary documentation and references for future use. However, the existing documentation process is cumbersome and lacks a centralized system for storing and accessing installation information. This results in inefficiencies and potential information gaps.

## Solution
The WUAV Documentation System offers a solution to the above problem by providing a user-friendly desktop application that allows technicians to record and manage installation details efficiently. The system enables technicians to create layout drawings of installations, add text descriptions, capture and attach pictures taken with their mobile phone cameras, and include setup/login information such as usernames and passwords.

The application caters to different customer types, distinguishing between business and private customers, and provides relevant documentation requirements based on their specific needs. It ensures that the resulting documentation can be easily compiled into printable reports or documentation documents to be sent to the customers.

## Key Features
User authentication and access control: The system enforces user-level access restrictions to maintain data privacy and security. Technicians, project managers, salespersons, and customers have different levels of access and functionality within the application.
Installation documentation: Technicians can create detailed layout drawings, add text descriptions, and attach pictures to accurately document the installation process.
Customer differentiation: The application caters to the varying needs of business and private customers, allowing for specific documentation requirements tailored to each customer type.
Centralized data storage: All relevant installation data is persistently stored in a database using the school's MSSQL server, ensuring easy retrieval and management of documentation.
Usability-focused user interface: The application prioritizes usability, considering the technical nature of the users, and offers advanced functionality while maintaining an intuitive user experience.
Data validation: The system includes input checks and validation to ensure accurate and reliable data entry, reducing errors and inconsistencies.

## Installation
Clone the repository to your local machine.
Open the project in IntelliJ or your preferred Java IDE.
Configure the project to use the required dependencies and libraries.
Set up the connection to the school's MSSQL server and ensure the necessary database is available.
Build and run the application.

## Usage
Launch the application on your desktop.
Log in using your assigned credentials based on your user role (technician, project manager, salesperson, or customer).
Explore the available features based on your user role.
Follow the provided user guide or on-screen instructions to create, edit, or view installation documentation.
Ensure to save your progress and log out when finished.

## Contributing
Contributions to the WUAV Documentation System project are welcome. Please follow the guidelines outlined in CONTRIBUTING.md to contribute to the project.

## License
The WUAV Documentation System project is licensed under the MIT License.