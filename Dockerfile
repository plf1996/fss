FROM java:8
USER root
RUN mkdir /app
COPY *.jar /app/app.jar
WORKDIR /app
EXPOSE 8080

CMD [ "java -jar /app/app.jar" ]
