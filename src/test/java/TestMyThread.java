
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import dz.MyThread;

import java.util.HashSet;
import java.util.Set;


public class TestMyThread {

    @Test
    public void testAddToSet(){
        String q = "q";
        Set<String> set = new HashSet<String>();
        Assert.assertTrue(MyThread.addToSet(q, set));
        Assert.assertFalse(MyThread.addToSet(q, set));
    }

}
