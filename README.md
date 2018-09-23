[Trello Board](https://trello.com/b/OWZRAP5j/blockone)

## Remaining Items
 - Write tests for the ViewModels
 - "Busy" spinner while retrieving recent blocks
 - Change list backing store to a SortedList
 - Update list adapter after each retrieved block
 - Implement Paging library to limit network calls
 - Prettify the raw transactions JSON
 - Use Room Persistence library for local cache

## Performance
Wait time for the list of recent blocks is bad at the moment - several of the remaining items would help that - notably updating the list adapter after each block is retrieved. The reason that isn't being done now is that the data isn't guaranteed to be in order with the current list type, and so to accommodate that, I'm just waiting for the whole list to come back before updating the list.

Another improvement would be had with the use of paging - only make calls for those items that are visible on the page, and maybe a couple more, then as you begin scrolling, keep pulling until you've hit the upper limit requested.
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTQ5MjAwMDY5OSwxODA5NTQzODQ0LDk4Mz
g1OTA3NiwyNTM2MTY5M119
-->