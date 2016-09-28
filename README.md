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
<h3>"GET" REST links</h3>
<ul>
<li>http://localhost:8080/ - list of all repositories;</li>
<li>http://localhost:8080/categories;</li>
<li>http://localhost:8080/products;</li>
<li>http://localhost:8080/orders;</li>
<li>http://localhost:8080/stats/daily - <i>report controller which returns date and sum of income grouped by day</i>;</li>
</ul>

<h3>Other REST "link" usage</h3>
<ul>
<li>curl -X DELETE http://localhost:8080/categories/1</li>
<li>curl -X DELETE http://localhost:8080/products/1</li>
<li>curl -X DELETE http://localhost:8080/orders/1</li>
<li>curl -X POST -H 'Content-Type: application/json' -d '{"name":"New Category"}' http://localhost:8080/categories</li>
<li>curl -X POST -H 'Content-Type: application/json' -d '{"name":"New Item Name","price":0,"sku":"sku value","category_id":1}' http://localhost:8080/products</li>
</ul>

