# cordova-android-gestures
A PhoneGap/Cordova plugin that allows to handle gestures on Android device. Especially useful if your project uses dynamic iframes.

## Recognizable gestures
 - leftSwipe
 - rightSwipe
 - upSwipe
 - downSwipe
 - longTap
 - singleTap
 
## Supported platforms
 - Android
 
## Where to use.
Wherever you need to handle gestures on Android devices.

## Installation
    phonegap plugin add https://github.com/megaduck/cordova-android-gestures.git
    cordova plugin add https://github.com/megaduck/cordova-android-gestures.git

## Usage
    MegaduckGestures.swiper(function(direction){
        switch (direction) {
            case 'rightSwipe': {
                //do some goodness...
                alert('Right swipe detected!');
                break;
            }
            case 'leftSwipe': {
                //do some goodness...
                alert('Left swipe detected!');
                break;
            }
            case 'upSwipe': {
                //do some goodness...
                alert('Up swipe detected!');
                break;
            }
            case 'downSwipe': {
                //do some goodness...
                alert('Down swipe detected!');
                break;
            }
            case 'longTap':{
                //do some goodness...
                alert('It`s veeeeery long tap!');
                break;
            }
            case 'singleTap':{
                //do some goodness...
                alert('Ok, single tap/onclick detected!');
                break;
            }
            default: {
                break;
            }
        }
    });

## FAQ
 - Q: Why this plugin is more useful then, for example, Hammer.js?
 A: This plugin is useful to handle gestures onto iframe. Iframe "catches" the gestures, and this plugin is "listening" events on CordovaWebView, not "inside" CordovaWebView. So, this helps to listen and handle gestures easily instead of implementing of an event listener in "child" iframe.
 - Q: Wait! Hammer.js can [handle gestures inside iframe](http://stackoverflow.com/questions/17307907/iframe-conflicting-with-hammer-js-gestures) , isn`t it? 
 A: Yes, hammer.js can handle it. But in case of using dynamically generated iframes (for example Youtube carousel slider, which is getting slides via AJAX) we have rather less dances with a tambourine around the campfire because of no need to implement listeners inside iframe body
