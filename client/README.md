# Monopoly web project group 02

## Before you start
Search for the string XX and **replace it with your group number**.

## Parent group
https://git.ti.howest.be/TI/2021-2022/s2/programming-project/projects/group-02

## Remote urls
### Your own project
* https://project-i.ti.howest.be/monopoly-02/
* https://project-i.ti.howest.be/monopoly-02/api/

### Provided API
* https://project-i.ti.howest.be/monopoly-api-spec/


## INSTRUCTIONS
1. **Clear localStorage**
There are some bugs that will be avoided by clearing the localStorage. For example: having the same key variable as in our game (playerColor, gameId, tiles ...).
2. **Read the "##BUGS" chapter**
All of the known bugs are listed in the "##BUGS" chapter. This knowledge could help you understand how to play the most enjoyable way.

## FUNCTIONALITY TABLE

|PRIORITY  |ENDPOINT                                                                                              |Client                | Client          |Server                       | Server                      |
|--------|--------------------------------------------------------------------------------------------------------|----------------------|-----------------|-----------------------------|-----------------------------|
|        |                                                                                                        |Visualize ( HTML/CSS)|Consume API (JS)|Process request (API-Bridge)|Implement Game Rules (logic)|
|        |**General Game and API Info**                                                                           |100%                  |YES/NO           |YES/NO                       |100%                         |
|        |GET /                                                                                                   |100%                  |YES              |YES                          |100%                         |
|MUSTHAVE|GET /tiles                                                                                              |100%                      |100%                 |YES                             |100%                             |
|MUSTHAVE|GET /tiles /{tileId}                                                                                    |100%                      |100%                 |YES                             | 100%                            |
|        |GET /chance                                                                                             |                      |                 |YES                             |                             |
|        |GET /community-chest                                                                                    |                      |                 |YES                             |                             |
|        |                                                                                                        |                      |                 |                             |                             |
|        |**Managing Games**                                                                                      |                      |                 |                             |                             |
|        |DELETE /games                                                                                           |                      |                 |YES                             | 90% (no personal token)                            |
|MUSTHAVE|GET /games                                                                                              |100%                      |100%                 |YES                             |  100%                           |
|        |Additional requirement: with filters                                                                    |                      |                 |                             |                             |
|MUSTHAVE|POST /games                                                                                             |100%                      |100%                 |YES                             | 100%                            |
|MUSTHAVE|POST /games /{gameId} /players                                                                          |100%                      |100%                 |YES                             | 100%                            |
|        |                                                                                                        |                      |                 |                             |                             |
|        |Info                                                                                                    |                      |                 |                             |                             |
|        |GET /games /dummy                                                                                       |                      |                 |                             |                             |
|MUSTHAVE|GET /games /{gameId}                                                                                    |100%                      |100%                 |YES                             |100%                             |
|        |                                                                                                        |                      |                 |                             |                             |
|        |**Turn Management**                                                                                     |                      |                 |                             |                             |
|MUSTHAVE|POST /games /{gameId} /players /{playerName} /dice                                                      |100%                      |95%                 |YES                             | 95%                            |
|        |With jail                                                                                               |                      |                 |YES                             |  100%                           |
|MUSTHAVE|POST /games /{gameId} /players /{playerName} /bankruptcy                                                |100%                      |100%                 | YES                            | 100%                            |
|        |Decent distribution of assets                                                                           |                      |                 |                             |                             |
|        |                                                                                                        |                      |                 |                             |                             |
|        |**Tax Management**                                                                                      |                      |                 |                             |                             |
|        |POST /games /{gameId} /players /{playerName} /tax /estimate                                             |100%                      |100%                 |YES                             |100%                             |
|        |POST /games /{gameId} /players /{playerName} /tax /compute                                              |                      |                 |                             |                             |
|        |                                                                                                        |                      |                 |                             |                             |
|        |**Buying property**                                                                                        |                      |                 |                             |                             |
|MUSTHAVE|POST /games /{gameId} /players /{playerName} /properties /{propertyName}                                |100%                      |95%                 |YES                             |  100%                           |
|MUSTHAVE|DELETE /games /{gameId} /players /{playerName} /properties /{propertyName}                              |100%                      |95%                 |YES                             | 100%                            |
|        |With 1 bank auction                                                                                     |                      |                 |                             |                             |
|        |                                                                                                        |                      |                 |                             |                             |
|        |**Improving property**                                                                                      |                      |                 |                             |                             |
|        |POST /games /{gameId} /players /{playerName} /properties /{propertyName} /houses                        |                      |                 |                             |                             |
|        |DELETE /games /{gameId} /players /{playerName} /properties /{propertyName} /houses                      |                      |                 |                             |                             |
|        |POST /games /{gameId} /players /{playerName} /properties /{propertyName} /hotel                         |                      |                 |                             |                             |
|        |DELETE /games /{gameId} /players /{playerName} /properties /{propertyName} /hotel                       |                      |                 |                             |                             |
|        |                                                                                                        |                      |                 |                             |                             |
|        |**Mortgage**                                                                                                |                      |                 |                             |                             |
|        |POST /games /{gameId} /players /{playerName} /properties /{propertyName} /mortgage                      |                      |                 |                             |                             |
|        |DELETE /games /{gameId} /players /{playerName} /properties /{propertyName} /mortgage|                      |                 |                             |                             |
|        |                                                                                                        |                      |                 |                             |                             |
|        |**Interaction with another player**                                                                         |                      |                 |                             |                             |
|MUSTHAVE|DELETE /games /{gameId} /players /{playerName} /properties /{propertyName} /visitors /{debtorName} /rent|100%                      |90%                 |YES                             | 95%                            |
|        |With potential debt    |                      |                 |                             |                             |
|        |                                                                                                        |                      |                 |                             |                             |
|        |**Prison**                                                                                                  |                      |                 |                             |                             |
|        |POST /games /{gameId} /prison /{playerName} /fine                                                       |95%                      |20%                 |YES                             |  80%                           |
|        |POST /games /{gameId} /prison /{playerName} /free  |95%                      |                 20%|YES                             |80%                             |YES
|        |                                                                                                        |                      |                 |                             |                           |
|        |**Auctions**                                                                                                |                      |                 |                             |                             |
|        |GET /games /{gameId} /bank /auctions                                                                    |                      |                 |                             |                             |
|        |POST /games /{gameId} /bank /auctions /{propertyName} /bid                                              |                      |                 |                             |                             |


