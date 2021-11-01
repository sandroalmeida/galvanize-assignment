## Instructions

This part of the interview process allows us to learn more about your software engineering and web development skills. Below is a description of a CRUD API that manages products, keeps track of their pricing and their view counts. You are given a boilerplate application with parts that are incomplete or not working as expected. The task is to add features to the application, adapt some parts of it and come prepared at the next interview with suggestions on how to improve it.

The boilerplate application has some basic components set up: a Product model with a database connection and an empty controller. We would like you to do the following:
- Add an API to get a single product
- Finish the implementation for fetching the currency conversion

When a single product is requested, all fields of that product are returned and the view-count for that product is incremented. The request can optionally specify a currency, in which case the price should be converted to the requested currency before being returned. We need to support the following currencies:
*	USD (default)
*	CAD
*	EUR
*	GBP

The latest exchange rates are retrieved from the public API https://currencylayer.com/. Tests are optional but we would like to hear from you how would you design such tests at the interview.

## Development

This is a initial development to achieve the requirements described on the instructions.
Some highlights about the implementation:
# Versioning
Added versioning package and Base URL to Controllers layer.
# Random String ID
The product model was changed to include a new field called StrID (String ID) and a helper function to generate this ID was implemented.
This procedure was made to improve the security of the API, considering the API is using this parameter as filtering the sequencial number if exposed could be used to overload the server maliciously.
# Mapper
Another dependency added to this project was the MapStruct, this library allow us to use an Interface to define the mapper funcionality between the DTO and Beans, avoiding boilerplate code.
# Application.properties
This file was deleted and integrated with the Application.yml
# Error Handling
To customized classes was added and refered to the Controller Advice just as example.
# CRUD APIs
The API to get Product was created, but also added some extra APIs as example.
The API for get the Product was implemented in two different endpoints, one using only the parameter ID and returning the standard price, another using an extra parameter "currency" and returning the price after currency convertion.
# Swagger Config
A Swagger dependencies and Configuration class was added to the project to exposed the API documentation.
No customized configurations was made.

## Next improvements suggested
- Create the unit tests for all the API operations.
- Enabling HTTPS.
- Adding HATEOS to expose relational API address on the responses.
- Implement rate limits to avoid server overload attacks.
- Add pagination, sort and filtering on the getAll Products API.
- Implement customized fields responses.

## Project Images and Tests
![database](https://user-images.githubusercontent.com/6804563/139712451-7f6691a9-a50a-4d74-9564-618d670551fb.png)

![swagger](https://user-images.githubusercontent.com/6804563/139712456-150a5bef-6bd1-490a-b91c-60b3ecef38bc.png)

![postman](https://user-images.githubusercontent.com/6804563/139712454-b10aa11c-17f2-4b34-9fa7-7522c256ac39.png)

