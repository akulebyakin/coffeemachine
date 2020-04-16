# coffeemachine
Project for EPAM training

### Task: <br>
**Coffee machine system.** <br>
The coffee machine has a set of drinks. Customer can buy one or more drinks.

**What needs to be done:** <br>
1. Create a database (MySQL, MS SQL, SQLite, PostgreSQL etc.);
2. Organize access to the database from the application using JDBC and the DAO design pattern;
3. Implement necessary services that will use the data from the DB (get, get by parameter, 
get in a certain order, get statistics based on the received data etc.);
4. Implement the output of the result of the service layer to the console;
5. Make JUnit tests for the service layer;
6. Classes and interfaces must be well organized by packages;
7. Observe Java Code Conventions;

---

### Project Structure <br>
#### MySQL tables<br>

**ingredients** - coffee ingredients <br>

    +----+-----------------+---------+------+
    | id | name            | balance | unit |
    +----+-----------------+---------+------+
    |  1 | Water           |   10000 | ml   |
    |  2 | Coffee          |    1000 | g    |
    |  3 | Sugar           |     500 | g    |
    +----+-----------------+---------+------+

**recipes** - name of each drink and its price <br>

    +----+------------------+-------+-----------+------------+
    | id | name             | price | available | total_sold |
    +----+------------------+-------+-----------+------------+
    |  1 | Espresso         |   105 |         1 |          6 |
    |  2 | Double espresso  |   175 |         1 |          1 |
    |  3 | Americano        |   105 |         1 |          4 |
    +----+------------------+-------+-----------+------------+
    
**drinks_sold** - sales information <br>

    +----+------------+----------+-------------+--------------+--------------+---------------------+-------------+
    | id | name       | quantity | total_price | paid_by_cash | paid_by_card | date                | client_name |
    +----+------------+----------+-------------+--------------+--------------+---------------------+-------------+
    |  1 | Espresso   |        1 |         105 |          105 |            0 | 2020-04-02 20:17:53 | Noname      |
    |  2 | Raf        |        1 |         275 |            0 |          275 | 2020-04-04 17:57:26 | Andrey      |
    |  3 | Cappuccino |        1 |         225 |            0 |          225 | 2020-04-04 17:57:53 | Andrey      |
    +----+------------+----------+-------------+--------------+--------------+---------------------+-------------+
    
**drink_compositions** - intermediate table that contains compositions of the drinks <br>

    +-----------+---------------+--------+
    | recipe_id | ingredient_id | amount |
    +-----------+---------------+--------+
    |         1 |             1 |     40 |
    |         1 |             2 |      7 |
    |         2 |             1 |     80 |
    |         2 |             2 |     14 |
    |         3 |             1 |    200 |
    |         3 |             2 |      7 |
    +-----------+---------------+--------+
 
#### Code structure<br>
 
 ![code structure](http://joxi.ru/nAy5yPZujDo4VA.png)
 
---

### Manual <br>

Introduce yourself! <br>

    Welcome to the Red Tiger Coffee House! Please tell me your name.
    My name is: Andrey

If you type 'admin', you'll get access to special features.
Choose an action. Type 'help' to get all available commands Available operations:<br> 

    List of all client commands:
    	menu - get the menu
    	make Cappuccino - makes Cappuccino. Put any drink name instead of 'Cappuccino'. 
    	  If a drink with the name exists, it will be made
    	getAllDrinkCompositions - get compositions of all drinks
    	getMostPopularDrinks - get a list with 5 most popular drinks
    
    List of all admin commands:
    	getAllIngredients - get a list with all ingredients
    	getEndingIngredients - get a list with ending ingredients
    	getAllRecipes - display all recipes
    	getUnavailableRecipes - display all unavailable recipes
    	getAllSales - list of all sales
    	getSalesToday - list of only today sales
    	addIngredient - create new one or upgrade existing ingredient
    	createNewRecipe - cmake a new drink
    	deleteRecipeByName - delete a recipe. Notice: Composition of a drink will be deleted.