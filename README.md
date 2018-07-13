# JCS-Failover
## Client Site JCS setup if Default JCS Failover is not working
 * JCS has a default Server Failover mechanism but it did not work for me
 * It is a Java utility , if Primary server is ALIVE, it takes value from Primary server.
 * If Primary Server Fails, It sets new properties and looks for the failover server and takes value from it.
 * This is for JCS remote Cache. JCS Server setup is required
 * Required Jar also provided
