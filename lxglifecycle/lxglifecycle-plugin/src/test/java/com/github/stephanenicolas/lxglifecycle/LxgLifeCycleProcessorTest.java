package com.github.stephanenicolas.lxglifecycle;

import android.app.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author SNI
 */
@RunWith(lxglifecycleTestRunner.class)
@Config( manifest = Config.NONE)
public class lxglifecycleProcessorTest {

  @Test
  public void shouldLog_activity() {
      final boolean[] b = new boolean[5];
      ActivityListenerUtil.registerListener(
          new ActivityListener() {
              @Override
              public void onActivityStarted(Activity activity) {
                  b[0] = true;
              }

              @Override
              public void onActivityResumed(Activity activity) {
                  b[1] = true;
              }

              @Override
              public void onActivityPaused(Activity activity) {
                  b[2] = true;
              }

              @Override
              public void onActivityStopped(Activity activity) {
                  b[3] = true;
              }

              @Override
                public void onActivityDestroyed(Activity activity) {
                  b[4] = true;
                }
            });

      ActivityController<TestActivity> controller = Robolectric.buildActivity(TestActivity.class).create();

      assertFalse(b[0]);
      controller.start();
      assertTrue(b[0]);

      assertFalse(b[1]);
      controller.resume();
      assertTrue(b[1]);

      assertFalse(b[2]);
      controller.pause();
      assertTrue(b[2]);

      assertFalse(b[3]);
      controller.stop();
      assertTrue(b[3]);

      assertFalse(b[4]);
      controller.destroy();
      assertTrue(b[4]);
  }

//  @Test
//  public void shouldLog_fragment() {
//    Robolectric.buildActivity(TestActivityWitFragment.class).create().start().stop().destroy().get();
//    List<ShadowLog.LogItem> logItems = ShadowLog.getLogsForTag("lxglifecycle");
//
//    assertNotNull(logItems);
//    assertLogContainsMessage(logItems, "onStart");
//    assertLogContainsMessage(logItems, "onStop");
//  }
//
  public static class TestActivity extends Activity {
  }
//
//  //do not log activity now, only fragment
//  public static class TestActivityWitFragment extends Activity {
//
//    public void onCreate(Bundle bundle) {
//      TestFragment testFragment = new TestFragment();
//      getFragmentManager().beginTransaction().add(testFragment, "TAG").commit();
//    }
//  }
//
//  public static class TestFragment extends Fragment {
//  }
//
//
}