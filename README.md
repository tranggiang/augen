 1.build & run the application 
	import project as maven 
	make sure Kafka is running , config kafka in application.yaml
	go to DemoApplication.class and run the project 
	send request to http://localhost:8080/price/sell?amount=10&currency=VND to get data for sell
	send request to http://localhost:8080/price/buy?amount=10&currency=NZD to get data for buy
	param amount is required, currency is optinal

2. price for bitcoin is retrieved  from https://api.coinbase.com/v2/prices/spot?currency=something

3.This app uses Spring boot .All the bean is manged and get injected to dependencies that reduce coupling between class in app.