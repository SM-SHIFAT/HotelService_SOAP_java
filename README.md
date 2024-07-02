# OYE Hotel Management Service

### KeyPoints
- [Description](#description)
- [Service Contracts](#service-contracts)
- [Principles appliced](#principles-appliced)
- [Overview](#overview)



## Description
This project is a Java-based implementation of a hotel management system using Service-Oriented Architecture (SOA) principles. The system consists of five web services: User, Room, Payment, Booking, and Utility. Each service is designed to operate independently, providing a specific functionality to the overall system. The services communicate with each other using SOAP (Simple Object Access Protocol) protocol, enabling loose coupling and reusability. The project demonstrates the application of SOA principles, such as service autonomy, reusability, abstraction, composability, and discoverability, to build a scalable and maintainable hotel management system.

Here an user of the app can do the following operations:
1. ***Signup*** for a new account
2. ***Sign In*** to the existing account
3. ***Check for available rooms*** 
4. ***Booking*** hotel room.
5. ***Check-out*** from the hotel
6. ***Make Paymens***
7. Get ***Payment Statement***
8. ***Log*** every payment statement.


These operations have been divided into 5 services.
1. **User Service** : an *Entity Service* that is concerned with management of the user account
2. **Room Service**: an *Entity Service* that provide room details, and concerned with booking and checkout
3. **Payment Service**: a *Entity Service* that manages user payments.
4. **Booking Service**: a *Task Service* that manages booking related operations.
5. **Utility Service**: a *Utility Service* that manages logs of each successful payments.

## Service Contracts
1. **User**: [User.wsdl](http://localhost:8081/wsdlfirst/user.wsdl)
2. **Room**: [RoomService.wsdl](http://localhost:8082/wsdlfirst/roomservice.wsdl)
3. **Payment**: [PaymentService.wsdl](http://localhost:8084/wsdlfirst/paymentservice.wsdl)
4. **Booking**: [Booking.wsdl](http://localhost:8083/wsdlfirst/booking.wsdl)
5. **Utility**: [Utility.wsdl](http://localhost:8085/wsdlfirst/utility.wsdl)

------------------------------------------------------------------------------------------
## Principles appliced: 
1. ***Service Autonomy***:
Each service (User, Room, Payment, Booking, and Utility) is designed to operate independently, with its own logic and decision-making authority. For example, the Payment Service manages user payments without relying on other services to make decisions.

2. ***Service Reusability***:
The services are designed to be reusable across the application. For instance, the Room Service provides operations for managing rooms, which can be reused by the Booking Service to book a room.

3. ***Service Abstraction***:
The services expose their functionality through abstract interfaces (WSDL files), hiding the underlying implementation details. This allows for loose coupling between services and makes it easier to change or replace individual services without affecting the overall system.

4. ***Service Composability***:
The services are designed to be composed together to achieve a higher-level business capability. For example, the Booking Service composes the Room Service and Payment Service to create a booking.

5. ***Loose Coupling***:
The services are loosely coupled, meaning that they don't have tight dependencies on each other. This is evident in the way the Payment Service consumes the Room Service and User Service, but doesn't require a direct reference to their implementation.

6. ***Service Discoverability***:
The services are discoverable through their WSDL files, which provide a standardized way of describing the service interface.


-------------------------------------------------------------------------------------------
# Java SOAP Web Services Documentation

## Overview
This documentation provides an overview of five Java SOAP web services: User, Room, Payment, Booking, and Utility. Each service has various operations, categorized based on their functionality. 

### Services
1. [**User Service**](#1-user-service) Entity Service
2. [**Room Service**](#2-room-service) (Entity Service)
3. [**Payment Service**](#3-payment-service) (Entity Service)
4. [**Booking Service**](#4-booking-service) (Task Service)
5. [**Utility Service**](#5-utility-service) (Utility Service)

## 1. User Service
The User Service provides operations for managing user-related actions. 

### Operations
- **getUser**: Retrieves user details based on the provided user ID.
- **login**: Authenticates a user with the provided credentials.
- **signup**: Registers a new user with the provided information.

## 2. Room Service
The Room Service manages room-related operations.

### Operations
- **getAllRoom**: Retrieves a list of all available rooms.
- **bookRoom**: Books a specified room.
- **checkoutRoom**: Checks out from a specified room.

## 3. Payment Service
The Payment Service handles payment-related operations.

### Operations
- **makePayment**: Processes a payment for a booking.
  - **Dependencies**: Accesses the `addLog` operation in the Utility Service to add a log entry.
- **getStatement**: Retrieves the payment statement for a user.

## 4. Booking Service
The Booking Service manages the booking process and integrates with other services.

### Operations
- **makeBooking**: Creates a booking for a room.
  - **Dependencies**: Accesses `getAllRoom` operation in the Room Service and `makePayment` operation in the Payment Service.
- **getAvailableRooms**: Retrieves a list of available rooms.
  - **Dependencies**: Accesses `getAllRoom` operation in the Room Service.
- **checkout**: Completes the checkout process for a booking.
  - **Dependencies**: Accesses `checkoutRoom` operation in the Room Service.

## 5. Utility Service
The Utility Service provides utility operations for other services.

### Operations
- **addLog**: Adds a log entry for a specified action.

## Service Interactions
- **Booking Service**:
  - `makeBooking` interacts with `getAllRoom` (Room Service) and `makePayment` (Payment Service).
  - `getAvailableRooms` interacts with `getAllRoom` (Room Service).
  - `checkout` interacts with `checkoutRoom` (Room Service).
- **Payment Service**:
  - `makePayment` interacts with `addLog` (Utility Service).

## Summary
This documentation provides an overview of the five Java SOAP web services, their operations, access methods, and interactions with other services. The User, Room, and Payment services are entity services handling user, room, and payment operations, respectively. The Booking service is a task service managing the booking process and interacting with the Room and Payment services. The Utility service provides a utility operation for logging actions, used by the Payment service during payment processing.
