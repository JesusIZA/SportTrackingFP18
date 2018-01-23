## Task

10. Система Похудения/Трекинга Пищи.
Клиент выбирает еду (название, кол. белки, жиры, углеволы), которую съел (из уже готового списка) и пишет Количество.
Клиент может добавить свой тип Еды (название, калории, кол. белки, жиры, углеволы).
Если Клиент привысил дневную норму, система сообщит ему об этом и напишет, на сколько была превышена Норма.
Норму брать из параметров Клиент (возраст, рост, вес, образ жизни и т.д.).

## Requirements

- git
- maven
- mysql
- h2

## Manual Build

1. Clone the project
    ```bash
    git clone https://github.com/JesusIZA/SportTrackingFP18.git
    ```

2. Change directory
    ```bash
    cd SportTrackingFP18
    ```
3. Start h2 and mysql

4. Create empty mysql db and add data about it to src\main\java\ua\jr\raichuk\DB\utils\UtilConnectionPool.java

5. Create empty h2 db and add data about it to src\main\java\ua\jr\raichuk\DB\utils\UtilSimpleConnection.java

6. Run clean and default lifecycles (inclusive up to install phase)
    ```bash
    mvn clean install
    ```
    
7. Put compiled files to foldet tomcat`s ROOT and start the tomcat

## Run

Open browser and put this command 
