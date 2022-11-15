Due to what I write below, I'm not sure if my bidder is good (maybe it is even bad), but in this readme I will try to explain why I made it the way I did it.

Having received the task, I understood that this was an auction, because I once engaged in the implementation of the auction of the second price.
However, the second price auction bid strategy and the first price auction bid strategy are not applicable to this auction.

After researching the issue, I found out that this is an all pay auction. Moreover, it is interesting that this is series of all pay auctions, and not a single one.
I tried to find out if there is a Nash equilibrium for this case and, to be honest, I got confused by the number of scientific papers written about such auctions. Since I had not studied mathematics for a long time, it was difficult to understand some of what was written, and I did not have enough time to brush up on mathematical analysis.

Why did I do what I did:

1) Let's talk about interface interpretation.

It wasn't very obvious to me what exactly the placeBid method should do. I interpreted the word show not as an output to the screen (because I don't see the point in this), but as a method by which bidders receive information about the completed stage of the auction.

2) Let's talk about value.

When participating in an auction, it is important to know the value for both players. In this case, I considered that not all rounds are valuable for us, but only the number sufficient for us to win.
Therefore, the value is distributed evenly between the number of rounds required to win. Since my opponent is trying to achieve the same thing as me, I considered his values and strategy should be similar to mine (assuming he needs to win the same number of rounds as me).

3) Let's talk about the implementation of the placeBid method.

It is important to say that I suggested that we need to win at all costs, but if we lose, then we need to leave as much money as possible (for future auctions, for example). Here I have identified several situations in which further loss of money is useless.
First: if one of the participants has already achieved victory, then it is pointless to place not 0 bids, because it will not bring victory to the second.
Second: if the opponent's advantage in money at the moment is so great that until the end of the game or until he gets the required number of points he can place bid "all my money + 1", then I will not lose money and I will place bid 0.
If I have such an advantage in money that I can place bid "all his money + 1" until I get the required number of points, then I will place bid "all his money + 1" until I get them.
There is a little trick I put in here that will hopefully work against some strategies. The trick is this: if the opponent needs to win all the remaining rounds, then in order to stay in the game, he may want place bid "all my money + 1". I will place bid 0 at this point and the next round I will probably win.
If there is only 1 round left, my opponent and I need to win one each and our budgets are equal, then I will place bid equal to all my money so that neither of us is the winner.
If none of the conditions fit, then I take place bid randomly from the numerical range from 90% to 110% of the value of the round.

I am attaching a list of what I read while doing the assignment.
https://digitalcommons.chapman.edu/cgi/viewcontent.cgi?referer=&httpsredir=1&article=1130&context=economics_articles
https://www.researchgate.net/publication/5013770_Equilibrium_behavior_in_all-pay_auction_with_complete_information
https://www.cs.princeton.edu/courses/archive/spr06/cos444/papers/GoereeApAp.pdf
https://rua.ua.es/dspace/bitstream/10045/2300/1/WP_All-Pay%20Auction_Alcalde.pdf