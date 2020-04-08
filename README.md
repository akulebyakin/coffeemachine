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

**recipes** - composition of each drink and its price <br>

    +----+------------------+--------------------+-------------------------+-------+-----------+------------+
    | id | name             | ingredient_ids     | ingredient_amount       | price | available | total_sold |
    +----+------------------+--------------------+-------------------------+-------+-----------+------------+
    |  1 | Espresso         | [1, 2]             | [40, 7]                 |   105 |         1 |          0 |
    |  2 | Double espresso  | [1, 2]             | [80, 14]                |   175 |         1 |          0 |
    |  3 | Americano        | [1, 2]             | [200, 7]                |   105 |         1 |          0 |
    +----+------------------+--------------------+-------------------------+-------+-----------+------------+

**drinks_sold** - sales information <br>

    +----+------------+----------+-------------+--------------+--------------+---------------------+-------------+
    | id | name       | quantity | total_price | paid_by_cash | paid_by_card | date                | client_name |
    +----+------------+----------+-------------+--------------+--------------+---------------------+-------------+
    |  1 | Espresso   |        1 |         105 |          105 |            0 | 2020-04-02 20:17:53 | Noname      |
    |  2 | Raf        |        1 |         275 |            0 |          275 | 2020-04-04 17:57:26 | Andrey      |
    |  3 | Cappuccino |        1 |         225 |            0 |          225 | 2020-04-04 17:57:53 | Andrey      |
    +----+------------+----------+-------------+--------------+--------------+---------------------+-------------+
 
 #### Code structure (temp)<br>
 
 ![code structure](http://joxi.ru/gmvExPdfvKnB8r.png)
 
---

### Manual <br>

Introduce yourself! <br>

    Welcome to the Red Tiger Coffee House! Please tell me your name.
    My name is: Andrey

Choose an action. Available operations:<br> 
**menu** - displays the menu <br>

    Operation: menu
    1. Espresso - 105 rub. 
    2. Double espresso - 175 rub. 
    3. Americano - 105 rub. 
    4. Cappuccino - 225 rub. 
    5. Latte - 225 rub. 
    6. Moccaccino Dark - 300 rub. Not available!
    7. Moccaccino White - 300 rub. 
    8. Raf - 275 rub. 
    9. Flat White - 250 rub. 

**make Cappuccino** - makes Cappuccino. Put any drink name instead of 'Cappuccino'. 
If a drink with the name exists, it will be made <br>

    Operation: make Cappuccino
    I'm making 'Cappuccino'
    Done! Drinks made: 1

**getDrinkComposition** - displays composition of all drinks

     Operation: getDrinkComposition
     1. Espresso:
     	Water - 40 ml
     	Coffee - 7 g
     
     2. Double espresso:
     	Water - 80 ml
     	Coffee - 14 g
     ...
  