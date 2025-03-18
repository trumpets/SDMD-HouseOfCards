# House of Cards Case Study

## System Description
The main task of this assignment is to analyze, design, and implement a simple yet functional Android application. The application you are required to produce is the **“House of Cards Lite”** game.

### Game Rules:
- There are four houses in the game: **Alpha, Bravo, Charlie, and Delta**. Each of them has an associated sum of numbers assigned to it.
- The game generates a random number (from 1 to 10, both inclusive) each round, and the player needs to assign that number to one of the houses.
- After assigning the number:
  - If the sum for that house is **exactly 21**, the game clears the house and awards the player **10 points**.
  - If the sum for that house is **exactly 30**, the game closes that house (the player is no longer capable of assigning values to that house) and awards the player **50 points**.
  - If the sum for that house is **over 30**, the game closes that house (the player is no longer capable of assigning values to that house) and penalizes the player **-20 points**.
- The game ends when **all the houses are closed**.
- The objective is to have fun while achieving a **high score**.  

### Main Functionalities:
- Implement the above game logic so the user can play the game.
- Always display all relevant data to the user (**next number, points, all 4 houses with their current sums**).
- Provide mechanisms for the player to assign the number to a house.
- Develop a mechanism for detecting the game end and show the user a **Game Over** message when that happens.
- **Send the score** to a remote server.
- Provide a screen to **show the top 10 high scores** from the server.

## Lab Instructions
1. Identify the necessary activities and **mock up their UI design** on a piece of paper.
2. Write the **layout XML files** for each activity separately.
3. Write the **Java code** for each activity separately.
4. Declare all activities in the **Android Manifest**. Mark the **MAIN** activity.
5. Write the **transition code** to move through activities in your app.
6. Write the **network code** to send the high score.
7. Write the **network code** to fetch the top 10 scores.
8. Write the **persistence code** to store the top 10 scores **locally in the app** when there is no active Internet connection.
9. Write the **business logic** to store your high score locally if there is no active Internet connection when you achieve it and send it to the server at the first opportunity.

## Server Endpoints

### **GET High Scores**
```
GET https://city-201617.appspot.com/_ah/api/highscores/v1/score
```
#### Response Example:
```json
{
  "items": [
    {
      "id": "258820003",
      "score": 150,
      "websafeKey": "ag1ofmNpdHktMjAxNjE3chALEgdDb250YWN0GIGS9AEM"
    },
    {
      "id": "264810003",
      "score": 100,
      "websafeKey": "ag1ofmNpdHktMjAxNjE3chILEglIaWdoc2NvcmUYk9yifgw"
    }
  ]
}
```

### **POST High Score**
```
POST https://city-201617.appspot.com/_ah/api/highscores/v1/score
```
#### Request Body Example:
```json
{
  "score": 200
}
```
#### Response Example:
```json
{
  "id": "242840003",
  "score": 200,
  "websafeKey": "ag1ofmNpdHktMjAxNjE3chILEglIaWdoc2NvcmUYw-Plcww"
}
```
