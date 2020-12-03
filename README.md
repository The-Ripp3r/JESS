# JESS (Just Everyday Shared Stories)

# To Run (on emulator, tested on macbook)

Steps a bit modified from: [https://developers.google.com/ar/develop/java/emulator] 
## Install the tools and packages 

1. Install Android Studio 3.1 or later
2. In Android Studio, go to Preferences > Appearance and Behavior > System Settings > Android SDK.
3. Select the SDK Platforms tab and check all packages for Android 10.0 (Q) API level 29 Revision 5.
4. Select the SDK Tools tab and add Android Emulator 27.2.9 or later (maybe also Google Play services)
5. Click OK to install of the the selected packages and tools.

## Create a new Android Virtual Device

1. In Android Studio open the AVD Manager by clicking Tools > AVD Manager.
2. Click Create Virtual Device, at the bottom of the AVD Manager dialog.
3. Select the Pixel 2 device, select next
4. Select API 29 (Q) x86, Android 11.0 (Google Play), select next
5. Click Show Advanced Settings and check that Camera Back is VirtualScene
6. Finish to create the new AVD

## Build and run

1. Download the latest Google_Play_Services_for_AR_1.21.0_x86_for_emulator.apk from [https://github.com/google-ar/arcore-android-sdk/releases]
2. Start the new device from AVD Manager. When the emulator is started up, literally drag the file onto the device, which installs the google play services (I think for physical devices, you need to install Google Play Services for AR through the Google Play Store).
3. Open the android project and press the play button with the new device selected.

