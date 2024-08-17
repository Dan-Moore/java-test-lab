package lab.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Snippets of TestNG assert statements.
 */
public class Assert_Example {

    @Test
    public void testHappyPath() {
        Assert.assertEquals(true,true,"Foo");
        Assert.assertTrue(true, "Bar");
        Assert.assertSame(true,true, "Baz");
        Assert.assertNull(null, "");
    }

    @Test
    public void testFailure() {
        Assert.assertNotNull(null, "object was null");
    }
}
