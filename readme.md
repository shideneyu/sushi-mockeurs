# Sushi-mockeurs

Sushi-mockeurs is a **Java J2EE** Application that runs on top of **Spring** and **Maven**.

## How to run

Go to the root of this project and run:

```sh
$ mvn spring-boot:run
```

To run the specs:

```sh
$ mvn test
```

## What is working ?

* GET (one or all), POST and PUT requests work using the REST conventions
* Users are showed anonymously
* A basic graphical interface is runing based on the API
* Exceptions for errors
* Tests depends (and run) Spring
* Some datas are insterted after Spring's startup

## Tests
All tests are made to avoid corrupting the DB as much as possible

* Get one phone
* Get all the phones
* Add a phone
* Change the stolen state of a phone
* Get one phone with a wrong identifier
 

## Contributing

1. Fork it ( https://github.com/shideneyu/sushi-mockeurs/fork )
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create a new Pull Request

## Licence

Copyright (c) 2015 Sidney Sissaoui, Justine Delalleau, Hugo Gennaro, Anne-Laura Bulin, Benoît Delorme, released under the MIT license

