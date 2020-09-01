# TrueLayer-Challenge
TrueLayer Challenge
#### Prerequisites

Installed: [Docker](https://www.docker.com/), [Java 1.8 or 1.11](https://www.oracle.com/technetwork/java/javase/overview/index.html), [Maven 3.x](https://maven.apache.org/install.html), [git](https://www.digitalocean.com/community/tutorials/how-to-contribute-to-open-source-getting-started-with-git), optional [Docker-Compose](https://docs.docker.com/compose/install/)

#### Steps

##### Clone source code from git
```
$  git clone https://github.com/.
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
```

the response should be:
```
{"name":"charizard","description":"Charizard flies 'round the sky in search of powerful opponents. It breathes fire of such most wondrous heat yond 't melts aught. However 't nev'r turns its fiery breath on any opponent weaker than itself."}
```

##### Stop Docker Container:
```
docker-compose down
```

##### Run Docker Container
```
$ docker run -p 8080:8080 -it --rm truelayerchallenge_fm-pokemon-shakespeare-description
```

#####  Stop Docker Container:
```
docker stop `docker container ls | grep "truelayerchallenge_fm-pokemon-shakespeare-description:*" | awk '{ print $1 }'`
```
