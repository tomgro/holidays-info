# Instructions how to run code:
1. Open terminal
2. git clone https://github.com/tomgro/holidays-info
3. cd holidays-info
4. mvn clean install
5. mvn spring-boot:run
6. Open browser
7. Paste e.g. http://localhost:8080/holidays?date=2016-03-25&countryCode1=PL&countryCode2=DE

Response should be
{"date":"2016-03-28","name1":"Poniedziałek Wielkanocny","name2":"Easter Monday"}

