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
import static org.testng.Assert.assertFalse;

import org.apache.tools.ant.BuildException;
import org.testng.annotations.Test;

import com.google.common.io.Files;
import com.phenix.pct.RCodeInfo.InvalidRCodeException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Class for testing PCTCompile task
 * 
 * @author <a href="mailto:justus_phenix@users.sourceforge.net">Gilles QUERRET</a>
 */
public class PCTCompileTest extends BuildFileTestNg {

    @Test(groups= {"all"}, expectedExceptions = BuildException.class)
    public void test1() {
        configureProject("PCTCompile/test1/build.xml");
        executeTarget("test");
    }

    @Test(groups= {"all"})
    public void test2() {
        configureProject("PCTCompile/test2/build.xml");
        executeTarget("test");
    }

    @Test(groups= {"all"})
    public void test3() {
        configureProject("PCTCompile/test3/build.xml");
        executeTarget("test");

        File f = new File("PCTCompile/test3/build/test.r");
        assertTrue(f.exists());
    }

    @Test(groups= {"all"})
    public void test3bis() {
        configureProject("PCTCompile/test3bis/build.xml");
        expectBuildException("test", "Compilation should fail");

        File f = new File("PCTCompile/test3bis/build/test.r");
        assertFalse(f.exists());
    }

    @Test(groups= {"all"})
    public void test4() {
        configureProject("PCTCompile/test4/build.xml");
        executeTarget("test");

        File f1 = new File("PCTCompile/test4/build/test.r");
        File f2 = new File("PCTCompile/test4/build2/test.r");
        assertTrue(f1.exists());
        assertTrue(f2.exists());
        assertTrue(f2.length() < f1.length());
    }

    @Test(groups= {"all"})
    public void test5() {
        configureProject("PCTCompile/test5/build.xml");
        executeTarget("test");

        File f = new File("PCTCompile/test5/build/wizz~~'~.r");
        assertTrue(f.exists());
    }

    @Test(groups= {"all"})
    public void test6() {
        configureProject("PCTCompile/test6/build.xml");
        executeTarget("test1");

        File f = new File("PCTCompile/test6/build/test.r");
        long mod = f.lastModified();
        executeTarget("test2");
        assertTrue(mod == f.lastModified());
    }

    @Test(groups= {"all"})
    public void test7() {
        configureProject("PCTCompile/test7/build.xml");
        executeTarget("test1");

        File f = new File("PCTCompile/test7/build/test.r");
        long mod = f.lastModified();
        executeTarget("test2");
        assertTrue(mod < f.lastModified());
    }

    @Test(groups= {"all"})
    public void test8() {
        configureProject("PCTCompile/test8/build.xml");
        executeTarget("test1");

        File f = new File("PCTCompile/test8/build/test.r");
        long mod = f.lastModified();
        executeTarget("test2");
        assertTrue(mod < f.lastModified());
    }

    @Test(groups= {"all"})
    public void test9() {
        configureProject("PCTCompile/test9/build.xml");
        executeTarget("test1");

        File f = new File("PCTCompile/test9/build/test.r");
        long mod = f.lastModified();
        executeTarget("test2");
        assertTrue(mod < f.lastModified());
    }

    @Test(groups= {"all"})
    public void test10() {
        configureProject("PCTCompile/test10/build.xml");
        executeTarget("test1");

        File f = new File("PCTCompile/test10/build/test.r");
        long mod = f.lastModified();
        executeTarget("test2");
        assertTrue(mod < f.lastModified());
    }

    @Test(groups= {"all"})
    public void test11() {
        configureProject("PCTCompile/test11/build.xml");

        expectBuildException("test", "Second task should not be launched");
        File f = new File("PCTCompile/test11/build/test2.r");
        assertFalse(f.exists());

        executeTarget("test2");
        File f2 = new File("PCTCompile/test11/build2/test2.r");
        assertTrue(f2.exists());
        
        executeTarget("test3");
        assertTrue(new File("PCTCompile/test11/build3/test0.r").exists());
        assertFalse(new File("PCTCompile/test11/build3/test1.r").exists());
        assertTrue(new File("PCTCompile/test11/build3/test2.r").exists());
        
        expectBuildException("test4", "failOnError set to true");
        assertTrue(new File("PCTCompile/test11/build4/test0.r").exists());
        assertFalse(new File("PCTCompile/test11/build4/test1.r").exists());
        assertFalse(new File("PCTCompile/test11/build4/test2.r").exists());
    }

