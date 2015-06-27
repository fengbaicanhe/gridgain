/* 
 Copyright (C) GridGain Systems. All Rights Reserved.
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

/*  _________        _____ __________________        _____
 *  __  ____/___________(_)______  /__  ____/______ ____(_)_______
 *  _  / __  __  ___/__  / _  __  / _  / __  _  __ `/__  / __  __ \
 *  / /_/ /  _  /    _  /  / /_/ /  / /_/ /  / /_/ / _  /  _  / / /
 *  \____/   /_/     /_/   \_,__/   \____/   \__,_/  /_/   /_/ /_/
 */

package org.gridgain.grid.kernal.processors.hadoop.taskexecutor.external;

import org.gridgain.grid.hadoop.*;
import org.gridgain.grid.kernal.processors.hadoop.message.*;
import org.gridgain.grid.util.tostring.*;
import org.gridgain.grid.util.typedef.internal.*;

import java.io.*;

/**
 * Child process initialization request.
 */
public class GridHadoopPrepareForJobRequest implements GridHadoopMessage {
    /** */
    private static final long serialVersionUID = 0L;

    /** Job ID. */
    @GridToStringInclude
    private GridHadoopJobId jobId;

    /** Job info. */
    @GridToStringInclude
    private GridHadoopJobInfo jobInfo;

    /** Total amount of reducers in the job. */
    @GridToStringInclude
    private int totalReducersCnt;

    /** Reducers to be executed on current node. */
    @GridToStringInclude
    private int[] locReducers;

    /**
     * Constructor required by {@link Externalizable}.
     */
    public GridHadoopPrepareForJobRequest() {
        // No-op.
    }

    /**
     * @param jobId Job ID.
     * @param jobInfo Job info.
     * @param totalReducersCnt Number of reducers in the job.
     * @param locReducers Reducers to be executed on current node.
     */
    public GridHadoopPrepareForJobRequest(GridHadoopJobId jobId, GridHadoopJobInfo jobInfo, int totalReducersCnt,
        int[] locReducers) {
        assert jobId != null;

        this.jobId = jobId;
        this.jobInfo = jobInfo;
        this.totalReducersCnt = totalReducersCnt;
        this.locReducers = locReducers;
    }

    /**
     * @return Job info.
     */
    public GridHadoopJobInfo jobInfo() {
        return jobInfo;
    }

    /**
     * @return Job ID.
     */
    public GridHadoopJobId jobId() {
        return jobId;
    }

    /**
     * @return Reducers to be executed on current node.
     */
    public int[] localReducers() {
        return locReducers;
    }

    /**
     * @return Number of reducers in job.
     */
    public int totalReducerCount() {
        return totalReducersCnt;
    }

    /** {@inheritDoc} */
    @Override public void writeExternal(ObjectOutput out) throws IOException {
        jobId.writeExternal(out);

        out.writeObject(jobInfo);
        out.writeInt(totalReducersCnt);

        U.writeIntArray(out, locReducers);
    }

    /** {@inheritDoc} */
    @Override public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        jobId = new GridHadoopJobId();
        jobId.readExternal(in);

        jobInfo = (GridHadoopJobInfo)in.readObject();
        totalReducersCnt = in.readInt();

        locReducers = U.readIntArray(in);
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return S.toString(GridHadoopPrepareForJobRequest.class, this);
    }
}
