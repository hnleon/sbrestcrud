<h1>SpringBoot + Rest CRUD test task</h1>
<h2>Task details:</h2>
Create Spring-boot service application with REST CRUD methods for this entities:
<ul>
<li>category</li>
<li>product</li>
<li>order</li>
<li>orderItem</li>
</ul>
<p>Entity details:</p>
<ul>
<li>Category contains multiple products and name</li>
<li>Product contains price, sku and name</li>
<li>Order item contains “quantity” value and one-to-one connection to product</li>
<li>Order contains multiple order items and sum of total charge</li>
<li>Add report controller which returns date and sum of income grouped by day (2016-08-22, 250.65, 2016-08.23, 571.12 ... etc) in JSON format</li>
</ul>
<p><b>Bonus:</b> make integration with Swagger</p>
<p><b>Technology stack:</b> Spring-boot, Hibernate, Liquibase, PostgreSQL.</p>

<h2>Implementation details:</h2>
<ul>
<li>file "src/main/resources/application.properties" contains all required configuration settings; db-connection details must be set here before any tests performed;</li>
<li>project configured to "create-drop" database structure and has some mock records. File "src/main/resources/import.sql" contains all data insertion routines;</li>
<li>swagger support is not implemented - never did this before (sadly :( )</li>
</ul>

<h3>GET REST links</h3>
<ul>
<li><b>Date and sum of income grouped by day: </b>http://localhost:8080/stats/daily</li>
<li><b>All repositories: </b>http://localhost:8080/</li>
<li><b>Orders: </b>http://localhost:8080/orders</li>
<li><b>Categories </b>http://localhost:8080/categories</li>
<li><b>Products </b>http://localhost:8080/products</li>
</ul>

<h3>Create REST usage samples</h3>
<ul>
<li><b>Order: </b>curl -X POST -H 'Content-Type: application/json' -d '[{"productId": 7, "quantity": 3}, {"productId": 11, "quantity": 4}, {"productId": 24, "quantity": 5}]' http://localhost:8080/api/order</li>
<li><b>Category: </b>curl -X POST -H 'Content-Type: application/json' -d '{"name":"New Category"}' http://localhost:8080/categories</li>
<li><b>Product: </b>curl -X POST -H 'Content-Type: application/json' -d '{"name":"New Item Name","price":0,"sku":"sku value","category_id":1}' http://localhost:8080/products</li>

</ul>

<h3>Delete REST usage samples</h3>
<ul>
<li><b>Order: </b>curl -X DELETE http://localhost:8080/orders/1</li>
<li><b>Category: </b>curl -X DELETE http://localhost:8080/categories/1</li>
<li><b>Product: </b>curl -X DELETE http://localhost:8080/products/1</li>
</ul>