    @Test(groups= {"all"})
    public void test12() {
        configureProject("PCTCompile/test12/build.xml");
        expectBuildException("test1", "File with underscore");
        File f = new File("PCTCompile/test12/build/test.r");
        assertFalse(f.exists());
        executeTarget("test2");
        assertTrue(f.exists());
    }

    @Test(groups= {"all"})
    public void test13() {
        configureProject("PCTCompile/test13/build.xml");
        executeTarget("test1");

        File f = new File("PCTCompile/test13/build/test.r");
        long mod = f.lastModified();
        executeTarget("test2");
        assertTrue(mod < f.lastModified());
    }

    @Test(groups= {"all"})
    public void test14() {
        configureProject("PCTCompile/test14/build.xml");
        executeTarget("base");
        executeTarget("test1");

        File f1 = new File("PCTCompile/test14/build/test.r");
        File f2 = new File("PCTCompile/test14/build/test2.r");
        assertTrue(f1.exists());
        assertTrue(f2.exists());

        long mod1 = f1.lastModified();
        long mod2 = f2.lastModified();
        executeTarget("update");
        executeTarget("test2");
        assertTrue(mod1 < f1.lastModified());
        assertTrue(mod2 < f2.lastModified());
    }

    @Test(groups= {"all"})
    public void test15() {
        configureProject("PCTCompile/test15/build.xml");
        executeTarget("base");
        executeTarget("test1");

        File f = new File("PCTCompile/test15/build/test.r");
        assertTrue(f.exists());

        long mod = f.lastModified();
        executeTarget("test2");
        assertTrue(mod == f.lastModified());
    }

    @Test(groups= {"v10", "v11"})
    public void test16() {
        configureProject("PCTCompile/test16/build.xml");
        executeTarget("xcode");
        File f1 = new File("PCTCompile/test16/src/xcode/test.p");
        assertTrue(f1.exists());
        executeTarget("test");

        File f2 = new File("PCTCompile/test16/build/std/test.r");
        assertTrue(f2.exists());
        File f3 = new File("PCTCompile/test16/build/xcode/test.r");
        assertTrue(f3.exists());
    }

    @Test(groups= {"v10", "v11"})
    public void test17() {
        configureProject("PCTCompile/test17/build.xml");
        executeTarget("xcode");
        File f1 = new File("PCTCompile/test17/src/xcode/test.p");
        assertTrue(f1.exists());
        expectBuildException("test1", "No XCodeKey");

        executeTarget("test2");
        File f2 = new File("PCTCompile/test17/build/xcode/test.r");
        assertFalse(f2.exists());
        File f3 = new File("PCTCompile/test17/build2/xcode/test.r");
        assertTrue(f3.exists());
    }

    // @Test
    // public void test18() {
    // executeTarget("test18init");
    // executeTarget("test18");
    //
    // File f = new File("build/sandbox/test.r");
    // File f2 = new File("build/sandbox/test2.r");
    // assertTrue(f.exists());
    // assertTrue(f2.exists());
    //
    // long mod = f.lastModified();
    // long mod2 = f2.lastModified();
    // executeTarget("test18bis");
    // assertTrue(mod < f.lastModified());
    // assertTrue(mod2 < f2.lastModified());
    // }

    @Test(groups= {"all"})
    public void test19() {
        configureProject("PCTCompile/test19/build.xml");
        executeTarget("test1");

        File f1 = new File("PCTCompile/test19/build/test.r");
        File f2 = new File("PCTCompile/test19/build/.pct/test.p.crc");
        assertTrue(f1.exists());
        assertTrue(f2.exists());
        long mod1 = f1.lastModified();
        long mod2 = f2.lastModified();
        executeTarget("test2");
        assertTrue(mod1 < f1.lastModified());
        assertTrue(mod2 < f2.lastModified());
    }

