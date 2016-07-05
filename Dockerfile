FROM tomcat:8.0.36-jre8

COPY ./target/rest-prime*.war $CATALINA_HOME/webapps/rest-prime.war
