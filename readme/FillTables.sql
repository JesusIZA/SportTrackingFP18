/*USERS*/
INSERT INTO USERS (login, password) VALUES
  ('Jesus', '12'),
  ('Login2', 'pass2'),
  ('Login3', 'pass3'),
  ('Admin', '12'),
  ('Admin2', '12');

/*FOODS*/
INSERT INTO FOODS (name, calories, proteins, fats, carbohydrates) VALUES
  ('Cutlet in Kiev', 239.0, 25.0, 23.0, 17.0),
  ('Pasta', 187.0, 13.0, 10.0, 23.0),
  ('French fries', 214.0, 3.0, 19.0, 25.0),
  ('Pea soup', 120.0, 4.0, 5.0, 7.0),
  ('Borsch', 126.0, 10.0, 5.0, 12.0),
  ('Dumplings with potatoes', 178.0, 12.0, 17.0, 23.0),
  ('Dumplings with cheese', 196.0, 15.0, 20.0, 20.0),
  ('Dumplings', 238.0, 23.0, 15.0, 14.0),
  ('Caesar salad', 85.0, 5.0, 1.0, 2.0);

/*PROFILES*/
INSERT INTO PROFILES (name, surname, sex, birthday, height, weight, activeTime) VALUES
  ('Толя', 'Фомич', 'male', '1996-05-12', 184, 82, 20),
  ('Сара', 'Коннор', 'female', '1997-06-12', 164, 52, 30),
  ('Марша', 'Рорук', 'female', '1999-07-22', 174, 56, 40);

/*WASEATEN*/
INSERT INTO WASEATEN (idP, idF, dateWE) VALUES
  (1, 1,'2017-05-11'),
  (1, 3,'2017-05-12'),
  (1, 5,'2017-06-16'),
  (1, 1,'2017-07-18'),
  (1, 2,'2017-07-19'),
  (2, 1,'2017-05-14'),
  (2, 3,'2017-05-12'),
  (2, 5,'2017-06-16'),
  (2, 1,'2017-07-18'),
  (2, 2,'2017-07-19'),
  (3, 1,'2017-05-14'),
  (3, 3,'2017-05-12'),
  (3, 5,'2017-06-16'),
  (3, 1,'2017-07-18'),
  (3, 2,'2017-07-19');

/*NORMS*/
INSERT INTO NORMS (calories, proteins) VALUES
  (4356, 399),
  (2345, 256),
  (2767, 311);

/*LINKS*/
INSERT INTO LINKS (idU, idP, idN) VALUES
  (1, 1, 1),
  (2, 2, 2),
  (3, 3, 3);