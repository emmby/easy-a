package com.github.stephanenicolas.loglifecycle;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityListenerUtil {

    private static final ArrayList<ActivityListener> listeners = new ArrayList<>();
    
    public static void registerListener( ActivityListener listener ) {
        listeners.add(listener);
    }
    
    public static void onDestroy( Activity activity ) {
        for( ActivityListener listener : listeners )
            listener.onDestroy(activity);
    }
}