## BUGS
| Bug behaviour  | How to reproduce  | Why it hasn't been fixed    |
|---|---|---|
|Removal and duplication of "go-back" button on pawn-page and start-page.  |picking a pawn on the pawn-page, pressing confirm, clicking on the "go-back" button on the top-left. Go-back button is duplicated. Or continuing after chosing the amount of players and then pressing the "go-back" button. This results in the removal of the button on the start-page| We didn't really focus on these little details on the last day of the project. We wanted to complete the must-have's first and have a funcional game.|
|Showing incomplete version of game.|Refreshing after picking the game to join. The game will give you a token after joining. will give errors, because the game can't find the other players.     Also after joining a game with the right amount of players, you'll need to refresh. You can also get this by going to the project link with a localStorage that shares the same key name as ours like: playerColor, gameId ...| We wanted to focus on the must-have's and then focus on the minor bugs. |
|Buying a previous tile.   |Rolling the dice, buying a property, waiting until it's your turn without refreshing, rolling the dice again, and buying again. Eventhough your position has moved, the bord says otherwise. You'll see two positions of your color. You can also see the two same property in your assets list, but only one bought property.| We haven't noticed it untill the final days of sprint 5. We didn't have enough time to fix the polling system, so we always had to refresh after rolling so the property is the right one you want to buy. |
|You can roll multiple times in one turn.   | After rolling and landing on a non-property (or a property that is already bought) that doesn't take your turn like: chance, community chest, free parking (that hasn't been implemented in the server) you can roll a second time untill you land on a property tile.  | Due to lack of time. |
|Buying a property will cause it to try and buy it multiple times.   | Buying a property works, but due to wrong implementations of polling, the client tries to fetch mutiple times which results in 409 (conflict) http errors. | Due to wrong implemntations of polling and lack of time. |
Click multiple times on the "pass" button| Clicking on the "pass" button after not wanting to buying a property will sometimes cause it to ask you the same question again and not remove the buttons. Console will also show a lot of responses from the server. This depends on how much property is bought. More property = more fetches. | Polling issue, due to lack of time.   |
|Not refreshing after rolling the dice. | After rolling the dice the currentPlayer must refresh the page. This insures that the currentPlayer is on the right tile. If this doesn't happen, then you'll buy the same property twice (as already mentioned). But you can also risk losing all of your money and being bankrupt. | Polling issue, due to lack of time.  |
|Joining in a full game or a game that is already finished. |Joining in a full game or a game that is already finished. If you have the same name as one of the previous players, then the game will think that you just came back and show both losing screen and winning screen at the same time. If your name differs from all of the players, then fetching will not work, and you won't even be able to see yourself in the players list. | Due to lack of time. We wanted to finish as much as possible of the must-have's and a few of the should-have's. |
| Getting out of jail.  | After losing a turn due to being sent to jail you can chose between: paying or using a card. Both should work theoretically, because they don't show any errors in the console, but also no "notJailed" response. You can just simply roll the dice and escape jail, but you'll still have the "jailed: true" mark in your players information. You will also still see the pay-fine and user-card buttons under the pay or pass buttons after standing on a property tile. These buttons don't do anything. | Jailing was hard to make sure it works on the client, because you had to land on 30. Due to lack of time.|
|Asking for rent. | If a player stand on your property, you can recieve for rent. This will return a lot of responces, due to polling. The server also doesn't have a check to verify if the debtor has already paid. The taxTiles have this imlementation. So if a player stands on your property, you can simply spam the "collect rent" button and win the game, because the other player will be bankrupt.| This could've been the same check that has been implemented in the taxTiles methods in the server. Due to lack of time.
|Losing screen will give an error that it can't find the article inside class currentTile| After losing in a game of three or four players, you should still be able to see your board. This isn't the case. There is an article missing inside the currentTile. Because of this, the currentTile cannot be set in "bankrupt-mode" (which is just big red cross over the currentTile and the inventory tile).| We didn't fix this due to lack of time. We needed to focus on the must-have's.

