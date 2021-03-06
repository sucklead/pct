/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2002-2003 The Apache Software Foundation.  All rights
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

import static org.testng.Assert.assertTrue;

import org.apache.tools.ant.BuildException;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class for testing PCTCRC task
 * 
 * @author <a href="mailto:g.querret+PCT@gmail.com">Gilles QUERRET</a>
 */
public class PCTCRCTest extends BuildFileTestNg {

    @Test(groups= {"v9"}, expectedExceptions = BuildException.class)
    public void test1() {
        configureProject("PCTCRC/test1/build.xml");
        executeTarget("test");
    }

    @Test(groups= {"v9"}, expectedExceptions = BuildException.class)
    public void test2() {
        configureProject("PCTCRC/test2/build.xml");
        executeTarget("test");
    }

    @Test(groups= {"v9"})
    public void test3() {
        configureProject("PCTCRC/test3/build.xml");
        executeTarget("base");
        executeTarget("test");

        File f = new File("PCTCRC/test3/foo/crc.txt");
        assertTrue(f.exists());

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(f));

            String s = br.readLine();
            assertTrue(s.startsWith("test.Tab1"));
        } catch (FileNotFoundException fnfe) {
        } catch (IOException ioe) {
        } finally {
            try {
                br.close();
            } catch (IOException ioe) {
            }
        }
    }

    @Test(groups= {"v9"})
    public void test4() {
        configureProject("PCTCRC/test4/build.xml");
        executeTarget("base");
        executeTarget("test");

        File f = new File("PCTCRC/test4/foo/crc.txt");
        assertTrue(f.exists());

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(f));

            String s = br.readLine();
            assertTrue(s.startsWith("test.Tab1"));
            s = br.readLine();
            assertTrue(s.startsWith("test2.Tab1"));
        } catch (FileNotFoundException fnfe) {
        } catch (IOException ioe) {
        } finally {
            try {
                br.close();
            } catch (IOException ioe) {
            }
        }
    }
}
