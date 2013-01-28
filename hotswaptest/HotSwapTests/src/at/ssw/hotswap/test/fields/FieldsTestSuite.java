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

package at.ssw.hotswap.test.fields;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Class redefinition tests that may change the methods and fields of class, but do not change the superklass or the implemented
 * interface.
 * 
 * @author Thomas Wuerthinger
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    AccessDeletedFieldTest.class,
    FieldChangedOrderTest.class,
    FieldModificationTest.class,
    ObjectStressTest.class,
    YieldTest.class,
    ComplexFieldTest.class,
    StringFieldTest.class,
    RedefinePrivateFieldTest.class,
    AccessDeletedStaticFieldTest.class
})
public class FieldsTestSuite {
}
