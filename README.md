#### Spring Starter
Spring Starter is ready to go Spring Boot Project. Spring Starter has modified Spring Security Implementation By Providing an Annotation Based approach for Authentication and Authorization purpose. To add Authentication on any route just add @LoginRequired Annotation in your route definition. To add Authorization pass authorized roles in roles array in @LoginRequired Annotaion. For Database Integration JPA has been used. 


##### Database Configuration
By Default Postgres driver is installed with the application. To use this application with default configuration install postgres and change database properties in application.properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.jpa.database-platform=

For running test cases h2 database is used.


##### Database Migration
To allow database upgradation on the run Liquibase has been used. The Liquibase change log file is available at src/main/resources/db folder.  
liquibase-changelog.xml file is used to manage migration for main application.
liquibase-changelog-test.xml file is used to manage migrations for running test cases.

#### Examples

@LoginRequired(roles= {"admin","superadmin"})

Above example will check if Logged in user belongs to admin/superadmin  role

@Login Required()
Above example will echeck if user is logged in or not.

