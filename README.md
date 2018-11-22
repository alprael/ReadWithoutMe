# Read Without Me
Read Without Me is a nice little reading app with the basic function of keeping track
of people's reading behavior and reading comprehension.
Allow me to start off with the reasoning behind why I created this
app before I explain what it does.

When I started android development I came in with the idea that I wanted to develop
apps that make people's lives just a little bit easier. I truly believe that this app,
even in it's current form, embodies that philosophy.

Read Without Me implements Google Sign In. Once you successfully sign in, you're instantly a new 
user and ready to continue one to the rest of the appThe app itself holds a number of books already
in the app. Each book may have an associated quiz. What I mean is that not every book has a quiz, 
instead, a default quiz is given. Some books do have associated quizzes that ask more direct 
questions about the contents of the book itself.

Choose a book, and you continue on to reading it. On the book reading screen there is a timer with 
a start, pause and reset button. The timer, I believe, is a crucial tracking method in understanding
somebody's reading comprehension. Of course, this timer could be easily manipulated, and even trying
to implement methods to prevent such manipulation wouldn't solve this problem. Still a crucial 
functionality to this app.

After reading the book, the user makes their way to the quiz. This quiz usually contains specific
questions to the contents of the book. Right now those answers aren't meant to be manually inputed
in the app, instead answers are to be given to those who are supervising that user.
Future implementations of the app might allow for inputed answers for various reasons, but at the 
moment, not necessary. Of course, there could be books that don't have associated quizzes, where 
instead the person will be directed to a default quiz which asks generalized questions. There's a 
done button when the user is done taking the quiz.

At any moment of the app, instead of the sign in screen, the user can go to the user info screen.
This screen provides information on the user such as display name and email. Future implementations
will allow the user to see what books they've read, and how long it took them to read that book.
Note that it is possible for the same user to have multiples of the same book read with 
different times.