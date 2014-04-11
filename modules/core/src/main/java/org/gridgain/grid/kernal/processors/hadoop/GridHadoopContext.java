/* @java.file.header */

/*  _________        _____ __________________        _____
 *  __  ____/___________(_)______  /__  ____/______ ____(_)_______
 *  _  / __  __  ___/__  / _  __  / _  / __  _  __ `/__  / __  __ \
 *  / /_/ /  _  /    _  /  / /_/ /  / /_/ /  / /_/ / _  /  _  / / /
 *  \____/   /_/     /_/   \_,__/   \____/   \__,_/  /_/   /_/ /_/
 */

package org.gridgain.grid.kernal.processors.hadoop;

import org.gridgain.grid.kernal.*;
import org.gridgain.grid.kernal.processors.hadoop.jobtracker.*;
import org.gridgain.grid.kernal.processors.hadoop.shuffle.*;
import org.gridgain.grid.kernal.processors.hadoop.taskexecutor.*;

import java.util.*;

/**
 * Hadoop accelerator context.
 */
public class GridHadoopContext {
    /** Kernal context. */
    private GridKernalContext ctx;

    /** Job tracker. */
    private GridHadoopJobTracker jobTracker;

    /** */
    private GridHadoopTaskExecutor taskExecutor;

    /** */
    private GridHadoopShuffle shuffle;

    /** Managers list. */
    private List<GridHadoopManager> mgrs = new ArrayList<>();

    /**
     * @param ctx Kernal context.
     */
    public GridHadoopContext(
        GridKernalContext ctx,
        GridHadoopJobTracker jobTracker,
        GridHadoopTaskExecutor taskExecutor,
        GridHadoopShuffle shuffle
    ) {
        this.ctx = ctx;

        this.jobTracker = add(jobTracker);
        this.taskExecutor = add(taskExecutor);
        this.shuffle = add(shuffle);


    }

    /**
     * Gets list of managers.
     *
     * @return List of managers.
     */
    public List<GridHadoopManager> managers() {
        return mgrs;
    }

    /**
     * Gets kernal context.
     *
     * @return Grid kernal context instance.
     */
    public GridKernalContext kernalContext() {
        return ctx;
    }

    /**
     * @return Jon tracker instance.
     */
    public GridHadoopJobTracker jobTracker() {
        return jobTracker;
    }

    /**
     * @return Task executor.
     */
    public GridHadoopTaskExecutor taskExecutor() {
        return taskExecutor;
    }

    /**
     * @return Shuffle.
     */
    public GridHadoopShuffle shuffle() {
        return shuffle;
    }

    /**
     * Gets local node ID. Shortcut for {@code kernalContext().localNodeId()}.
     *
     * @return Local node ID.
     */
    public UUID localNodeId() {
        return ctx.localNodeId();
    }

    /**
     * @return Rack resolver.
     */
    public GridHadoopRackResolver rackResolver() {
        return null;
    }

    /**
     * @return Block resolver.
     */
    public GridHadoopBlockResolver blockResolver() {
        return null;
    }

    /**
     * @return Map-reduce planner.
     */
    public GridHadoopMapReducePlanner planner() {
        return null;
    }

    /**
     * @return Task factory.
     */
    public GridHadoopTaskFactory taskFactory() {
        return null;
    }

    /**
     * Adds manager to managers list.
     *
     * @param mgr Manager to add.
     * @return Added manager.
     */
    private <T extends GridHadoopManager> T add(T mgr) {
        mgrs.add(mgr);

        return mgr;
    }
}
