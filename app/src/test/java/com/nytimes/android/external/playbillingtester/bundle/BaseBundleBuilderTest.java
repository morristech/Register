package com.nytimes.android.external.playbillingtester.bundle;

import com.nytimes.android.external.playbillingtester.APIOverridesAndPurchases;
import com.nytimes.android.external.playbillingtesterlib.GoogleUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BaseBundleBuilderTest {

    private BaseBundleBuilder testObject;

    @Mock
    private APIOverridesAndPurchases apiOverridesAndPurchases;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testReturnOkWhenDefault() {
        testObject = new BaseBundleBuilder(apiOverridesAndPurchases) {
            @Override
            protected int rawResponseCode() {
                return APIOverridesAndPurchases.RESULT_DEFAULT;
            }
        };
        assertThat(testObject.responseCode())
                .isEqualTo(GoogleUtil.RESULT_OK);
    }

    @Test
    public void testReturnOverrideWhenNotDefault() {
        testObject = new BaseBundleBuilder(apiOverridesAndPurchases) {
            @Override
            protected int rawResponseCode() {
                return GoogleUtil.RESULT_BILLING_UNAVAILABLE;
            }
        };
        assertThat(testObject.responseCode())
                .isEqualTo(GoogleUtil.RESULT_BILLING_UNAVAILABLE);
    }
}