# Monopoly group 02


## BUGS

| Bug behaviour  | How to reproduce  | Why it hasn't been fixed    |
|---|---|---|
|Getting out of jail paying for a fine or using a card doesn't work in the client.  | By standing on the GoToJail Tile the currentPlayer will be sent to tile 10 or the jailTile. In the server all of the tests have passed and work. The logic doens't seem off. But this is still a bug. | The getOutOfJail fine and card were one of the last things we did. We couldn't really test this in the client, due to lack of probability of landing on tile 30. Also due to lack of time.|
|Can roll multiple times. | There is no check present that stops the user from rolling a second time. Only when on a purchasable tile. Because Chance, Community Chest, Free Parking ... are not implemented in the server as tiles that have any value, the user can roll again. | We wanted to focus on the must-have's and then focus on the minor bugs. |
| Jailed person is still "jailed" after not being on the Jail tile. | A person that is sent to the Jail tile before landing on a GoToJail tile is jailed. After waiting a turn, this player can roll again (see bug 1). The player still has "jailed: true" as key-value pair in the playerInfo. | We haven't noticed it untill the final days of sprint 5. We didn't have enough time to fix the system |
|There is no controle/check to see if a debtor has already payed the other player. |After standing on someone else's property, this currentPlayer can pay (if the owner clicks on the "collect rent" button) the rent to the owner. But there is no check, unlike the taxPayment one, where if the debtor pays, then they don't have to pay again. | Due to lack of time. We made taxPayment after collectDebt and didn't connect the dots that collectDebt works on the same principle.|

## TOKEN SCHEME
**not implemented**

(DELETE games bridge is implemented, but not connected with the client or with a personal token scheme.)
