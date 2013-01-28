/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 *
 */

package at.ssw.hotswap.test.structural;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.ssw.hotswap.HotSwapTool;
import at.ssw.hotswap.test.methods.OverrideMethodTest;

/**
 * Tests for adding an interface to a class and for changing the methods of an interface.
 *
 * @author Thomas Wuerthinger
 */
public class InterfaceTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        HotSwapTool.toVersion(InterfaceTest.class, 0);
    }

    @After
    public void tearDown() throws Exception {
    }

    public static interface A {

        int giveMeFive();
    }

    public static class AImpl {

        public int giveMeFive() {
            return 5;
        }
    }

    public static class BImpl implements A {

        @Override
        public int giveMeFive() {
            return 5;
        }

        public int giveMeTen() {
            return 10;
        }
    }

    public static class AImpl___1 implements A {

        @Override
        public int giveMeFive() {
            return 5;
        }
    }

    public static interface A___2 {

        int giveMeTen();
    }

    public static class Helper {

        public int giveMeTenA2(A a) {
            return 3;
        }
    }

    public static class Helper___2 {

        public int giveMeTenA2(A a) {
            return ((A___2) a).giveMeTen();
        }
    }

    @Test
    public void testAddInterface() {

        assert HotSwapTool.getCurrentVersion(OverrideMethodTest.class) == 0;

        AImpl a = new AImpl();
        assertFalse(a instanceof A);
        try {
            int val = (((A) a).giveMeFive());
            fail();
        } catch (ClassCastException e) {
        }

        HotSwapTool.toVersion(InterfaceTest.class, 1);
        assertTrue(a instanceof A);
        assertEquals(5, ((A) a).giveMeFive());

        HotSwapTool.toVersion(InterfaceTest.class, 0);
        assertFalse(a instanceof A);
    }

    @Test
    public void testModifyInterface() {

        assert HotSwapTool.getCurrentVersion(OverrideMethodTest.class) == 0;

        BImpl b = new BImpl();
        assertTrue(b instanceof A);

        HotSwapTool.toVersion(InterfaceTest.class, 2);

        assertEquals(10, new Helper().giveMeTenA2(b));

        HotSwapTool.toVersion(InterfaceTest.class, 0);
        assertEquals(5, ((A) b).giveMeFive());
    }
}
