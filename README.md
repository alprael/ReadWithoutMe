# Read Without Me
Read Without Me is a nice little Android reading app with the basic function of keeping track of
your children's reading behavior when you're not looking, and with a complex function of keeping track
of people's reading behavior and reading comprehension. Allow me to start off with the reasoning 
behind why I created this app before I explain what it does.

When I started android development I came in with the idea that I wanted to develop
apps that make people's lives just a little bit easier. As you'll read from my User Stories down 
below, you'll see what my true thought process was for the creation of this app. I truly believe 
that this app in it's current form, embodies that philosophy.

Read Without Me is meant for parents or people in a supervisory roles to keep track of one's reading
habits and reading comprehension indirectly. There are a few books on the device ready for users to
engage in. Each book has a timer, which I believe is an important function to tracking a person 
reading level. Of course this timer can be easily manipulated, but still an important function. 
Along with keeping track of one's reading, it's equally important to know if that one retained the 
information. Each book has a quiz associated to it. Either it has a quiz that directly asks 
questions to the contents of the book, or a default quiz is given with more general questions.
The user info panel gives per device information, since it wouldn't be as easy to track ones 
reading habits if they signed in under a different account and didn't track that information.
With all that in mind, you can understand that this app just makes it a bit easier to know if your 
child or whoever you're supervising is doing the reading you assign them.

By the way, I call them books, but books aren't the only type of media that can be presented in
this app. There could also be magazines, articles, textbooks, or even short stories. All of which 
are equally important.

Here's a few aspects of the app I currently would love to have changed in order of most important:
1. In the info section of the app, there's a list view to show which users read which book and what
times they got on those books. I would like the app to actually show the name of the user instead of 
the user's ID for future implementations. 
2. I would also like it if once you were at the home screen that pressing the back button kicked you
out of the app completely. 
3. Also, the timer in the uer info screen be formatted correctly to reflect both minutes and seconds
instead of just seconds. 
4. Lastly, I would like it if the app, design wise, fit more closely to Google's Material Design 
standards.

The minimum API level for this app is 21, however, the app has been tested to work on APIs 24 - 28.
The main language is English, and the current required orientation is portrait mode. In order to
successfully sign into the app, a working internet connection is required.

Here are the current implementations needed to work for this app to function.
```
implementation 'android.arch.persistence.room:runtime:1.1.1'
implementation 'com.google.android.gms:play-services-auth:16.0.1'
implementation 'com.android.support:support-media-compat:28.0.0'
implementation 'com.android.support:support-v4:28.0.0'
```

The last 3 implementations above are mostly for the use of Google Sign In. Google Sign In is the
only API this app consumes.

## User Stories
As a mother I want to be able to keep track of my children's reading habits as I assign while I cook dinner.


## Wire Frames
![](ReadWithoutMeWireFrame.png "Wire Frames")

## Physical Entity Relationship Diagram
![](ReadWithoutMeERD.png "Entity Relationship Diagram")
