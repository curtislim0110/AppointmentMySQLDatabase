Title: Appointment Scheduling System
Purpose: To create a appointment scheduling system in Java/JavaFX that interfaces with a MySQL database.

Author: Curtis Lim
Contact Email: clim23@wgu.edu
Application Version 1.0 Build 9/20/2023

IDE: IntelliJ IDEA 2021.1.3 Community Edition
JavaFX SDK 17.0.1
Database Driver Version: MySQL Connector Java 8.0.25


How to run program:
After launching the program, the user is presented with a login screen.  The login screen and login screen messages
will display in french if the user's local computer setting uses french "fr" locale.  User login attempts are recorded
to login_activity.txt.  After successfully logging in, any appointments starting within 15 minutes of the login time
will be displayed in an alert message. After the alert messages, the user is presented with the main menu.  The user
can select between appointments, customers, and three report screens.

Customers: Add, update, or delete customers.  Deleting a customer will also delete any appointments associated with the
customer.  Updating or deleting is performed by clicking a customer in the table, which will automatically populate
the text fields and selection combo boxes.

Appointments: Add, update, or delete appointments. Updating or deleting is performed by clicking an appointment in
the table, which will automatically populate the text fields and selection combo boxes.

Report sorted customer appointments: View appointments sorted by starting month and type.

Report contact schedule: View appointments by associated contact.

Description of additional report:
The additional custom report shows the appointments associated by user.  This user report is similar to the other
required report that shows the appointments associate with a contact.  This report is needed because it is useful to
have quick way of filtering user-associated appointments, to save time for the person reading the report.