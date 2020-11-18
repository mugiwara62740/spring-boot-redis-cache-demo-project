FROM java:8
VOLUME /tmp
ADD /target/spring-boot-redis-cache-0.0.1-SNAPSHOT.war spring-boot-redis-cache-0.0.1-SNAPSHOT.war
RUN bash -c 'touch /spring-boot-redis-cache-0.0.1-SNAPSHOT.war'
ENTRYPOINT ["java", "-Dspring.config.location=/usr/local/application.yml", "-jar", "/spring-boot-redis-cache-0.0.1-SNAPSHOT.war"]