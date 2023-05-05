# Time-To-Leave-Final
##Introduction
This project is a Java-based application that sends notifications to users to remind them when it's time to leave for their next class. The application calculates the walking time between the user's current location and the building where their next class is held, and sends the notification accordingly. The user interface of the application is built using Java's Swing toolkit, and the Google Distance Matrix API is used to calculate walking times.

##Background
Being on time for class is crucial for success in college, especially at the University of Texas at Dallas (UTD), where students are expected to actively participate in class discussions and navigate a complex campus layout to get to their classes. The application developed in this project aims to address these challenges by providing timely notifications that take into account walking times between different buildings on campus, helping UTD students plan their schedules more effectively and arrive to class on time, which can in turn improve their academic performance and build professional skills and habits.

##Methodology
The application is built using Java's Swing toolkit for the user interface. The main screen of the application allows the user to input their current location and the building where their next class is held. The application then calculates the walking time between the two locations using the Google Distance Matrix API. The calculated walking time is displayed on the screen along with the estimated time of arrival (ETA) at the destination.
The application continuously monitors the time and sends a notification to the user when it's time to leave for their next class. The notification contains information about the walking time, the destination building, and the ETA.

##Conclusion
This project demonstrates how technology can be used to solve everyday problems and make people's lives easier. By leveraging the power of APIs and user-friendly interfaces, we can create applications that provide useful services to users. In the future, this application could be expanded to include additional features, such as real-time traffic updates and alternative route suggestions.

References
The Google Distance Matrix API was used to calculate walking times. Documentation for the API can be found at https://developers.google.com/maps/documentation/distance-matrix/start.
