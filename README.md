# ScreenshotApplication
A Java application that can take screenshots of whole books

To use it, run the UI. You will see: width, height, top left X position, top left Y position, a button to choose the "next page" option, and a number of times option.
- Width dictates the width of the screenshot (i.e. x direction)
- Height dictates the height of the screenshot (i.e. y direction)
- Top left X position dictates the top left X position of the screenshot (cannot be < 0 or greater than screen width)
- Top left Y position dictates the top left Y position of the screenshot in screen coordinates (i.e. top is 0, bottom is screen height). It cannot be less than 0 or greater than screen height.
- "Next page" option indicates the action to do after a screenshot is taken (e.g. press space bar).
- Number of times indicates how many screenshots is taken (and the Next Page option is pressed n - 1 times)

