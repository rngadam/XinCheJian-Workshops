# Before the workshop

## Setting up an the development environment

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

## Details: Checking the workshop projects and documentation

in a shell (or git bash shell on Windows)

    mkdir XinCheJian-Workshops
    cd XinCheJian-Workshops
    git init
    git pull git@github.com:xinchejian/XinCheJian-Workshops.git
    mkdir wiki
    cd wiki
    git pull git@github.com:xinchejian/XinCheJian-Workshops.wiki.git

## Verifying the setup

1. Creating the Hello World project using the wizard
1. Running and uploading to the phone
1. Debugging
1. Commit a change

## Details: Creating an "Hello World!" project

* Create new project 
 * File > New > Android Project 
 * Set the Project name to whatever you'd like: ex: "RickyRobot"
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