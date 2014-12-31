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

/**
 * Interface which has to be implement to reflect programs used by PCT depending on Progress
 * version.
 * 
 * @author <a href="mailto:g.querret+PCT@gmail.com">Gilles QUERRET</a>
 * @version $Revision$
 */
public abstract interface ProgressProcedures {
    String getCompileProcedure();
    String getIncrementalProcedure();
    String getDumpUsersProcedure();
    String getLoadUsersProcedure();
    String getLoadSchemaProcedure();
    String getLoadSingleTableDataProcedure();
    String getLoadMultipleTablesDataProcedure();
    boolean needRedirector();
    String getInitString();
    String getCallbackString();
    String getConnectString();
    String getAliasString();
    String getDBAliasString();
    String getPropathString();
    String getSuperInitString();
    String getRunString();
    String getReturnProc();
    String getParameterString();
    String getOutputParameterDeclaration();
    String getOutputParameterProc();
    String getAfterRun();
    String getOutputParameterCall();
    String getQuit();
}
