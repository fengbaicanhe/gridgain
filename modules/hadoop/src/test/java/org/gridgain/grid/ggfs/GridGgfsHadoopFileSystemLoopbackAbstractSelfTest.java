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

package org.gridgain.grid.ggfs;

import java.util.*;

import static org.gridgain.grid.util.ipc.shmem.GridIpcSharedMemoryServerEndpoint.*;

/**
 * GGFS Hadoop file system IPC loopback self test.
 */
public abstract class GridGgfsHadoopFileSystemLoopbackAbstractSelfTest extends
    GridGgfsHadoopFileSystemAbstractSelfTest {
    /**
     * Constructor.
     *
     * @param mode GGFS mode.
     * @param skipEmbed Skip embedded mode flag.
     */
    protected GridGgfsHadoopFileSystemLoopbackAbstractSelfTest(GridGgfsMode mode, boolean skipEmbed) {
        super(mode, skipEmbed, true);
    }

    /** {@inheritDoc} */
    @Override protected Map<String, String> primaryIpcEndpointConfiguration(final String gridName) {
        return new HashMap<String, String>() {{
            put("type", "tcp");
            put("port", String.valueOf(DFLT_IPC_PORT + getTestGridIndex(gridName)));
        }};
    }
}
