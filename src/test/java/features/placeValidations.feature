Feature: Validating Place API's

Scenario Outline: Verify if Place is successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "addPlaceAPI" with Post http request
	Then the API call is successful with status code 200
	And "status" is response body is "OK"
	And "scope" is response body is "APP"
	
Examples:
	| name          | language  |    address                |
	|Frontline house| French-IN | 29, side layout, cohen 09 |
	|AAHouse        | English   | World center              |
		