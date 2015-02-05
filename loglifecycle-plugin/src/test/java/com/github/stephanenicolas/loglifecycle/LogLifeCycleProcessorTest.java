package com.github.stephanenicolas.loglifecycle;

import android.app.Activity;
import android.os.Bundle;

import org.junit.Before;
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
@RunWith(LogLifeCycleTestRunner.class)
@Config( manifest = Config.NONE)
public class LogLifeCycleProcessorTest {

  @Test
  public void shouldLog_activity() {
      final boolean[] b = new boolean[]{false};
      ActivityListenerUtil.registerListener(new ActivityListener() {
                                                @Override
                                                public void onDestroy(Activity activity) {
                                                    b[0] = true;
                                                }
                                            });

      ActivityController<TestActivity> controller = Robolectric.buildActivity(TestActivity.class).create();
      Activity a = controller.get();
      assertFalse(b[0]);
      controller.destroy();
      assertTrue(b[0]);
  }

//  @Test
//  public void shouldLog_fragment() {
//    Robolectric.buildActivity(TestActivityWitFragment.class).create().start().stop().destroy().get();
//    List<ShadowLog.LogItem> logItems = ShadowLog.getLogsForTag("LogLifeCycle");
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