# Checkout application

### Assumption 
sku only contains alphabet characters.


### Design
Every product is represented by a class such that the parent is a abstract class `Product`. We can add more product specific feature in the child class later. A factory is created for generating items by sku.
A rule set is represented by a class and implements an interface `Rules` as definting the rule set behaviour.

Users can change the content of `OpeningDaySpecialRules` ruleset easily. They can change varabile `APPLE_TV_DEAL_X` and disable some rule by commenting out the process.
Alternative, they can create a new rules set and inject the new ruleset in `Storescanner`.

The approach in `OpeningDaySpecialRules` is grouping items by product and create a map to store the result. And it run checking on the map based on the business rules. It will sum up any remaining items in the map at the end. The overall time complexity is O(n) and the most of the time consumed in the grouping. 


### Requirement 
1. gradle 4.8+
2. java 1.8+
3. Windows/Linux/Mac


### Set up
1. Please download the source code from git hub
2. `cd [project root]` and run `./gradlew clean build`
    The unit testing will be executed.
3. `rules-1.0.SNAPSHOTS.jar` will be created in `[project root]/build/libs`.
4. run `java -jar build/libs/rules-1.0-SNAPSHOT.jar ` in project root.

5. You will see following in the terminal
```
SKUs Scanned:


```
5a. input the sku
```
SKUs Scanned:
ipd

```
5b. result
```
SKUs Scanned: 
ipd 
Total expected: $549.99

```

### Extra
If users provide invalid sku, the system will skip the item and print `Invalid sku : `.


### Testing
You can run the test by
```
cd [project root]
./gradlew test
```
