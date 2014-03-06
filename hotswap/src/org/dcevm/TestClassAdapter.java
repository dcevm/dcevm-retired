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
package org.dcevm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.commons.RemappingClassAdapter;
import org.objectweb.asm.commons.RemappingMethodAdapter;

import java.util.Map;

/**
 * @author Ivan Dubrov
 */
public class TestClassAdapter extends RemappingClassAdapter {
    /**
     * This suffix is automatically removed from the method.
     */
    private final static String METHOD_SUFFIX = "___";

    private boolean isObject;

    public TestClassAdapter(ClassVisitor cv, final Map<String, String> typeMappings) {
        super(cv, new Remapper() {
            @Override
            public String mapType(String type) {
                return typeMappings.containsKey(type) ? typeMappings.get(type) : type;
            }
        });
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        // For java/lang/Object redefinition
        String newName = remapper.mapType(name);
        if (newName.equals("java/lang/Object")) {
            superName = null;
            isObject = true;
        }
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        return super.visitMethod(access, stripMethodSuffix(name), desc, signature, exceptions);
    }

    /**
     * Get renamed class name.
     *
     * @return
     */
    public String getClassName() {
        return remapper.mapType(className);
    }

    protected MethodVisitor createRemappingMethodAdapter(
            int access,
            String newDesc,
            MethodVisitor mv)
    {
        return new RemappingMethodAdapter(access, newDesc, mv, remapper) {
            @Override
            public void visitMethodInsn(int opcode, String owner, String name, String desc) {
                if (name.equals("<init>") && isObject && owner.equals("java/lang/Object")) {
                    return;
                }

                super.visitMethodInsn(opcode, owner, stripMethodSuffix(name), desc);
            }
        };
    }

    private static String stripMethodSuffix(String name) {
        int pos = name.indexOf(METHOD_SUFFIX);
        return (pos != -1) ? name.substring(0, pos) : name;
    }
}

