# dataprocessor

This is a Spring Boot project using Java 8. The packaging type is a Jar and no external server will be needed to run this. 
I have uploaded the runnable jar too. It is in the target directory. Just pull the jar and 
go to the directory where you have saved it from the command line. And run the command : java -jar parser-0.0.1-SNAPSHOT.jar
Then go to any browser and hit : http://localhost:8080/items

Alternatively, Just need to import the project, do a maven build to fetch all the dependencies and simply right clicking the project and Run As-> Spring Boot Application will make the application run. The IDE is Spring Tool Suite.

The input I have taken is http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/CategoryDisplay?listView=true&orderBy=FAVOURITES_FIRST&parent_category_rn=12518&top_category=12518&langId=44&beginIndex=0&pageSize=20&catalogId=10137&searchTerm=&categoryId=185749&listId=&storeId=10151&promotionId=#langId=44&storeId=10151&catalogId=10137&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20&orderBy=FAVOURITES_FIRST&searchTerm=&beginIndex=0&hideFilters=true

The output I am getting is( the below output does not print the size of the document properly, need to check why is it not working properly) :

{
"results":[
{
"title":"Sainsbury's Avocado, Ripe & Ready x2 ", "size":"-1kb", "unitPrice":1.6, "description":"Avocados" }, {
"title":"Sainsbury's Conference Pears, Ripe & Ready x4", "size":"-1kb", "unitPrice":1.95, "description":"Conference" }, {
"title":"Sainsbury's Mango, Ripe & Ready x2 ", "size":"-1kb", "unitPrice":1.55, "description":"by Sainsbury's Ripe and Ready Mango" }, {
"title":"Sainsbury's Kiwi Fruit, Ripe & Ready x4", "size":"-1kb", "unitPrice":1.75, "description":"Kiwi" }, {
"title":"Sainsbury's Kiwi Fruit, SO Organic x4", "size":"-1kb", "unitPrice":1.35, "description":"Organic Kiwi" }, {
"title":"Sainsbury's Pears, Ripe & Ready x4", "size":"-1kb", "unitPrice":1.95, "description":"Pear" }, {
"title":"Sainsbury's Peaches Ripe & Ready x4", "size":"-1kb", "unitPrice":2.5, "description":"Ripe and Ready Peach sweet & juicy peaches" }, {
"title":"Sainsbury's Tree Ripened Papaya Each ", "size":"-1kb", "unitPrice":1.25, "description":"Papaya" }, {
"title":"Sainsbury's Avocados, Ripe & Ready x4 ", "size":"-1kb", "unitPrice":2.5, "description":"Avocados" }, {
"title":"Sainsbury's Ripe & Ready Extra Large Avocados Each ", "size":"-1kb", "unitPrice":1.65, "description":"Avocados" } ], "total":18.05 }
