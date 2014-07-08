***** Amex Coding Challenge *******

1) Intro
-> This android app satisfies the Taxes Problem defined in the coding challenge. It utilizes the ActionBar as well as Fragments. Tests are included for the sales tax calculations.

2) Features
-> App consists of MainActivity with ActionBar that allows the user to open up the SettingsActivity. MainActivity is comprised of a ViewPager, and 3 instances of the ScrollListFragment are created,
and added to the view pager to be able to swipe between. A "Calculate Receipt" button is part of the ScrollListFragment, allowing for the calculation of the salesTax and totals, which will be updated
in the view. If the user enters the SettingsActivity, they will be able to modify the "Base Sales Tax", "Import Sales Tax", or "Rounding Increment". If the user selects the "Modify Settings" button
on the SettingsActivity, the tax calculations will then be updated with these new values when the user selects the "Calculate Receipt" button in the ScrollListFragment. Item objects were created to
capture item information, as well as enumerations for ItemType and ItemOrigin.

3) Assumptions
-> None of the calculations took very long due to the number of items. If the calculations took a lot of time (tieing up the UI thread), an AsyncTask could be created and the calculations 
could be moved off the main thread, to another background thread. Once the sales tax calculations are made, would then move back to main thread to update the views.
-> Locked the display in portrait.

4) Tests
-> Tests were created for both the calculation of sales tax and the rounding methods. I created a separate testing project from which the tests can be run.

5) Running the app
-> The app was built with API 19 and set a minimum to run of API 14. I built the app in the eclipse IDE. I ran the app through the IDE on both an emulator as well as a nexus 5 to confirmed it worked. 

6) Future improvements
-> This app could be modified to update the item lists. This would involve tapping on the screen to open a new view to create a new entry that would be updated in the listview after creation.
In addition, tapping on an exisiting item would open a view to edit the item.
-> This app could be modified to allow for different orientations.