Welcome to the XinCheJian Androids & Robots wiki!

# Why use Android for robotics?

* Built-in hardware is relatively cheap and powerful (GPS, accelerometers, Bluetooth, camera)
* Built-in processing power is sufficient to do high-level processing (vision)
* Easy-to-use development environment
* Compact size, built-in battery
 
# What you'll need to develop Android apps

* A MacOS X (Intel), Linux (x86 or x86_64) or Windows laptop
* An Android phone (2.1-update1 +) with the USB cable (Bluetooth, camera, accelerometers functions)
* The Android SDK for your platform
* Eclipse (3.6)
* The Eclipse extension for Android
* OEM USB (development) driver for your Android phone
* Git client and Eclipse extension for Git
* An account on github.com and access to [[https://github.com/xinchejian/XinCheJian-Workshops]]

# Setting up an the development environment

* Create your account on github
 * Create account
 * Send account name to be added to the organization
 * Generate a Private and Public Key
 * Set your github profile to use that
* Install git
 * Use git to checkout XinCheJian-Workshop (git@github.com:xinchejian/XinCheJian-Workshops.git)
* Install Android SDK Release 11 (decompress in your home folder)
* Install and run Eclipse 3.6.2
* Install Eclipse extensions (using Help > Install new software)
 * Install ADT 10.0.1 into eclipse: https://dl-ssl.google.com/android/eclipse/
 * Install eGit into eclipse: http://download.eclipse.org/egit/updates
* Set Eclipse ADT preference to point to your SDK
 * Window > Preferences > Android > SDK location
 * Apply > you should see a list of "Target name" ranging from Android 1.5 to Android 3.0
* Set workspace to new folder in XinCheJian-Workshops/Android/Participants/<your github username>

# "Hello World!"

* Create new project 
 * File > New > Android Project 
 * Set the Project name to whatever you'd like: "RickyRobot"
 * Target: Android 2.1-update1 for now
 * Application name to whatever you'd like: "Ricky Robot"
 * Package name: com.xinchejian.android.<your github username>.robot
 * Create Activity: RobotUserInterface
 * Next
 * Skip creating a test project for now
* Running the application
 * Connect your phone to USB
 * On the phone, Settings > Applications > Development > Check USB debugging
 * Eclipse > Run > Run As... > Eclipse Application

# The Basics of an Android application

* Source and packages
* AndroidManifest.xml: permissions and intents
* android.jar: rich library
* R.java: generated resources references
* Activity: user interface
* res > layout > main.xml

# Source control

* Share your project
 * Right-click project  > Team > Share > Select type git > Next > Select existing repository > Finish
* Commit your base project 
 * Right-click project > Commit > select all
 * Deselect manually files in bin/
 * Commit

# Android communication to physical hardware

* Write Arduino program to control actuators or read sensors
 * Test actuator or sensor
 * Create serial interface
 * Test serial interface
 * Add Bluetooth hardware module

* Using Bluetooth to communicate with hardware
 * Bind your phone to RobotBase (default pin: 1234)
 * Change AndroidManifest.xml to add "Uses permissions" for android.permission.BLUETOOTH android.permission.BLUETOOTH_ADMIN
 * Communication
  * Find out bluetooth id you want to connect to
  * Create low-level connection class (com.xinchejian.android.Bluetooth)
  * Create Robot control interface using your connection class
 * Add Bluetooth and Robot control as fields of your Activity, instantiated in your onCreate

* Integrate in a UI
 * Modify res/layout/main.xml
 * add toggle button
 * add findViewById in onCreate to create field
 * add android.os.Handler
 * add Runnable with following code:

			if(!bluetooth.isConnected())
				bluetooth.connect();
			bluetoothConnectionStatusToggle.setChecked(bluetooth.checkBluetoothAvailable());

	                handlerTimer.postDelayed(this, 300);  

 * You should now be able to start the app and see if application is available or now
 * Do the same for Bluetooth.isConnected(): add a toogle and set its status based on the boolean value returned

* Adding controls
 * Add two variables with the values you want in your activity
 * Add the appropriate widget in the layout to control those values
 * set initial values of widgets
 * Add Listeners to update your values based on the widget interactions
    upDownSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
 * Handle events changes 

		public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
				if (fromUser) {
					if (progress < RobotControl.UPDOWN_MIN_POS) {
						updownValue = RobotControl.UPDOWN_MIN_POS;
					} else {
						updownValue = progress;
					}
				}
		}

 * Send the values from inside the timer using sendCommandString

# Sensing the environment

# Controlling actuators

Archive:

[[Workshop-#1:-The-Basics-for-Beginners]]