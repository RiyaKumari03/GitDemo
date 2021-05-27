Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI

Given Add place payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "Post" http request
Then the API call is success with status Code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created maps to "<name>" using "GetPlaceAPI"

Examples: 
|name|langugage|address|
|Ahouse|English|World cross center|
|Bhouse|French|Saint cross Mary|

@DeletePlace
Scenario: Verify if delete place functionality is working

Given DeletePlace Payload
When user calls "DeletePlaceAPI" with "Post" http request
Then the API call is success with status Code 200
And "status" in response body is "OK"