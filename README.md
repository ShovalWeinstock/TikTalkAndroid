# TikTalkAndroid
Authors:

  Shoval Weinstok 209540731

  Itamar Shachen Tov 207497769

In order to run the project:

1) Open TikTalkServer -> run inner project webServerAPI:
   
   https://github.com/itamar-st/TikTalkServerUpdatedForEx3
   
2) We used "MariaDB". 
   Execute the following commands in the Package Console Manager: 
 - Add-Migration
 - Update-Database.
         
3) Open TikTalkAndroid and run it using the emulator (suggested device - pixel 2 with android 11):
   
    https://github.com/ShovalWeinstock/TikTalkAndroid
  
4) Register to the applications. suggested password: 12345678Aa

5) Enter the application again, and register again (in order to send messages between multiple users):

6) Add the the first user as a contact of the second user (contact server is 5051 [without "localhost"])

7) send messages to the contact

  * you can get messages by loggin-in to the contact and send a message, or by using the swagger transfer controller 
