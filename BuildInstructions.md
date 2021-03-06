# Build Instructions
Hello, and thank you for the taking the time to check out and or work on my app.

Below are specific instructions to clone and build this project. Enjoy.

This app is developed for Android, Android builds on Java 8 so make sure you have it downloaded. Here's a
link to download [JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
This project won't work on previous versions of the JDK, nor the latest versions ie (JDK 9, 10, nor 7).

Make sure you've configured a [project](https://developers.google.com/identity/sign-in/android/start-integrating) 
with Google Sign API to get their credentials file. It will be needed.

Here's a link to clone the repository from Github:
```
git@github.com:alprael/ReadWithoutMe.git
```
You could clone the repo from the command line, or you could clone it directly in IntelliJ.
Instructions below.
## Building with IntelliJ
This Project was built on IntelliJ. Here's some quick instructions to get started.
1. Open IntelliJ.
2. Make sure you have an active internet connection.
3. Click on 'Check Out from Version Control'.
4. Input the above git link to clone the project.
5. Make sure that these implementations and annotationProcessor are included in the app module of
the build.gradle file:
```
implementation 'android.arch.persistence.room:runtime:1.1.1'
implementation 'com.google.android.gms:play-services-auth:16.0.1'
implementation 'com.android.support:support-media-compat:28.0.0'
implementation 'com.android.support:support-v4:28.0.0'
annotationProcessor "android.arch.persistence.room:compiler:1.1.1"
```
Also make sure that this Java Compile Option are also included:
```
javaCompileOptions {
            annotationProcessorOptions {
                arguments = ["room.schemaLocation": "$projectDir/schemas".toString()]
            }
            dataBinding {
                enabled = true
            }
        }
 ```
 Which should be inserted directly under the TestInstrumentationRunner.
 
 With these instructions, you should be able to successfully build on InelliJ.
 
 Keep in mind that this app runs on a minimum API level of 21, but has been tested and works on API
 levels 24 - 28.
 
 ## Didn't Work?
 1. Make sure JDK 8 is installed.
 2. Make sure java is available.
 3. Make sure you have an active internet connection.
 4. Make sure you've created and downloaded a Google Sign In credentials file from the site above.
 5. Make sure the above implementations and java compile options are included.
 6. Make sure you successfully sync your libraries from the above implementations.
 7. Try invalidating the cache and restarting.
 8. Try rebuilding the project.