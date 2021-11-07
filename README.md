 1.Build & run the application
 
	import project as maven into IDE
	make sure Kafka is running , config kafka in application.yaml
	go to DemoApplication.class and run the project 
	send request to http://localhost:8080/price/sell?amount=10&currency=VND to get data for sell
	send request to http://localhost:8080/price/buy?amount=10&currency=NZD to get data for buy
	param amount is required, currency is optinal

2. price for bitcoin is retrieved  from https://api.coinbase.com/v2/prices/spot?currency=something

3.This app uses Spring boot,Spring stream Kafka .All the bean is managed and  injected to dependencies.
Controller class BitcoinController.All other service is injected to this class to reduce coupling between them.
Junit test: BitcoinControllerTest

4,Summary breakdown
 Preparation: 1 hour 
 Coding: 5 hours  
 Documentation:1 hour 
 Building and testing: 1 hour 
 Grand total: 8 hours 
