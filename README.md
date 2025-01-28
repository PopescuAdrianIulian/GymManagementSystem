
# Gym Management System

A Gym Management System built with Java 17, Hibernate 5, MySQL, and a Command-Line Interface (CLI). This system allows gym administrators to manage members, trainers, training sessions, reservations, subscriptions, and equipment.

## Features

- **Member Management**: Add, view, update, and delete gym members.
- **Trainer Management**: Manage trainer details and availability.
- **Training Sessions**: Schedule and manage training sessions, assign trainers to sessions.
- **Reservations**: Reserve gym equipment and track reservation details.
- **Subscriptions**: Handle gym memberships, including creation and progress tracking.
- **Equipment**: Manage gym equipment, check availability, and track reservations.
- **Maintenance**: Track and manage equipment maintenance schedules.

## Technologies

- **Java 17**: Main programming language.
- **Hibernate 5**: ORM framework for database interaction.
- **MySQL**: Relational database management system to store data.
- **Command-Line Interface (CLI)**: Interface for interacting with the system.

## Database Structure

The system uses several database tables to store gym-related data:

- **Member**: Stores information about gym members.
- **Trainer**: Contains details about trainers.
- **TrainingSession**: Records scheduled training sessions and assigns trainers.
- **Subscription**: Tracks the subscription status and progress of members.
- **Reservation**: Manages reservations for gym equipment.
- **Equipment**: Contains details about gym equipment.

### Relationships

- A **Member** can have one **Subscription**.
- A **Trainer** can conduct multiple **TrainingSessions**.
- **Members** can make **Reservations** for **Equipment**.
- **Equipment** can have multiple **Reservations** but must not be under maintenance for reservation.

## Installation

### Prerequisites

- Java 17 or higher
- MySQL Database
- Hibernate 5

### Setup

1. **Clone the repository**:

   ```bash
   git clone https://github.com/PopescuAdrianIulian/GymManagementSystem.git
   cd gym-management-system
   ```

2. **Set up the MySQL database**:

   Create a new database in MySQL:

   ```sql
   CREATE DATABASE gym_management;
   ```

   Update the `hibernate.cfg.xml` file with your MySQL credentials.

3. **Install dependencies**:

   Make sure you have all required dependencies in your project by using Maven or Gradle.

   - Maven: Run `mvn clean install` to build the project.

4. **Run the application**:

   - Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
   - Run the main class to start the CLI-based Gym Management System.

## Usage

Once the system is running, use the CLI to interact with the system. You can perform actions such as:

- **Add Member**: Add new gym members.
- **View Members**: Display all members and their details.
- **Manage Trainers**: Add and assign trainers to training sessions.
- **Reserve Equipment**: Reserve available gym equipment.
- **Schedule Sessions**: Create and assign training sessions for members.

### Sample Commands

- **Add a new member**:
  ```
  Enter member details: Name, Age, etc.
  ```
- **Reserve equipment**:
  ```
  Select a member, equipment, and set reservation date.
  ```

## Contributing

Feel free to fork the project, create issues, and submit pull requests. All contributions are welcome!




