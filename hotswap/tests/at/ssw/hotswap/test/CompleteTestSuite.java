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

package at.ssw.hotswap.test;

import at.ssw.hotswap.test.accesstests.AccessTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import at.ssw.hotswap.test.body.BodyTestSuite;
import at.ssw.hotswap.test.eval.EvalTestSuite;
import at.ssw.hotswap.test.fields.FieldsTestSuite;
import at.ssw.hotswap.test.methods.MethodsTestSuite;
import at.ssw.hotswap.test.natives.NativesTestSuite;
import at.ssw.hotswap.test.transformer.TransformerTestSuite;
import at.ssw.hotswap.test.structural.StructuralTestSuite;

/**
 * Summarizes all available test suites.
 * 
 * @author Thomas Wuerthinger
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    BodyTestSuite.class,
    EvalTestSuite.class,
    MethodsTestSuite.class,
    FieldsTestSuite.class,
    StructuralTestSuite.class,
    TransformerTestSuite.class
    //NativesTestSuite.class,
    //AccessTestSuite.class
})
public class CompleteTestSuite {
}
