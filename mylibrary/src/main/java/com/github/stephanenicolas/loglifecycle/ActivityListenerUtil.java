package com.github.stephanenicolas.loglifecycle;

import android.app.Activity;

import java.util.ArrayList;

@SuppressWarnings("UnusedDeclaration")
public class ActivityListenerUtil {

    private static final ArrayList<ActivityListener> listeners = new ArrayList<>();
    
    public static void registerListener( ActivityListener listener ) {
        listeners.add(listener);
    }

    public static void onStart( Activity activity ) {
        for( ActivityListener listener : listeners )
            listener.onActivityStarted(activity);
    }
    public static void onResume( Activity activity ) {
        for( ActivityListener listener : listeners )
            listener.onActivityResumed(activity);
    }
    public static void onPause( Activity activity ) {
        for( ActivityListener listener : listeners )
            listener.onActivityPaused(activity);
    }
    public static void onStop( Activity activity ) {
        for( ActivityListener listener : listeners )
            listener.onActivityStopped(activity);
    }
    public static void onDestroy( Activity activity ) {
        for( ActivityListener listener : listeners )
            listener.onActivityDestroyed(activity);
    }
}


