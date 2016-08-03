# example-2016

Beispielanwendung für den Leitfaden REST-API im Kurs Management der Anwendungsentwicklung der HTW-Berlin.


## Benutzung des Beispielcodes
Es handlet sich um ein Spring-Boot Project. Zum Ausführen des Programmes auf dem lokalen Rechner sind folgende Schritte notwendig.

1. Install PostgreSQL and REDIS on local machine
2. Checkout git Repository
3. run "mvn spring-boot:run"

## Authentication 
Der Username und das Kennwort steht im Code in der Klasse WebSecurityConfiguration

- Request Token URL: http://localhost:8080/oauth/token?grant_type=password&username=USERNAME&password=PASSWORD