    @Test(groups= {"all"})
    public void test20() {
        configureProject("PCTCompile/test20/build.xml");
        executeTarget("test1");

        File dotR = new File("PCTCompile/test20/build/test.r");
        File f1 = new File("PCTCompile/test20/build/.pct/test.p");
        File f2 = new File("PCTCompile/test20/build/.pct/test.p.preprocess");
        File f3 = new File("PCTCompile/test20/build/.pct/test.p.dbg");
        File f4 = new File("PCTCompile/test20/build/.pct/test.p.xref");
        File f5 = new File("PCTCompile/test20/debug/test.p");
        File f6 = new File("PCTCompile/test20/debug/dir1/dir2/test.p");
        assertTrue(dotR.exists());
        assertFalse(f1.exists());
        assertFalse(f2.exists());
        assertFalse(f3.exists());
        assertFalse(f4.exists());

        executeTarget("test2");
        assertTrue(f1.exists(), "Unable to find listing file");
        assertTrue(f2.exists(), "Unable to find preprocess file");
        assertTrue(f3.exists(), "Unable to find debug-listing file");
        assertTrue(f4.exists(), "Unable to find xref file");
        assertTrue((f4.length() > 0), "Empty xref file");

        executeTarget("test3");
        assertTrue(f5.exists(), "Unable to find debug-listing file");
        assertTrue(f6.exists(), "Unable to find debug-listing file");
    }

    @Test(groups= {"v10", "v11"})
    public void test21() {
        configureProject("PCTCompile/test21/build.xml");
        executeTarget("test");

        File f1 = new File("PCTCompile/test21/build/package/testclass.r");
        assertTrue(f1.exists());
    }

    @Test(groups= {"v10", "v11"})
    public void test22() {
        configureProject("PCTCompile/test22/build.xml");
        executeTarget("test");

        File f1 = new File("PCTCompile/test22/build/X.r");
        assertTrue(f1.exists());
        File f2 = new File("PCTCompile/test22/build/Y.r");
        assertTrue(f2.exists());
        File f3 = new File("PCTCompile/test22/build/.pct/X.cls.crc");
        assertTrue(f3.exists());
        File f4 = new File("PCTCompile/test22/build/.pct/Y.cls.crc");
        assertTrue(f4.exists());

        executeTarget("test2");
        File f5 = new File("PCTCompile/test22/build2/Y.r");
        assertTrue(f5.exists());
        File f6 = new File("PCTCompile/test22/build2/X.r");
        assertTrue(f6.exists());
    }

    @Test(groups= {"win"})
    public void test23() {
        configureProject("PCTCompile/test23/build.xml");
        expectBuildException("test1", "Should fail - No stream-io");

        File f = new File("PCTCompile/test23/build/test.r");
        assertFalse(f.exists());
        executeTarget("test2");
        assertTrue(f.exists());
    }

    @Test(groups= {"all"})
    public void test24() {
        configureProject("PCTCompile/test24/build.xml");

        File f = new File("PCTCompile/test24/build/test.r");
        assertFalse(f.exists());

        executeTarget("test1");
        assertFalse(f.exists());
        executeTarget("test2");
        assertTrue(f.exists());
    }

    @Test(groups= {"all"})
    public void test25() {
        configureProject("PCTCompile/test25/build.xml");
        executeTarget("test");

        File f = new File("PCTCompile/test25/build/test.r");
        assertTrue(f.exists());
    }

    // Dropping this test case, doesn't work on 11.3 anymore, and no time to maintain it
    // @Test(groups= {"win"})
    public void test26() {
        configureProject("PCTCompile/test26/build.xml");
        executeTarget("test");

        File f = new File("PCTCompile/test26/build/Ö_example.r");
        assertTrue(f.exists());
    }

