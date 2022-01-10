# CloudSek Backend Assignment


## In this assignment I have made three packages:

- **Controller**
- Model
- Repository
  



### Controller

- In Controller package One class name is CalculateController  in this class i create object of StoreVariableToDatabase to store numbers and their sum.
- After that create a method StringData that is Returns Status code: 200 and string: <i>**Hi from test API**</i> with using **@GetMapping** annotation.
- After that create a cal method in this method take two variable and store in a queue and those variable store in repository.
- **Create a methode test in this method check queue is Empty or not if not add two variable and store into database with help of rpaRepository.**
- In  result method check id with help of response class state code and message. if id does not exit return 404 status code.





## Model
- In Model package there are **two classes NumberEquality and Response.**
- In NumberEquality class takes variable and generate **two method first is equals method and second is hashcode.**
- In equals method check two numbers are equals if equal store only one variable avoid duplicate.
- In **hashcode methode to store those variables.**
- In Response class take **two variable status_code and message. and apply getter and setter annotation.**
- **Message is return sum of two variable.**





## Repository
- In repository package create an interface StoreVariableToDatabase to store NumberEquality class as a table to store these class datatype as attributes to store in database using RpaRepository<br>


## Configuration

- @GetMapping("/")
    * This is  simple test **API** that  return string value **"Hi from test API"** with help of @GetMapping annotation.




- @GetMapping("/calculate/{number1}/{number2}")
    * This **API** Takes 2 data inputs number1 and number2 as url pathVariables. which is stored in the database as number1, number2 and answer which is empty for now.
    * Also for every request, a payload: { number_1 and number_2, unique_identifier } are added to a
      queue which was created during runtime. 
    * The unique_identifier is the document id of the entry stored in database. With help of hashcode method.
  
    * Return:  The unique identifier for the request (the document id of the entry stored in database).





- @GetMapping("/get_answer/{identifier}")
  * This **API** takes unique identifier as a path variable.
  * This **API** returns Status code: 404 if the identifier entry does not exist in the database
  * This **API** returns Status code: 200 and message string "Please wait." if the entry exists but the answer isn't calculated yet.
  * This **API** returns  Status code: 200 and the answer as the message string if the entry exists and the
  answer has been calculated.



- calculateSum() method
  * This is schedule method which runs every 10 second
  * Its calculates the sum of the number_1 and number_2 which is present inside the queue On the basic of first in first out.
  * Its updates the sum of the value calculated of number1 and number2 using the same identifier present in database.






### How to build and run application

- Requirement to run this application.
  - Before running this application make sure that your system port **3306 & 8086** is free because MySQL is using port 3306 and the spring application will use 8086.
  - After this you can simply run **sudo docker-compose up** which will run the application on port 8086.

## End Point table

<html>
 <table>
  <tr>
    <th>VERBS</th>
    <th>LINK</th>
    <th>PATH_VARIABLE</th>
<th>Result</th>
  </tr>
  <tr>
    <td>GET</td>
    <td>http://localhost:8086/</td>
    <td> </td>
<td>Hi from test API</td>
  </tr>
  <tr>
    <td>GET</td>
    <td>http://localhost:8086/calculate/34/45</td>
    <td> 34,45</td>
<td>2060</td>
  </tr>
  <tr>
    <td>GET</td>
    <td>http://localhost:8086/get_answer/2060</td>
    <td>2060</td>
<td>status_code: 200
message: 79</td>
  </tr>


</table> 



</html>