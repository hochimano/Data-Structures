import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DivisionTests {
 
    @Test
    public void testDivide(){
        Division d = new Division();
        assertEquals(d.divide(2, 4), 0);
    }
}
