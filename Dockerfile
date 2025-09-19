FROM tomcat:11.0-jdk17

RUN rm -rf /usr/local/tomcat/webapps/*

COPY target/exsh_12_sql_gateway-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war


EXPOSE 8080

CMD ["catalina.sh", "run"]



