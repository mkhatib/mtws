# Introduction #
We Are going to use SVN with Google Code to collaborate in this project.
## The Why ##
  * SVN can save us a lot of headache. For once, we can work all together on different (or the same) parts of the code and still we are in the safe side! SVN will handle all the conflict for us.
  * We Can Revert to any version we saw that it was working better. So you don't have to make tons of copies on your system. SVN Handles this.
  * Staying Up To Date: SVN Provide the best way so all of us stay up-to-date and we don't have to keep sending emails with what we have changed and how...etc, It's just one command.
  * Google Code uses SVN (Subversion) for Version Controlling System.

## Installation ##
  1. If you don't have already Subversion you need to get it and install it. To Check whether do you have it or not, just open a Terminal (command prompt) and type 'svn' and hit enter. If You got an error message, then you don't have svn and you need to install it. Else if you got svn then skip the following steps and go further.
  1. To install SVN on windows xp, download this file [Here](http://www.sliksvn.com/pub/Slik-Subversion-1.5.4-win32.msi). Install it and then test step #1 again.

## The How ##

### The Checkout ###
  * First, Get (checkout) a copy from the code (You will do this once in your computer).
    1. Go to "Source" tab above
    1. Copy the link that starts with (https://) (Make sure you're logged in your Google Account)
    1. Paste it in the Command Line and hit enter.
    1. You will be promoted sort of the source is unknown or something like that, Just type 'p' and hit enter to accept it permanently.
    1. You will then be promoted to enter your password, Don't mix it up, it's not your email password, you can get the password from 'Source' Tab.
    1. Hit the link that says "When prompted, enter your generated googlecode.com password."
    1. Copy your generated password and paste it in Command line.

Now you've got your copy on your computer. (You will find inside there one single file (I've added) namely, ReadMe.txt.


### The Add ###
  * When creating new files, you have to tell SVN that you've actually added a file so it can keep track of it.
> `svn add fileName`

### The update ###
  * REMEMBER, before you start changing, and after ending from changing on the code, do an Update command.
> > `svn update`

### The Commit ###
  * If After you ran update everything is good then continue. Else jump to **The Conflict**
  * Now when you're done adding files, and modifying them, you can send (commit) your changes to the main copy on Google Code.

> `svn commit -m 'A message briefly state what changes you made on the files'`

### The Conflict ###
  * Conflicts happens when two persons changes things that could not be distinguished! For example, Suppose we have the following code:
> > `for(i=0;i<=10;i++)`
and two people are working on the same line, here are each person changes:


> P1:   `for(counter=10; counter>0; counter--)`

> P2:   `while(i<10)`

These two lines will cause **Conflict**.

PS. Most of the time this won't happen. However, I will write how to resolve the conflict later.


That's All For Now...