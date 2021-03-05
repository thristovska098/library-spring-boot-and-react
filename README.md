# library-spring-boot-and-react
This project performs CRUD operations with library entities.

### Built with:
* Spring Boot
* Maven 
* Postgresql
* React

### Listening ports:
After you start the application the server will listen on port 8080 and the client will listen on port 3000.

### Demo of Sring Boot app 
* Get all books  
HTTP GET localhost:8080/api/books

* Get book with id 2  
HTTP GET localhost:8080/api/books/2

* Get books with title "Alice in wonderland"  
HTTP GET localhost:8080/api/books/find?name=Alice in wonderland

* Create new book  
HTTP POST localhost:8080/api/books  
Payload Json Body  
{"name":"Alice in wonderland",  
"edition":1,  
"description":"One normal summer day, Alice is sitting on the riverbank when a big..",  
"sources": [{  
	"format": "pdf",  
	"url":"https://www.adobe.com/be_en/active-use/pdf/Alice_in_Wonderland.pdf",  
	"imgSrc":"https://embed.cdn.pais.scholastic.com/v1/channels/sso/products/identifiers/isbn/9780439291491/primary/renditions/700?useMissingImage=true"  
	}],  
"publisher":{  
"name": "Some publisher..",  
             "address":"Address of the publisher"  
	    },  
"authors":[{"ssn":"0000006776111",  
	  "name":"Lewis",  
	  "lastName":"Carroll",  
	  "birthDate":"1898-27-01"}],  
"categories":[{  
	"name":"Fantasy"  
}]  
}  

* Update existing book with id=1  
HTTP PUT localhost:8080/api/books/1  
Payload Json Body  
{"name":"Alice in wonderland",  
"edition":1,  
"description":"One normal summer day, Alice is sitting on the riverbank when a big..",  
"sources": [{  
	"format": "pdf",  
	"url":"https://www.adobe.com/be_en/active-use/pdf/Alice_in_Wonderland.pdf",  
	"imgSrc":"https://embed.cdn.pais.scholastic.com/v1/channels/sso/products/identifiers/isbn/9780439291491/primary/renditions/700?useMissingImage=true"  
	}],  
"publisher":{  
"name": "Some publisher..",  
             "address":"Address of the publisher"  
	    },  
"authors":[{"ssn":"0000006776111",  
	  "name":"Lewis",  
	  "lastName":"Carroll",  
	  "birthDate":"1898-27-01"}],  
"categories":[{  
	"name":"Fantasy"  
}]  
}  

* Delete Resource with id 2  
localhost:8080/api/books/2 

### Author 
* Teodora Hristovska

