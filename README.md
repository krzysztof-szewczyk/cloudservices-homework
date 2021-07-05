# 1. Run docker

   Path: ./docker/docker-compose.yml   
   Command: <b>docker-compose up</b>
   
   MongoExpress: http://localhost:8081   

# 2. Run SpringBoot app

   Path: ./   
   Command: <b>./gradlew bootrun</b>
   
   HomeworkApp: http://localhost:8080  
   Swagger: http://localhost:8080/swagger-ui/index.html
   
# Disclaimers
   
   - Hexagonal Architecture has been used
   - updated can be either proposal content or proposal state (not both together)
   - filtering
      - ignore null params (name or state)
      - name contains given phrase ignoring case
   - for change history (auditing) has been used JaVers lib (no endpoint was created)
