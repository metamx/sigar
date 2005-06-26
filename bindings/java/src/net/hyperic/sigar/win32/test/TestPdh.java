package net.hyperic.sigar.win32.test;

import net.hyperic.sigar.test.SigarTestCase;
import net.hyperic.sigar.win32.Pdh;
import net.hyperic.sigar.win32.Win32Exception;

public class TestPdh extends SigarTestCase {

    public TestPdh(String name) {
        super(name);
    }

    private void getValue(String key) throws Exception {
        Pdh pdh = new Pdh();

        assertGtEqZeroTrace("raw..." + key,
                            (long)pdh.getRawValue(key));
        assertGtEqZeroTrace("fmt..." + key,
                            (long)pdh.getFormattedValue(key));
    }

    public void testGetValue() throws Exception {
        String[] keys = {
            "\\Memory\\Available Bytes",
            "\\Memory\\Pages/sec",
        };
        for (int i=0; i<keys.length; i++) {
            getValue(keys[i]);
        }
        /* XXX hangs for a while on my XP box
        String bogusKey = "\\Does Not\\Exist";
        try {
            new Pdh().getRawValue(bogusKey);
            assertTrue(false);
        } catch (Win32Exception e) {
            assertTrue(true);
            traceln(bogusKey + "=" + e.getMessage());
        }
        */
    }

    public void testPdh () throws Exception {

        Pdh pdh = new Pdh();

        String[] iface = Pdh.getKeys("Thread");
        
        assertTrue(iface.length > 0);
    }
}