    @Test(groups= {"v10", "v11"})
    public void test27() {
        configureProject("PCTCompile/test27/build.xml");
        executeTarget("test");

        File f1 = new File("PCTCompile/test27/build/eu/rssw/pct/A.r");
        assertTrue(f1.exists());
        File f2 = new File("PCTCompile/test27/build/eu/rssw/pct/B.r");
        assertTrue(f2.exists());
        File f3 = new File("PCTCompile/test27/build/eu/rssw/pct/X.r");
        assertTrue(f3.exists());
        File f4 = new File("PCTCompile/test27/build/eu/rssw/pct/Y.r");
        assertTrue(f4.exists());
        File f5 = new File("PCTCompile/test27/build/eu/rssw/pct/Z.r");
        assertTrue(f5.exists());
        File f6 = new File("PCTCompile/test27/build/.pct/eu/rssw/pct/Z.cls.hierarchy");
        assertTrue(f6.exists());
        assertTrue(f6.length() > 0);
    }

    @Test(groups= {"win"})
    public void test28() {
        configureProject("PCTCompile/test28/build.xml");
        executeTarget("build");
        
        File f1 = new File("PCTCompile/test28/src-tty/test.p");
        assertTrue(f1.exists());
        assertTrue(f1.length() > 0);
        File f2 = new File("PCTCompile/test28/src-gui/test.p");
        assertTrue(f2.exists());
        assertTrue(f2.length() > 0);
        File f3 = new File("PCTCompile/test28/src-tty/sub1/sub2/sub3/test.p");
        assertTrue(f3.exists());
        File f4 = new File("PCTCompile/test28/src-tty/sub1/sub2/sub4/test.p");
        assertTrue(f4.exists());

        executeTarget("test");
        String str1 = getProject().getProperty("test28-tty");
        assertTrue(str1.equals("TTY"));
        String str2 = getProject().getProperty("test28-gui");
        assertTrue(str2.startsWith("MS-WIN"));
    }

    @Test(groups = {"all"})
    public void test29() {
        configureProject("PCTCompile/test29/build.xml");
        executeTarget("build");
        
        File f1 = new File("PCTCompile/test29/build1/test.r");
        assertTrue(f1.exists());
        File f2 = new File("PCTCompile/test29/build2/test.r");
        assertTrue(f2.exists());
        
        executeTarget("test1");
        executeTarget("test2");
        executeTarget("test3");

        String test1Inc = getProject().getProperty("test1-inc");
        // Absolute paths, so it should be found
        File ff = new File(test1Inc);
        assertTrue(ff.exists());
        String test1Main = getProject().getProperty("test1-main");
        File ff2 =new File(test1Main);
        assertTrue(ff2.exists());
        
        String test2Inc = getProject().getProperty("test2-inc");
        assertEquals(test2Inc.replace('\\', '/'), "inc/test.i");
        String test2Main = getProject().getProperty("test2-main");
        assertEquals(test2Main.replace('\\', '/'), "src/test.p");

        String test3Main = getProject().getProperty("test3-main");
        assertEquals(test3Main.replace('\\', '/'), "src/foo/bar/test.p");
        String test3Inc1 = getProject().getProperty("test3-inc1");
        assertEquals(test3Inc1.replace('\\', '/'), "src/foo/foo.i");
        String test3Inc2 = getProject().getProperty("test3-inc2");
        assertEquals(test3Inc2.replace('\\', '/'), "inc/foo/bar.i");
    }

