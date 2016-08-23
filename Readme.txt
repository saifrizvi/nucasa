Steps to install and run the code

1. import in your IDE as a maven project
2. Go to project root and run following maven commmand to build the code:
	mvn clean install
3. Go to project root and run following command to deploy and run the api on your localhost port 8080
	mvn spring-boot:run 
	[OR]
	java -jar target/nook-casa-service-1.0.jar
4. Test Following API calls in your browser or a client
	1. List All Properties - http://localhost:8080/properties
	2. Add Property with Start Price - http://localhost:8080/addProperty/1200
	3. Get Time To Bid by Property Id - http://localhost:8080/timeToBid/1001
	4. Get Start Bid Price by PropertyId - http://localhost:8080/startBidPrice/1001
	5. Get All Bids for a property by Property Id - http://localhost:8080/bids/1001
	6. Get Max Bid for a property by property Id - http://localhost:8080/maxBidPrice/1001
	7. Register a Bid for a property - This is a POST so use commandline to run the command
		curl -H "Content-Type: application/json" -X POST -d '{"propertyId": 1001,"price": 4500,"noOfTenants": 1,"proposal": "Test"}' http://localhost:8080/registerBid
		