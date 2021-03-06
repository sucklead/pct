/*
 * Copyright  2000-2004 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.phenix.pct;

import java.io.File;
import java.util.List;

import org.apache.tools.ant.BuildException;

public class WSServerProcess extends PCTRun {
    private boolean webLogError = true;

    public WSServerProcess() {
        super(false, false);
        this.procedure = "web/objects/web-disp.p";
    }

    public void setGraphicalMode(boolean graphMode) {
        throw new BuildException("Unknown attribute");
    }

    public void setDebugPCT(boolean debugPCT) {
        throw new BuildException("Unknown attribute");
    }

    public void setBaseDir(File baseDir) {
        throw new BuildException("Unknown attribute");
    }

    public void setFailOnError(boolean failOnError) {
        throw new BuildException("Unknown attribute");
    }

    public boolean isWebLogError() {
        return webLogError;
    }

    public void setWebLogError(boolean webLogError) {
        this.webLogError = webLogError;
    }

    protected List<String> getCmdLineParameters() {
        List<String> list = super.getCmdLineParameters();

        if (this.webLogError)
            list.add("-weblogerror");
        
        list.add("-p");
        list.add(procedure);
        
        for (PCTConnection conn : getDbConnections()) {
            list.addAll(conn.getConnectParametersList());
        }

        return list;
    }

    /**
     * Get the current propath as a path-separated list
     * 
     * @return String
     */
    public String getPropath() {
        if (this.propath == null)
            return "";

        StringBuffer propathList = new StringBuffer("");
        String[] lst = this.propath.list();
        for (int k = 0; k < lst.length; k++) {
            propathList.append(lst[k]);
            if (k < lst.length - 1)
                propathList.append(File.pathSeparatorChar);
        }
        return propathList.toString();
    }
}
