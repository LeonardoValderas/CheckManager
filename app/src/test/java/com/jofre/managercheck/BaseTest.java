package com.jofre.managercheck;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.internal.Shadow;
import org.robolectric.shadows.ShadowApplication;

/**
 * Created by LEO on 13/8/2016.
 */

public abstract class BaseTest {
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        if (RuntimeEnvironment.application != null) {
            ShadowApplication shadowApp = Shadows.shadowOf(RuntimeEnvironment.application);
   //         shadowApp.grantPermissions("android.permission.INTERNET");
        }
    }
}
