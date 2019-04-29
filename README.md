# gearbuddy
Track your outdoor gear purchases. Make reusable gear lists. Know where things are.

## What?

GearBuddy is the application I always wished I had. It records information about gear purchases (like where and when it was purchased) that will be useful for warranty claims or knowing when something needs replaced.

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
GearBuddy is a Spring Boot app running in a Docker container and built by Maven.

### Build

```
$ mvn install
```

### Run

```
$ docker run -p 8080:8080 -t docker.io/scottashipp/gearbuddy-main
```

### View
Open a browser and visit http://localhost:8080