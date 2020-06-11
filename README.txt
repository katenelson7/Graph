Project: Graph 


Handin: I intend for this to be my final hand-in.


Design Choices:
The decorations that I used were one to keep track of the previous node, one to keep track of the cost to get to the node from the source,
and one to keep track of whether the node had already been visited, and one to keep  track of the vertex's entry.
I kept track of the cost so I could compare it to the edge length that connected v and u.
I kept track of the node's previous so I could add that edge to the MST.
I kept track of whether the node has been visited so I could know if v's opposite vertex () was still in the PQ or not.
Lastly, I kept track of v's entry so I could pass it into the PQ.replaceKey method.
 
In my MyPrimJarnik class I wrote a getIncidentEdges() helper method that returns an arraylist of incident edges for v such that, for each incident edge, 
the opposite vertex u is still in the PQ.

In myMyDecorator class I wrote a helper method r so thaeplaceKey() so that I could easily replace the keys for my decorations.

In my MyPageRank i wrote two separate helper methods, getIncomingEdges() and getOutgoingEdges(), which return an arraylist of 
incoming and outgoing edges respectively. I did this because the incomingEdges and outgoingEdges methods defined in Adjacency
matrix both return iterators, not arraylists, and I needed to be able to index into the data structure easily and in a way 
that maintained its order. I also wrote a helper method called hasConverged() to check whether the graph has converged, since if it has
then the main do while loop in PageRank should stop. For PageRank, I handle sinks with the handleSinks() method where I 
connect the sinks to every single other node in the graph. Lastly, I chose to use Arrays to hold my ranks so that I could index in to them 
before I initialized any values.


Known Bugs: None that I know of.


Testing: I tested my code by writing and running numerous tests in which each test tested a very specific part of my code. Therefore,
I had a large number of total test, but I preferred the incremental testing rather than testing more functionality at once. 


Conceptual question:
I would ensure that the page has the lowest rank of any vertex by removing all of its incoming edges, so that the bad page wouldn't
have any pages pointing to it. Therefore, it would only receive the (1-d)/N portion of the page rank from each other page. But it would be 
giving page rank to all the pages it was already pointing to, along with (1-d)/n to all of the other nodes. Therefore, the bad 
page would be assured to have the lowest rank. 


Extra Credit:
I completed the extra credit portion of the project. I wasn't sure if we were supposed to include it in the turn in, so the method removeBlacklist() is 
left commented out in my calcPageRank().


