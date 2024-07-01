# OYE Hotel Management Service

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

-------------------------------------------------------------------------------------------
# Java SOAP Web Services Documentation

## Overview
This documentation provides an overview of five Java SOAP web services: User, Room, Payment, Booking, and Utility. Each service has various operations, categorized based on their functionality. 

### Services
1. **User Service** (Entity Service)
2. **Room Service** (Entity Service)
3. **Payment Service** (Entity Service)
4. **Booking Service** (Task Service)
5. **Utility Service** (Utility Service)

## 1. User Service
The User Service provides operations for managing user-related actions. 

### Operations
- **getUser**: Retrieves user details based on the provided user ID.
- **login**: Authenticates a user with the provided credentials.
- **signup**: Registers a new user with the provided information.

### Access
All operations in the User Service can be accessed directly.

## 2. Room Service
The Room Service manages room-related operations.

### Operations
- **getAllRoom**: Retrieves a list of all available rooms.
- **bookRoom**: Books a specified room.
- **checkoutRoom**: Checks out from a specified room.

### Access
All operations in the Room Service can be accessed directly.

## 3. Payment Service
The Payment Service handles payment-related operations.

### Operations
- **makePayment**: Processes a payment for a booking.
  - **Dependencies**: Accesses the `addLog` operation in the Utility Service to add a log entry.
- **getStatement**: Retrieves the payment statement for a user.

### Access
All operations in the Payment Service can be accessed directly.

## 4. Booking Service
The Booking Service manages the booking process and integrates with other services.

### Operations
- **makeBooking**: Creates a booking for a room.
  - **Dependencies**: Accesses `getAllRoom` operation in the Room Service and `makePayment` operation in the Payment Service.
- **getAvailableRooms**: Retrieves a list of available rooms.
  - **Dependencies**: Accesses `getAllRoom` operation in the Room Service.
- **checkout**: Completes the checkout process for a booking.
  - **Dependencies**: Accesses `checkoutRoom` operation in the Room Service.

### Access
All operations in the Booking Service can be accessed directly.

## 5. Utility Service
The Utility Service provides utility operations for other services.

### Operations
- **addLog**: Adds a log entry for a specified action.

### Access
All operations in the Utility Service can be accessed directly.

## Service Interactions
- **Booking Service**:
  - `makeBooking` interacts with `getAllRoom` (Room Service) and `makePayment` (Payment Service).
  - `getAvailableRooms` interacts with `getAllRoom` (Room Service).
  - `checkout` interacts with `checkoutRoom` (Room Service).
- **Payment Service**:
  - `makePayment` interacts with `addLog` (Utility Service).

## Summary
This documentation provides an overview of the five Java SOAP web services, their operations, access methods, and interactions with other services. The User, Room, and Payment services are entity services handling user, room, and payment operations, respectively. The Booking service is a task service managing the booking process and interacting with the Room and Payment services. The Utility service provides a utility operation for logging actions, used by the Payment service during payment processing.
