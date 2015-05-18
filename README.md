Restlet Demo Application With RAML Documentation
================================================

[![Build Status](https://semaphoreci.com/api/v1/projects/067fb9df-d7d0-4651-b933-de467c54317a/427209/badge.svg)](https://semaphoreci.com/infosec812/codepalousa-restlet-raml)

Overview
--------

An example application using Restlet Framework, Apache DeltaSpike, and the Restlet RAML module. This was an attempt
which did not succeed (Restlet Version 2.3.1). The RAML introspection is not complete and does not generate JSON/XML
schemas or examples. I will revisit this at another time when Restlet has improved


Prerequisites
-------------
* Java >= 1.8
* Maven >= 3.3.1

Building
--------

```bash
git clone git@github.com:InfoSec812/codepalousa-restlet-raml.git
cd codepalousa-restlet-raml
mvn clean test
```

Running
-------

```bash
mvn exec:java
```
