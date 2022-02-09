Classes to modify due to dir changes(replit uses "/" while windows uses "\):
1. Login.java
2. MainController.java
3. OrderControl.java
4. MenuControl.java
5. SalesReport.java
6. StaffControl.java

Please swap the commented lines(near the top of the code) in the above mentioned classes:
Comment this line:
private static String path = System.getProperty("user.dir") + "/data/"; //for replit
Uncomment this line:
 // private static String path = System.getProperty("user.dir") + "\\data\\"; 

If for some reason, the above command do not work, please input the absolute path manually. Remember to add a \\ at the back.

This program may not work well on Lenovo machines for some odd reason.

IDE used: IntelliJ, replit online collab.

Compiler: openJDK v17.0.1 javac