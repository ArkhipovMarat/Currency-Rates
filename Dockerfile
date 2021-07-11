FROM openjdk:11-jre-slim-buster

ENV PORT 8080
EXPOSE $PORT

ARG JAR=build/libs/ALFA-CurrencyRates-refactored-0.0.1-SNAPSHOT.jar
ADD $JAR currency_rates.jar

ENTRYPOINT ["java", "-jar", "/currency_rates.jar"]

