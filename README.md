# TrueLayer-Challenge
TrueLayer Challenge
#### Prerequisites

Installed: [Docker](https://www.docker.com/), [Java 1.8 or 1.11](https://www.oracle.com/technetwork/java/javase/overview/index.html), [Maven 3.x](https://maven.apache.org/install.html), [git](https://www.digitalocean.com/community/tutorials/how-to-contribute-to-open-source-getting-started-with-git), optional [Docker-Compose](https://docs.docker.com/compose/install/)

#### Steps

##### Clone source code from git
```
$  git clone https://github.com/Vegetam/TrueLayer-Challenge  .
```

### Run with docker-compose 

Build and start the container by running 

```
$ docker-compose up -d 
```

##### Run unit tests:
```
mvn test
```

##### Run integration tests:
```
mvn integration-test
```

##### Test application

```
http://localhost:8080/pokemon/api-docs-ui

and under PokemonName use charizard or blastoise

Note that is case sensitive so if you wrote Ditto you won't receive any correct description
```

the response should be:
```
{"name":"charizard","description":"Charizard flies 'round the sky in search of powerful opponents. It breathes fire of such most wondrous heat yond 't melts aught. However 't nev'r turns its fiery breath on any opponent weaker than itself."}
```
### Run without docker-compose

##### Build Docker image
```
docker build --tag fm-pokemon-description:1.0 .
```

>Note:if you run this command for first time it will take some time in order to download base image from [DockerHub](https://hub.docker.com/)

##### Run Docker Container
```
docker run -p 8080:8080 --rm fm-pokemon-description:1.0
```

#####  Stop Docker Container:
```
docker stop `docker container ls | grep "fm-pokemon-description:1.0:*" | awk '{ print $1 }'`
```

```
