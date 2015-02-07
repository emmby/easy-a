package com.github.stephanenicolas.lxglifecycle;

import android.app.Activity;

import java.util.ArrayList;

@SuppressWarnings("UnusedDeclaration")
public class ActivityListenerUtil {

    private static final ArrayList<ActivityListener> listeners = new ArrayList<>();

    /**
     * Use this method to register a listener to listen to all activities.
     * It's important that this listener does not have any references to any
     * contexts, or those contexts will never be garbage collected.  So pay
     * particular attention when creating anonynmous inner classes inside of
     * activities, services, views, etc., since those will all have implicit
     * references to the context.
     * 
     * See #unregisterListener if you wish to explicitly de-register a listener.
     */
    public static void registerListener( ActivityListener listener ) {
        listeners.add(listener);
    }

    public static void unregisterListener( ActivityListener listener ) {
        listeners.remove(listener);
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


