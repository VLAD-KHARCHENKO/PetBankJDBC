# JDBC PETBANK

Tема: "Платежі" 

Клієнт реєструється в системі і має одну або кілька Кредитних карт, кожна з яких відповідає певному Рахунку в системі. Клієнт за допомогою Рахунку може здійснити Платіж.
Платіж має один з двох статусів: 'підготовлений' або 'відправлений'. (За бажанням: реалізувати можливість генерації pdf-звіту про платіж).
Клієнт має особистий кабінет, в якому може переглядати інформацію про свої платежі та рахунки. Необхідно реалізувати можливість сортування:
- платежів:
1) за номером;
2) за датою (від старих до нових, від нових до старих);
- рахунків:
1) за номером;
2) за найменуванням рахунку;
3) за розміром залишку коштів на рахунку.
Клієнт може поповнити або заблокувати один зі своїх рахунків. Для розблокування рахунку клієнт повинен зробити запит до на розблокування.
Адміністратор системи володіє правами:
- блокування / розблокування користувача;
- блокування / розблокування одного з рахунків користувача

## Технології
- DB - MySql 8.x
- Java version 8 or higher
- Maven

## Установка і запуск проекту

1. Clone project with 'git clone' command from command line
3. Run create_schema.sql from src/main/resources/scripts folder
4. Run insert_schema.sql from src/main/resources/script folder
5. Update DB username and password in scr/main/webapp/META_INF/context.xml (and in db.properties from src/main/resources folder)
6. Start mysql service with 'service mysql start' 
7. Go to project root ../PetBankJDBC directory and run in terminal command 'mvn clean tomcat7:run' or 'mvn tomcat7:run -f pom.xml'
8. Go to link http://localhost:8080/petbank
9.Use login:admin@g.com password:admin to check up admin functional
10.Use login:user1@g.com password:admin to check up admin user functional (or create your own account)
11. Use Ctrl+C command in command line to force quit and kill all app process
12. Stop mysql service with 'service mysql stop' or 'mysql.service stop' (http://www.mysqltutorial.org/mysql-adminsitration/stop-mysql/)

## Доступний функціонал

- #### Рівень доступу - ADMIN/USER:

1.  Стартова сторінка
2. Вхід/ реєстрація
3. Зміна свого профілю
4. Зміна мови
5. Вихід із системи

- #### Рівень доступу - USER:

6.  Створення платежу
7. Пловедення/видалення платежу
8.  Створення картки
9. Поповнення картки
10. Поповнення картки
11. Перегляд та сортування платежів та доступних карток
12. Блокування карток/ подача запиту на розблокування

- #### Рівень доступу - ADMIN:

13.  Редагування/блокування профілю юзера
14.  Блокування карток користувача/ розблокування
15. Розблокування карток користувача

