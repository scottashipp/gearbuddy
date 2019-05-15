# gearbuddy [![Build Status](https://travis-ci.org/scottashipp/gearbuddy.svg?branch=master)](https://travis-ci.org/scottashipp/gearbuddy)
Track your outdoor gear purchases. Make reusable gear lists. Know where things are.

GearBuddy is built with Java 11, Spring Boot, Maven, JUnit, Mockito, Jacoco, Docker, MySql, AWS ECR and ECS, among others.

## In this readme
- [What is GearBuddy](#what-is-gearbuddy)
- [How to build and run](#build-and-run)
- [Example cURL commands](#use-the-app)
- [License](#license)

## What is GearBuddy

GearBuddy is the application I always wished I had for managing all my outdoor gear. It records information about gear purchases (like where and when it was purchased) that will be useful for warranty claims or knowing when something needs replaced.

For example:

```
Gear Type: Water Shoes
Gear Brand: Keen
Gear model: Seacamp II CNX Sandals - Kids
Gear color: Steel Grey/Rapture Rose
Gear size: 11 kids
Retailer: REI
Retail location: Seattle
Purchase price: $54.95
Purchase date: 04/21/2019
Warranty: 1 year
```

Furthermore, I can organize that gear into reusable lists with categories, making it easy to pack up and insure everything gets in.

### Example List

----

#### Car Camping

##### Mess
- Coleman stove
- REI camp kitchen

##### Sleep
- Coleman Montana tent
- Kelty Cosmic 20 sleeping bag

##### Fire
- Sol All-Weather Fire cubes
- Fire extinguisher
- Coleman hatchet
----

Lists can be copied and modified for specific trips, and have a checklist feature so you can mark off items as you pack. The checks can be easily cleared so you can make sure you get everything back in when your trip is done.

## Build and run
GearBuddy builds are managed by Maven. As you can probably tell from the build badge at the top of this README, Travis CI is also used.

At any rate, GearBuddy follows standard Maven conventions, as follows.  

### Build the Spring Boot app

```bash
$ mvn package
```

### Build the Spring Boot app and containerize it with Docker

```bash
$ mvn package dockerfile:build
```

Once you've done this, run `docker images` to see the image.

### Generate and view code coverage

Code coverage is generated through the maven jacoco plugin as part of the verify phase. 

```bash
$ mvn verify
```

After running that, you'll find an HTML code coverage report at target/site/jacoco/index.html.

### Run the app

#### With docker-compose

I would of course recommend running this with docker-compose:

```bash
$ docker-compose up
```

This should create and run two containers: 

- gearbuddy_gearbuddy-mysql_1
- gearbuddy_gearbuddy-mysql_1

#### Manual run
If you for some reason don't want to use docker-compose (can't imagine why) then you can also do this.
 
First, MySql container needs to be running:

```bash
$ docker start gearbuddy-mysql
```

Obviously, that will fail if you don't have that container. In which case, follow the [mysql container setup instructions below](#setup-the-mysql-container). 

Next, run the Spring Boot container:

```bash
$ docker container rm gearbuddy-main # just in case it's already there remove it
$ docker run -p 8080:8080 --name gearbuddy-main -t {{container id}}
```

Add the `-d` flag to that command if you don't want STDOUT to appear.

#### Setup the MySql container

If you don't already have the gearbuddy-mysql container, you need:

```bash
$ docker pull mysql
$ docker run -p 3306:3306 --name gearbuddy-mysql -e MYSQL_ROOT_PASSWORD=password -d mysql:8.0
``` 

Next, don't forget to connect and run src/main/resources/sql/create.sql to create the GearBuddyDb database.


### Deploy (push) container to AWS ECR

Maven has been configured to deploy the container to AWS ECR, provided your machine is set up correctly.

For this to work for you, you need to set up an AWS account, install the AWS cli, create an IAM user with rights to push to ECR, and set up AWS credentials on your machine for it. 

You also need to set an environment variable, `AWS_ACCOUNT_ID`, with the value of your AWS account. 

Next log in to aws ecr:
```
$ eval $(aws ecr get-login --no-include-email --region us-west-2)
```

Look for the message "Login Succeeded" and if that doesn't work fix your AWS IAM user / role and proceed.


Next:

```bash
$ mvn deploy
```

Past simply deploying the container, it's up to you to run it in AWS how you prefer. 

I use ECS/Fargate but you could also (obviously) throw it out onto your own EC2 instance(s). 

### Use the app
Here's a couple cURL commands to get started.

POST new gear:
```bash
$ curl -X POST \
    http://localhost:8080/gear \
    -H 'Content-Type: application/json' \
    -H 'Postman-Token: f79b41f5-d32f-4c8a-a0ea-f3def11c3f02' \
    -H 'cache-control: no-cache' \
    -d '{
  	"type": "Tent",
  	"brand": "Coleman",
  	"model": "Montana",
  	"color": "Green / Gray",
  	"size": "8-Person",
  	"purchasePlace": "B&H PhotoVideo Outdoors",
  	"purchasePrice": null,
  	"purchaseDate": null,
  	"warranty": null
  }'
```

GET all gear:
```bash
$ curl -X GET \
    http://localhost:8080/gear \
    -H 'Postman-Token: 566796fa-8370-4278-b6f3-f177be8e1ce4' \
    -H 'cache-control: no-cache'
```

GET gear by id:
```bash
$ curl -X GET \
      http://localhost:8080/gear/1 \
      -H 'Postman-Token: 566796fa-8370-4278-b6f3-f177be8e1ce4' \
      -H 'cache-control: no-cache'
```

## License
GearBuddy is made available under the MIT License. See the LICENSE file for more details.