# Many To Many Relation Demo

1. Create a Liferay Workspase Project In Liferay IDE
1. Clone `many to many` to `modules` folder
1. Execute Gradle command `buildService`
1. Execute Gradle command `deploy` 
1. Drag and drop `many-to-many-web Portlet` to a page
1. Execute the following SQL in your Database
```
SELECT * FROM DEMO_Student;
SELECT * FROM DEMO_Course;
SELECT * FROM DEMO_Students_Courses;
```
