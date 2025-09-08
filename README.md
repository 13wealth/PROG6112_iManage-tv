TV Series Management Application

Author: Smithy
Course: PROG6112 - Software Development
Project: Assignment Submission
Git Repository: [Insert your GitHub link here]

Overview

This Java-based TV Series Management application allows users to capture, search, update, and delete TV series data. It demonstrates full GUI integration, JSON file handling, and unit testing using JUnit.

The project started as a simple idea and evolved into a fully functional program with robust validation, user interaction, and persistent storage.

Features

Add TV Series: Capture series details including Series ID, Name, Age Restriction, Episodes, and optional Description.

Search Series: Search for a series by its ID with real-time display of metadata.

Update Series: Modify existing series data and save changes to the JSON file.

Delete Series: Remove series by ID with confirmation prompt and automatic refresh of display.

Validation: Ensures Age Restriction and Episodes are numeric and within valid ranges.

JSON Storage: Stores all series in AllSeries.json for persistent data management.

JUnit Testing: Comprehensive tests for search, update, delete, and validation logic.

Installation & Setup

Clone the repository:

git clone [Your GitHub link here]


Open the project in VS Code or your preferred Java IDE.

Ensure you have Java 24 or higher installed.

The project uses JUnit 5 for testing; Maven is configured to handle dependencies.

Run the application by executing the TVSeriesManagement main class.

Running Tests

Tests are written using JUnit 5 and cover:

testSearchSeries – Verifies series can be found by ID.

testSearchSeries_SeriesNotFound – Verifies behavior when series ID is missing.

testUpdateSeries – Confirms series data is updated correctly in JSON.

testDeleteSeries – Confirms deletion logic works and handles missing IDs.

testSeriesAgeRestriction_AgeValid & testSeriesAgeRestriction_AgeInvalid – Validates age and episodes input.

Run tests using Maven:

mvn test

File Structure

src/main/java/com/streaming_company/ – Java source files.

src/main/resources/ – Optional resources such as icons or images.

AllSeries.json – JSON file storing all series data.

src/test/java/com/streaming_company/ – JUnit test classes.

Notes

Series IDs are auto-generated to ensure uniqueness.

The GUI is built using JPanel, GridLayout, and GridBagLayout for flexibility.

Descriptions are optional and primarily for UI display; validation and tests focus on Series ID, Name, Age, and Episodes.

Future Enhancements

Implement a search by name feature.

Add sorting and filtering options.

Improve GUI with a more modern look using JavaFX or Swing enhancements.

Add user authentication for multi-user management.
