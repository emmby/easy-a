package com.github.stephanenicolas.loglifecycle;

import android.app.Activity;

/**
 * No-op implementation of ActivityListener.  Claims to be
 * abstract, but really isn't.
 */
public class AbstractActivityListener implements ActivityListener {
    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }
}
