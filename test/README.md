Location for each files as below.

test/doc - Under doc folder there are files for Java docs for high level API info. To access open index.html
test/src/main/java/com/qa/api/test - Files for Client resides here MovieAttributes, MovieCollection, SplunkClient
test/src/test/java/com/qa/api/test - Test file SplunkClientTest
test/test-output - This will have test reports. To access open index.html


************Bugs ********************

SPL-001 and SPL-002 is not supported and there is a bug. 

testSpl001 is associated with SPL-001 requirement which will be failing. There are duplicate poster path for movies.

testSpl002 is associated with SPL-002 requirement which will be failing. There are poster path which are invalid and doesn't start with
"https://" which is assumed to be a valid poster path. Also don't see any poster path to be null.

Based on the requirement document
	
- GET query doesn't take any parameter so no matter what we pass after "?q=" it retrieves entire movie list. It doen't return
	based on the movie parameter passed. Even it returns all result after passing null.

- POST query doesn't take any parameter to add any value. Even if it is associated with name, description. Moreover title or poster path added it doesn't add any new movies. through CURL it displays the value that it was added "{ "message": "Splunking your submission using monkeys ..... success... movie posted to catalog" }" but while validating throgh GET request it is not showing those values added. Also it states "name" and "description" parameter in the POST query but don't see any attributes 
as same in the actual result query.


