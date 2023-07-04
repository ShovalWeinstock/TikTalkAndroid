# TikTalkAndroid
Authors:

  Shoval Weinstok
  
  Itamar Shachen Tov
  
<img width="183" alt="androidLogin" src="https://user-images.githubusercontent.com/92527489/198890055-f2559642-4cb6-441a-a161-61cdc5238388.png">

<img width="185" alt="registrationAndroid" src="https://user-images.githubusercontent.com/92527489/198890067-928b4e1a-ce88-4346-86b4-791945f6dac7.png">

<img width="184" alt="contacts" src="https://user-images.githubusercontent.com/92527489/198890072-babc3624-649c-475d-b06c-bcf9e20bc8cc.png">

<img width="187" alt="chatAndroid" src="https://user-images.githubusercontent.com/92527489/198890076-c397f4d8-8f01-4655-afec-65b778284c80.png">


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
