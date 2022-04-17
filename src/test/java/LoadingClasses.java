import com.argyriou.proxies.ClassFinder;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import java.util.Set;

public class LoadingClasses {
    @Test
    public void shouldLoadClassesInDesiredPkg() {
        ClassFinder finder = new ClassFinder();
        Set classes = finder.loadAllClassesInClassPath("./target/classes");
        Assert.assertFalse(classes.isEmpty());
    }
}
