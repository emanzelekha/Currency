# Currency
Currency Task
using MVVM structue & modulizer ( data , domin , cache , ui )
using retrofit , glide , MVVM , room caching , liveData ...etc.
used last version of android .
using mvvm sturactue as :
easier to maintain 
easier to testable 
easier to resuable 
using glide lib as it easy to use easy to cache images , easy to download images with svg , Image’s Quality in Details .
using room as google recomnded ,it better than SQLite 
In case of SQLite, There is no compile time verification of raw SQLite queries. But in Room there is SQL validation at compile time.
As your schema changes, you need to update the affected SQL queries manually. Room solves this problem.
You need to use lots of boilerplate code to convert between SQL queries and Java data objects. But, Room maps our database objects to Java Object without boilerplate code.
Room is built to work with LiveData and RxJava for data observation, while SQLite does not. 

