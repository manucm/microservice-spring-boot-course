# microservice-spring-boot-course

### zipkin
````
docker run -d -p 9411:9411 openzipkin/zipkin
````

### keycloak
````
 docker run -p 8000:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:21.1.1 start-dev
````