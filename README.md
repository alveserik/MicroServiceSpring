# MicroServiceSpring

WebApi is a API utilizing Java and the framework Spring that retrieve transactions from OpenBank sandbox and transforms the data using a provided mapping.

The API has 3 endpoints:
1 - Transactions list (get a list of transactions)  
http://localhost:8080/WebApi/transaction/list

2 - Transaction filter based on transaction type (get list filtered by parameter)  
http://localhost:8080/WebApi/transaction/getListByType?type={type}

3 - Total amount for transaction type(get total amount per )  
http://localhost:8080/WebApi/transaction/getTotalTransactionByType?type={type}

## How to start project

The project has a embedded server of Tomcat 7, for starting the project it is necessary to create a Maven Goal. For Starting follow the below steps:

1 - Click with the right button of the mouse on the project  
2 - Click on Run As  
3 - Run Configurations  
4 - Click twice on Maven Build  
5 - Write a name for the build  
 Example: Tomcat Server  
6 - Select workspace of the project (in the case WebApi) behind Base directory  
7 - On goals write the following command:  
 tomcat7:run  
8 - Click on Apply and Run  
9 - The port is configured on the pom.xml to port 8080
