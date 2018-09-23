[Trello Board](https://trello.com/b/OWZRAP5j/blockone)

## Remaining Items
 - Write tests for the ViewModels
 - "Busy" spinner while retrieving recent blocks
 - Change list backing store to a SortedList
 - Update list adapter after each retrieved block
 - Implement Paging library to limit network calls
 - Prettify the raw transactions JSON

## Notes
Wait time for the list of recent blocks is bad at the moment - several of the remaining items would help that - notably updating the list adapter after each block is retrieved. The reason that isn't being done now is that the data isn't guaranteed to be in order with the current list type, and so to accomodate, I'm just waiting for the whole list to come back.
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTE4NjExMjMxMTcsOTgzODU5MDc2LDI1Mz
YxNjkzXX0=
-->