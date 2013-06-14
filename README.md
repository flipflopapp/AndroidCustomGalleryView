Android CoverFlow Widget
===
Introduction
-------------
This is a vertical and semicircular version of the CoverFlow Widget made my Neil Davies.
In this project we made changes in the transformimagebitmap function which is responsible for transformation of images in different orientations.

How to use
-----------
In the your xml file 
1. Add xmlns:custom="http://schemas.android.com/apk/res/com.example.coverflow" 
2. Inside the tag of coverflow, according to ur requirement set the value of the attribute custom-orientation to vertical or semicircle.

If you use it programmatically then 
Initialise the Arrangement interface object with the appropriate class constructor inside the CoverFlow constructor

How to extend
-----------------------
1. Extend the interface Arrangement.
2. Modify the transformImageBitmap function according to your orientation.
3. Set a calculated delta in the setDelta function for the new style for proper onDown event handling.

References
----------
The original source of Neil Davies can be found at http://www.inter-fuser.com/2010/02/android-coverflow-widget-v2.html.
