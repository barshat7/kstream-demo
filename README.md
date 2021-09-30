# About

This demo shows the usage of kafka and kafka streams. \
It's a simple application that listens to BlogCreatedEvent, applies some processing \
And loads data to Elastic Search for indexing and full text searches.



## Starting Kafka In Local Using Docker
```shell
docker-compose -f docker-compose-single-broker.yml up
```