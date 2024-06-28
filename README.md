# OYE Hotel Service

## Subtitle
An service management based backend server for a Hotel Management software. 

## Description
Here an user of the app can do the following operations:
1. ***Signup*** for a new account
2. ***Sign In*** to the existing account
3. ***Check for available rooms*** 
4. ***Booking*** online
5. ***Check-out*** from the hotel
6. ***Make Paymens***


These operations have been divided into 3 services.
1. **User Service** : an *Entity Service* that is concerned with management of the user account
2. **Room Service**: an *Entity Service* that provide room details, and concerned with booking and checkout
3. **Payment Service**: a *Entity Service* that manages user payments.

## Attachments
In here 3 maven spring boot project is available related to each of the services.

## Service Contracts
1. **User**: [User.wsdl](http://localhost:8081/ws/user.wsdl)
2. **Room**: [RoomService.wsdl](http://localhost:8082/ws/roomservice.wsdl)
3. **Payment**: [PaymentService.wsdl](http://localhost:8083/ws/paymentservice.wsdl)

## Relation between the services

Here the payment consumes the services User and RoomService who also independent operations of their own. While making the payment, the payment service sends a request to the RoomService and receives the list of Rooms available. After that, It parses the response and check the returned list for the availabity of the room user requested. if its available then it makes the payment. 

#### Schema
Schema contains the definitions of all the datatypes of the binding class.
#### Application properties
defines the port number and name of the application
#### Application Class
Contains the main function
#### "Generated" package
contains all the binding classes
#### Endpoint Class
defines each operation for a port type using the binding classes. It may call repository (dummy database) or client (consumer of other web services) according to its need.
#### Config Class
Makes a message dispatcher bean that is vital for all web services. makes a wsdl for the web service using the schema and endpoint class.

## Principles Followed
| principle        | User | Room | Payment |
| ---------------- | ---- | ---- | ---- |
| Service Contract | ✔️   | ✔️   | ✔️   |
| Loose Coupling   | ✔️   | ✔️   |      |
| Abstraction      | ✔️   | ✔️   | ✔️   |
| Reusability      | ✔️   | ✔️   |      |
| Autonomy         | ✔️   | ✔️   |      |
| Statelessness    | ✔️   | ✔️   | ✔️   |
| Discoverability  |      |      |      |
| Composability    | ✔️   | ✔️   | ✔️   |
