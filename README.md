# Spring Starter
### Spring Starter is ready to go Spring Boot Project. Spring Starter has modified Spring Security Implementation By Providing an Annotation Based approach for Authentication and Authorization purpose. To add Authentication on any route just add @LoginRequired Annotation in your route definition. To add Authorization pass authorized roles in roles array in @LoginRequired Annotaion. For Database Integration JPA has been used. 

#### Examples

#####@LoginRequired(roles= {"admin","superadmin"})

#####Above example will check if Logged in user belongs to admin/superadmin  role

##### @Login Required()
##### Above example will echeck if user is logged in or not.

