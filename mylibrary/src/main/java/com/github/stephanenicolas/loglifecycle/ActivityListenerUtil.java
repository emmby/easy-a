package com.github.stephanenicolas.loglifecycle;

import android.app.Activity;

import java.util.ArrayList;

@SuppressWarnings("UnusedDeclaration")
public class ActivityListenerUtil {

    private static final ArrayList<ActivityListener> listeners = new ArrayList<>();
    
    public static void registerListener( ActivityListener listener ) {
        listeners.add(listener);
    }

    
    public static final class internal {

        public static void _onStart( Activity activity ) {
            for( ActivityListener listener : listeners )
                listener.onActivityStarted(activity);
        }
        public static void _onResume( Activity activity ) {
            for( ActivityListener listener : listeners )
                listener.onActivityResumed(activity);
        }
        public static void _onPause( Activity activity ) {
            for( ActivityListener listener : listeners )
                listener.onActivityPaused(activity);
        }
        public static void _onStop( Activity activity ) {
            for( ActivityListener listener : listeners )
                listener.onActivityStopped(activity);
        }
        public static void _onDestroy( Activity activity ) {
            for( ActivityListener listener : listeners )
                listener.onActivityDestroyed(activity);
        }

    }
}


