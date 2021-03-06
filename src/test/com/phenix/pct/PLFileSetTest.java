/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2002 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "Ant" and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
package com.phenix.pct;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.phenix.pct.RCodeInfo.InvalidRCodeException;

/**
 * Class for testing PLFileSet
 * 
 * @author <a href="mailto:g.querret+PCT@gmail.com">Gilles QUERRET</a>
 */
public class PLFileSetTest extends BuildFileTestNg {

    @Test(groups = { "v10" })
    public void test1() {
        // Really crude, but we rely on prodict.pl in $DLC/tty
        // Number of files is different in every release
        try {
            DLCVersion version = DLCVersion.getObject(new File(System.getProperty("DLC")));
            if (version.getMajorVersion() != 10)
                return;
        } catch (IOException e) {
            return;
        } catch (InvalidRCodeException e) {
            return;
        }
        
        configureProject("PLFileSet/test1/build.xml");

        executeTarget("test1");
        File f1 = new File("PLFileSet/test1/lib1/prodict");
        assertEquals(f1.list().length, 36);

        executeTarget("test2");
        File f2 = new File("PLFileSet/test1/lib2/prodict");
        assertEquals(f2.list().length, 14);

        executeTarget("test3");
        File f3 = new File("PLFileSet/test1/lib3/prodict");
        assertEquals(f3.list().length, 3);
    }

    @Test(groups = {"v11"})
    public void test2() {
        // Really crude, but we rely on prodict.pl in $DLC/tty
        // Number of files is different in every release
        try {
            DLCVersion version = DLCVersion.getObject(new File(System.getProperty("DLC")));
            if (version.getMajorVersion() != 11)
                return;
        } catch (IOException e) {
            return;
        } catch (InvalidRCodeException e) {
            return;
        }

        configureProject("PLFileSet/test2/build.xml");

        executeTarget("test1");
        File f1 = new File("PLFileSet/test2/lib1/prodict");
        assertEquals(f1.list().length, 38);

        executeTarget("test2");
        File f2 = new File("PLFileSet/test2/lib2/prodict");
        assertEquals(f2.list().length, 14);

        executeTarget("test3");
        File f3 = new File("PLFileSet/test2/lib3/prodict");
        assertEquals(f3.list().length, 3);
    }

}
