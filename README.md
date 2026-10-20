# Cottage Rental Web App: Test Plan & Test Automation

Software testing project for the course **Software Testing (13S113TS)**, Homework 1, School of Electrical Engineering, University of Belgrade.

The project contains a complete **test plan**, **test execution report** and **defect report** for a cottage rental web application, together with **automated UI tests** written with Katalon Recorder and Selenium (Java + TestNG).

## About the system under test

A web application (Angular, served at `http://localhost:4200`) for renting weekend cottages with three user roles:

- **Tourist** – registers, browses cottages, makes reservations, leaves reviews.
- **Owner** – adds (manually or via JSON upload) and deletes cottages, approves or rejects reservation requests.
- **Administrator** – approves registration requests.

## Testing approach

Test cases were derived using **equivalence class partitioning** combined with **boundary value analysis**.

- **97 equivalence classes** were identified, each marked as valid (LK) or invalid (NK).
- **64 test cases** were designed so that every class is covered. Each test case lists the classes it covers, its steps, preconditions/postconditions and whether it is manual or automated.

| Functionality | Examples of what is covered |
| --- | --- |
| Registration | username/email uniqueness, password rules (length 6–10, uppercase, 3+ lowercase, digit, special char, starts with a letter), name/surname, phone, profile picture format, credit card format (Diners, Mastercard, Visa) |
| Cottage reservation | date and time logic (check-in from 14:00, check-out until 10:00, past dates), number of adults/children, special requests (max 500 characters) |
| Reviews | rating 1–5, comment length |
| Owner actions | accept/reject reservation (rejection reason required), delete cottage |
| Adding a cottage | required fields, non-negative prices, coordinate ranges (lat −90..90, lon −180..180), JSON import |

## Test automation

| Tool | Scope | Files |
| --- | --- | --- |
| **Katalon Recorder** | Registration tests (test cases 1, 4–17) | `test_primeri.krecorder` |
| **Selenium (Java, TestNG)** | Adding a new cottage (test cases 52, 55–64) | `DodavanjeVikendiceTest.java` |

The remaining test cases were executed manually.

### Running the Selenium tests

Prerequisites: JDK 11+, Google Chrome, Maven or Gradle with `selenium-java` and `testng` dependencies. Selenium Manager downloads the matching ChromeDriver automatically.

1. Start the application so it is available at `http://localhost:4200`.
2. Make sure the users `owner1` and `tourist1` (password `pass123`) exist in the system.
3. Run the `DodavanjeVikendiceTest` class with TestNG from your IDE or build tool.

### Running the Katalon Recorder tests

1. Install the Katalon Recorder browser extension.
2. Import `test_primeri.krecorder` as a test suite.
3. With the application running, play the test suite.

## Results summary

| Metric | Count |
| --- | --- |
| Total test cases | 64 |
| Executed manually | 38 |
| Executed automatically | 26 |
| Passed | 30 |
| Failed | 34 |

Execution dates: 31 Jan – 1 Feb 2026.

### Main defects found (18 reported)

- **Password validation is missing**: the app accepts passwords that are too short or too long, or that lack an uppercase letter, lowercase letters, digits, special characters, or a leading letter.
- **Input validation gaps in registration**: names with special characters, phone numbers with letters, emails without `@` and malformed credit card numbers are accepted.
- **Reservation validation gaps**: past check-in dates, negative guest counts and zero guests are accepted.
- **Special requests limit**: a valid 500-character text is rejected.
- **Coordinates field is missing** from the "Add new cottage" form, so all related automated tests fail.
