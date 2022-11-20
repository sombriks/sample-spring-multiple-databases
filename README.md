# Getting Started

Configure one spring boot datasource is a matter of simply declare the correct
properties into [application.properties](src/main/resources/application.properties)
file and let autoconfiguration work.

But things are different when we need a second one.

This project tries to sample how to configure multiple databases (enabling jdb,
jpa and spring repositories) in a single project.

## General steps

- define two sets of datasource configurations in properties file
- for each database, define distinct package sets for entities and repositories
- define two @Configuration classes defining these components:
  - DataSourceProperties
  - DataSource
  - LocalContainerEntityManagerFactoryBean
  - PlatformTransactionManager
- starting from java 11, add '--add-modules java.sql' in compiler options
- one of those configurations MUST annotate beans as @Primary

## Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.13/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.13/maven-plugin/reference/html/#build-image)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/2.6.13/reference/htmlsingle/#howto.data-access.exposing-spring-data-repositories-as-rest)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.6.13/reference/htmlsingle/#appendix.configuration-metadata.annotation-processor)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.13/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [JDBC API](https://docs.spring.io/spring-boot/docs/2.6.13/reference/htmlsingle/#data.sql)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.13/reference/htmlsingle/#web)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.13/reference/htmlsingle/#using.devtools)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/2.6.13/reference/htmlsingle/#web.reactive)

## Guides

The following guides illustrate how to use some features concretely:

* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)

