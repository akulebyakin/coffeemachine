--ingredients
CREATE TABLE `ingredients` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `balance` INT NOT NULL,
  `unit` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `ingredients` VALUES (DEFAULT, 'Water', '10000', 'ml');
INSERT INTO `ingredients` VALUES (DEFAULT, 'Coffee', '1000', 'g');
INSERT INTO `ingredients` VALUES (DEFAULT, 'Sugar', '500', 'g');
INSERT INTO `ingredients` VALUES (DEFAULT, 'Milk', '10000', 'ml');
INSERT INTO `ingredients` VALUES (DEFAULT, 'Cream', '1000', 'ml');
INSERT INTO `ingredients` VALUES (DEFAULT, 'Chocolate sauce', '50', 'ml');
INSERT INTO `ingredients` VALUES (DEFAULT, 'Caramel sauce', '50', 'ml');
INSERT INTO `ingredients` VALUES (DEFAULT, 'Cinnamon', '50', 'g');
INSERT INTO `ingredients` VALUES (DEFAULT, 'Marshmallow', '50', 'g');
INSERT INTO `ingredients` VALUES (DEFAULT, 'Coconut flakes', '50', 'g');



--recipes
CREATE TABLE recipes(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price INT NOT NULL,
    available TINYINT NOT NULL DEFAULT 1,
    total_sold INT NOT NULL DEFAULT 0
);

INSERT INTO `recipes` VALUES (DEFAULT, 'Espresso', 105, DEFAULT, DEFAULT);
INSERT INTO `recipes` VALUES (DEFAULT, 'Double espresso', 175, DEFAULT, DEFAULT);
INSERT INTO `recipes` VALUES (DEFAULT, 'Americano', 105, DEFAULT, DEFAULT);
INSERT INTO `recipes` VALUES (DEFAULT, 'Cappuccino', 225, DEFAULT, DEFAULT);
INSERT INTO `recipes` VALUES (DEFAULT, 'Latte', 225, DEFAULT, DEFAULT);
INSERT INTO `recipes` VALUES (DEFAULT, 'Moccaccino Dark', 300, DEFAULT, DEFAULT);
INSERT INTO `recipes` VALUES (DEFAULT, 'Moccaccino White', 300, DEFAULT, DEFAULT);
INSERT INTO `recipes` VALUES (DEFAULT, 'Raf', 275, DEFAULT, DEFAULT);
INSERT INTO `recipes` VALUES (DEFAULT, 'Flat White', 250, DEFAULT, DEFAULT);



--drink_compositions
CREATE TABLE drink_compositions(
    recipe_id INT NOT NULL,
    ingredient_id INT NOT NULL,
    amount INT NOT NULL
);

ALTER TABLE drink_compositions ADD FOREIGN KEY (recipe_id) REFERENCES recipes (id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE drink_compositions ADD FOREIGN KEY (ingredient_id) REFERENCES ingredients (id) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('1', '1', '40');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('1', '2', '7');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('2', '1', '80');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('2', '2', '14');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('3', '1', '200');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('3', '2', '7');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('4', '1', '40');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('4', '2', '7');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('4', '4', '300');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('5', '1', '40');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('5', '2', '7');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('5', '4', '300');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('6', '1', '40');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('6', '2', '7');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('6', '4', '300');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('6', '5', '50');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('6', '6', '30');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('6', '9', '5');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('7', '1', '40');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('7', '2', '7');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('7', '4', '300');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('7', '5', '50');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('7', '7', '30');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('7', '9', '5');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('8', '1', '40');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('8', '2', '7');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('8', '3', '200');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('8', '4', '50');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('8', '5', '4');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('9', '1', '40');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('9', '2', '14');
INSERT INTO `drink_compositions` (`recipe_id`, `ingredient_id`, `amount`) VALUES ('9', '4', '200');


--drinks_sold
CREATE TABLE `drinks_sold` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `quantity` INT NOT NULL,
  `paid_by_cash` INT NOT NULL DEFAULT 0,
  `paid_by_card` INT NOT NULL DEFAULT 0,
  `date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);