Just shows that the behaviour for dynamically inserted script that contains
document.write() statements wipes out the existing DOM, unlike real browsers.

Have a look at src/test/java/cschmidt/HtmlUnitTest.java which demonstrates the
the issue, and the source html file at 
src/test/resources/with-document-write-script.html

Here's what the DOM ends up as in Chrome when loading the sample html file:

![Chrome Screenshot](dom-in-real-browser.png "The DOM as displayed in Chrome")