    @Test(groups= {"all"})
    public void test30() {
        configureProject("PCTCompile/test30/build.xml");

        executeTarget("test");
        assertTrue(new File("PCTCompile/test30/build/test1.r").exists());
        assertFalse(new File("PCTCompile/test30/build/test2.r").exists());
        assertTrue(new File("PCTCompile/test30/build/test3.r").exists());
        
        expectBuildException("test2", "ZipFileset not supported");
        
        executeTarget("test3");
        assertFalse(new File("PCTCompile/test30/build3/test1.r").exists());
        assertTrue(new File("PCTCompile/test30/build3/test2.r").exists());
        assertFalse(new File("PCTCompile/test30/build3/test3.r").exists());
        
        executeTarget("test4");
        assertEquals(new File("PCTCompile/test30/build4").list().length, 1); // Only .pct
        
        executeTarget("test5");
        assertTrue(new File("PCTCompile/test30/build5/test1.r").exists());
        assertTrue(new File("PCTCompile/test30/build5/test2.r").exists());
        assertTrue(new File("PCTCompile/test30/build5/test3.r").exists());

        executeTarget("test6");
        assertTrue(new File("PCTCompile/test30/build6/test1.r").exists());
        assertFalse(new File("PCTCompile/test30/build6/test2.r").exists());
        assertFalse(new File("PCTCompile/test30/build6/test3.r").exists());
        
        executeTarget("test7");
        assertTrue(new File("PCTCompile/test30/build7/test1.r").exists());
        assertTrue(new File("PCTCompile/test30/build7/test2.r").exists());
        assertTrue(new File("PCTCompile/test30/build7/test3.r").exists());

        executeTarget("test8");
        assertTrue(new File("PCTCompile/test30/build8/test1.r").exists());
        assertTrue(new File("PCTCompile/test30/build8/test2.r").exists());
        assertTrue(new File("PCTCompile/test30/build8/test3.r").exists());
    }

    @Test(groups= {"v10", "v11"})
    public void test31() {
        configureProject("PCTCompile/test31/build.xml");
        executeTarget("test");
        
        assertTrue(new File("PCTCompile/test31/build/test.r").exists());
        assertTrue(new File("PCTCompile/test31/build/eu/rssw/pct/A.r").exists());
        assertTrue(new File("PCTCompile/test31/build/eu/rssw/pct/Z.r").exists());
        // Should test file content (mainly preprocessed output)
    }

    @Test(groups= {"v10", "v11"})
    public void test32() {
        configureProject("PCTCompile/test32/build.xml");
        executeTarget("test");
        
        assertTrue(new File("PCTCompile/test32/build1/.pct/test1.p.strxref").exists());
        assertTrue(new File("PCTCompile/test32/build1/.pct/test2.p.strxref").exists());
        assertTrue(new File("PCTCompile/test32/build2/.pct/strings.xref").exists());
    }

    @Test(groups= {"all"})
    public void test33() {
        configureProject("PCTCompile/test33/build.xml");
        executeTarget("test");

        assertTrue(new File("PCTCompile/test33/build/test1.r").exists());
        assertTrue(new File("PCTCompile/test33/build/test2.r").exists());

        expectBuildException("test2", "Expected failure");
        assertFalse(new File("PCTCompile/test33/build2/test1.r").exists());
        assertFalse(new File("PCTCompile/test33/build2/test2.r").exists());
        assertFalse(new File("PCTCompile/test33/build2/test3.r").exists());
    }

    // Waiting for you Arno !
//    @Test(groups= {"all"})
    public void test34() throws IOException, InvalidRCodeException {
        configureProject("PCTCompile/test34/build.xml");
        executeTarget("test");
        
        File dbg1 = new File("PCTCompile/test34/debugListing/test1.p.dbg");
        File dbg2 = new File("PCTCompile/test34/debugListing/foo/bar/test2.p.dbg");
        File rcode1 = new File("PCTCompile/test34/build/test1.r");
        File rcode2 = new File("PCTCompile/test34/build/foo/bar/test2.r");
        assertTrue(dbg1.exists());
        assertTrue(dbg2.exists());
        assertTrue(rcode1.exists());
        assertTrue(rcode2.exists());
        RCodeInfo r1 = new RCodeInfo(rcode1);
        RCodeInfo r2 = new RCodeInfo(rcode2);
        assertEquals(r1.getDebugListingFile(), "debugListing/test1.p.dbg");
        assertEquals(r2.getDebugListingFile(), "debugListing/foo/bar/test2.p.dbg");
    }

    @Test(groups = {"all"})
    public void test35() throws IOException {
        configureProject("PCTCompile/test35/build.xml");
        executeTarget("test");

        File crc = new File("PCTCompile/test35/build/.pct/test.p.crc");
        assertTrue(crc.exists());
        String line = Files.readFirstLine(crc, Charset.defaultCharset());
        assertTrue(line.startsWith("\"sports2000.Item\""));
    }
}