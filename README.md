# Groovy Spring Boot RESTdocs Example [![Build Status](https://travis-ci.org/jlstrater/groovy-spring-boot-restdocs-example.svg?branch=master)](https://travis-ci.org/jlstrater/groovy-spring-boot-restdocs-example)

This project is an example of a groovy spring boot app with Spring RESTdocs added to the spock tests for the talk `A Test-Driven Approach to Documenting RESTful APIs with Spring REST Docs` as presented by Jennifer Strater.

The published docs from the example are available [here](http://jlstrater.github.io/groovy-spring-boot-restdocs-example/).

### Upcoming Presentations

You can see this talk at any of the following events:

| Venue         | Date          | City  |
| ------------- |:-------------:|-----|
|Groovy Users of Minnesota | Feb 9, 2016 | Minneapolis, MN, USA
| Object Partners | March 18, 2016 | Minneapolis, MN, USA
| Twin Cities Code Camp | April 16, 2016 | Minneapolis, MN, USA
| Gr8Conf EU | June 2-3, 2016 | Copenhagen, Denmark
| Gr8Conf US | July 28, 2016 | Minneapolis, MN, USA
| JFokus | Feb 8, 2017 | Stockholm, Sweden
| Greach | March 31, 2017 | Madrid, Spain
| Code Europe | April 26, 2017 | Kraków, Pl
| API Conference DE | September 20, 2017 | Berlin, DE
| Spring One Platform | December 7, 2017 | San Francisco, CA, USA

Slides for previous presentations are available below:

* [9 Feb 2016 (GUM)](https://speakerdeck.com/jlstrater/test-driven-approaches-to-documenting-restful-apis)
* [18 March 2016 (OPI)](https://speakerdeck.com/jlstrater/a-test-driven-approach-to-documenting-restful-apis-with-spring-rest-docs)
* [16 April 2016 (TCCC)](https://speakerdeck.com/jlstrater/test-driven-approaches-to-documenting-restful-apis-1)
* [June 2016 (Gr8Conf EU)](https://speakerdeck.com/jlstrater/a-test-driven-approach-to-documenting-restful-apis-with-spring-rest-docs-gr8conf-eu-2016)
* [July 2016 (Gr8Conf US)](https://speakerdeck.com/jlstrater/a-test-driven-approach-to-documenting-restful-apis-with-spring-rest-docs-gr8conf-us)
* [Feb 2017 (JFokus)](https://speakerdeck.com/jlstrater/test-driven-docs-jfokus-2017)
* [March 2017 (Greach)](https://speakerdeck.com/jlstrater/test-driven-docs-greach-2017)
* [April 2017 (Code Europe)](https://speakerdeck.com/jlstrater/test-driven-docs-code-europe-2017)
* [September 2017 (ApiConf DE)](https://speakerdeck.com/jlstrater/test-driven-docs-apiconf-de-2017)
* [December 2017 (SpringOne Platform)](https://speakerdeck.com/jlstrater/test-driven-docs-springone-2017)

-----

You can build and run this sample using Gradle:

```
$ gradle build bootRun
```

Or the Gradle Wrapper:

```
$ ./gradlew build bootRun
```

Then access the app via a browser (or curl) on http://localhost:8080/html5/index.html to see the docs.

** It is very important to note that the project must be built before trying to run the project. Otherwise, the docs will not show up! **

**** As of December 2017/SpringOnePlatform MongoDB is a dependency to run this project. ****