package ru.megaduck.plugin.gestures;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Megaduck on 28.10.15.
 */
public class MegaduckGestures extends CordovaPlugin {
    Context thisContext;

    public MegaduckGestures() {}

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        thisContext=cordova.getActivity().getApplicationContext();

    }

    public boolean execute(final String action, JSONArray args, final CallbackContext callbackContext) throws JSONException
    {
        webView.getView().setOnTouchListener(new OnSwipeTouchLissna(thisContext, webView.getView()) {
            @Override
            public void onSwipeRight() {
				echo("rightSwipe", callbackContext);
            }

            @Override
            public void onSwipeLeft() {
                echo("leftSwipe", callbackContext);
            }

            @Override
            public void onSwipeUp() {
                echo("upSwipe", callbackContext);
            }

            @Override
            public void onSwipeDown() {
                echo("downSwipe", callbackContext);
            }

            @Override
            public void meinLongPress() {
                echo("longTap", callbackContext);
            }

            @Override
            public void meinSingleTap() {
                echo("singleTap", callbackContext);
            }
        });

        return true;
    }

    private void echo(String result, CallbackContext callbackContext) {
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, result);
        pluginResult.setKeepCallback(true);
        callbackContext.sendPluginResult(pluginResult);
    }



    private class OnSwipeTouchLissna implements View.OnTouchListener {

        private final GestureDetector gestureDetector;
        View view;

        public OnSwipeTouchLissna(Context context, View view) {
            gestureDetector = new GestureDetector(context, new GestureListener());
            this.view=view;
        }

        public void onSwipeLeft() {
        }

        public void onSwipeRight() {
        }

        public void onSwipeUp() {
        }

        public void onSwipeDown() {
        }

        public void meinLongPress() {
        }

        public void meinSingleTap() {
        }

        public boolean onTouch(View v, MotionEvent event) {

            view.setSystemUiVisibility(View.GONE);
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_DISTANCE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getY();
                //horizontal swipilko
                if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (distanceX > 0) { 
						onSwipeRight(); 
					}
                    else { 
						onSwipeLeft(); 
					}
                    return true;
                }
                else {
                    //vertical swipilko
                    if (Math.abs(distanceX) < Math.abs(distanceY) && Math.abs(distanceY) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (distanceY > 0) { 
							onSwipeDown(); 
						}
                        else { 
							onSwipeUp(); 
						}
                        return true;
                    }
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }

            @Override
            public void onLongPress(MotionEvent e) {
                meinLongPress();
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                meinSingleTap();
                return true;
            }
        }
    }
}
