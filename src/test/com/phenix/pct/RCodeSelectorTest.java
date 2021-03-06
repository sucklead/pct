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
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Class for testing RCodeSelector
 * 
 * @author <a href="mailto:g.querret+PCT@gmail.com">Gilles QUERRET</a>
 */
public class RCodeSelectorTest extends BuildFileTestNg {

    @Test(groups = { "v10" })
    public void test1() {
        configureProject("RCodeSelector/test1/build.xml");
        executeTarget("prepare");

        executeTarget("test1");

        File f1 = new File("RCodeSelector/test1/copy1");
        assertEquals(0, f1.list().length);

        File f2 = new File("RCodeSelector/test1/copy2");
        assertEquals(0, f2.list().length);

        File f3 = new File("RCodeSelector/test1/copy3");
        assertEquals(1, f3.list().length);
        assertTrue(f3.list()[0].equals("test2.r"));

        File f4 = new File("RCodeSelector/test1/copy4");
        assertEquals(1, f4.list().length);
        assertTrue(f4.list()[0].equals("test2.r"));

        File f5 = new File("RCodeSelector/test1/copy5");
        assertEquals(0, f5.list().length);

        File f6 = new File("RCodeSelector/test1/copy6");
        assertEquals(1, f6.list().length);
        assertTrue(f6.list()[0].equals("test3.r"));

        File f7 = new File("RCodeSelector/test1/copy7");
        assertEquals(1, f7.list().length);
        assertTrue(f7.list()[0].equals("test2.r"));

        File f8 = new File("RCodeSelector/test1/copy8");
        assertEquals(2, f8.list().length);
        List<String> tmp =  Arrays.asList(f8.list());
        assertTrue(tmp.contains("test2.r"));
        assertTrue(tmp.contains("test3.r"));

        executeTarget("test2");
        f1 = new File("RCodeSelector/test1/copylib1");
        assertEquals(0, f1.list().length);

        f2 = new File("RCodeSelector/test1/copylib2");
        assertEquals(0, f2.list().length);

        f3 = new File("RCodeSelector/test1/copylib3");
        assertEquals(1, f3.list().length);
        assertTrue(f3.list()[0].equals("test2.r"));

        f4 = new File("RCodeSelector/test1/copylib4");
        assertEquals(1, f4.list().length);
        assertTrue(f4.list()[0].equals("test2.r"));

        f5 = new File("RCodeSelector/test1/copylib5");
        assertEquals(0, f5.list().length);

        f6 = new File("RCodeSelector/test1/copylib6");
        assertEquals(1, f6.list().length);
        assertTrue(f6.list()[0].equals("test3.r"));

        f7 = new File("RCodeSelector/test1/copylib7");
        assertEquals(1, f7.list().length);
        assertTrue(f7.list()[0].equals("test2.r"));

        f8 = new File("RCodeSelector/test1/copylib8");
        assertEquals(2, f8.list().length);
        List<String> tmp2 =  Arrays.asList(f8.list());
        assertTrue(tmp2.contains("test2.r"));
        assertTrue(tmp2.contains("test3.r"));
    }

}