## Instructions for local CI testing
You can **run** the validator and Sonar with CSS and JS rules **locally.** There is no need to push to the server to check if you are compliant with our rules. In the interest of sparing the server, please result to local testing as often as possible.

If everyone will push to test, the remote will not last.

Please consult the Sonar guide at [https://git.ti.howest.be/TI/2021-2022/s2/programming-project/documentation/monopoly-documentation/-/blob/main/sonar-guide/Sonar%20guide.md](https://git.ti.howest.be/TI/2021-2022/s2/programming-project/documentation/monopoly-documentation/-/blob/main/sonar-guide/Sonar%20guide.md)

## Client
In order to help you along with planning, we've provided a client roadmap
[https://git.ti.howest.be/TI/2021-2022/s2/programming-project/documentation/monopoly-documentation/-/blob/main/roadmaps/client-roadmap.md](https://git.ti.howest.be/TI/2021-2022/s2/programming-project/documentation/monopoly-documentation/-/blob/main/roadmaps/client-roadmap.md)

## File structure
All files should be places in the `src` directory.

**Do not** change the file structure of the folders outside of that directory. Within, you may do as you please.


## Default files

### CSS
The `reset.css` has aleady been supplied, but it's up to you and your team to add the rest of the styles. Please feel free to split those up in multiple files. We'll handle efficient delivery for products in production in later semesters.

### JavaScript
A demonstration for connecting with the API has already been set up. We urge you to separate your JS files as **atomically as possible**. Add folders as you please.

## Extra tips for CSS Grid
In case you get stuck or confused
https://learncssgrid.com/

And for your convenience, yet use with caution
https://grid.layoutit.com/ 
