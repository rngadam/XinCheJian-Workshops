# Workshop #4: communicating with hardware using Bluetooth

## Using Bluetooth to communicate with hardware

 * Bind your phone to RobotBase (default pin: 1234)
 * Change AndroidManifest.xml to add "Uses permissions" for android.permission.BLUETOOTH android.permission.BLUETOOTH_ADMIN
 * Communication
  * Find out bluetooth id you want to connect to
  * Create low-level connection class (com.xinchejian.android.Bluetooth)
  * Create Robot control interface using your connection class
 * Add Bluetooth and Robot control as fields of your Activity, instantiated in your onCreate

## Integrate in a UI
 * Modify res/layout/main.xml
 * add toggle button
 * add findViewById in onCreate to create field
 * add android.os.Handler
 * add Runnable to be called by the handler at a delayed interval
    * if bluetooth is not connected, connect
    * update all toggle status
    * Handler.postDelayed on same object for nnn milliseconds
 * You should now be able to start the app and see if application is available or now
 * Do the same for Bluetooth.isConnected(): add a toogle and set its status based on the boolean value returned

## Adding controls
 * Add two variables with the values you want in your activity
 * Add the appropriate widget in the layout to control those values
 * set initial values (setProgress) of widgets and defaults (setMax)
 * Add Listeners to update your values based on the widget interactions
    upDownSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
 * Handle events changes 
 * Send the values from inside the timer using sendCommandString
