/* @java.file.header */

/*  _________        _____ __________________        _____
 *  __  ____/___________(_)______  /__  ____/______ ____(_)_______
 *  _  / __  __  ___/__  / _  __  / _  / __  _  __ `/__  / __  __ \
 *  / /_/ /  _  /    _  /  / /_/ /  / /_/ /  / /_/ / _  /  _  / / /
 *  \____/   /_/     /_/   \_,__/   \____/   \__,_/  /_/   /_/ /_/
 */

package org.gridgain.grid.util.lang;

import org.gridgain.grid.*;
import org.gridgain.grid.lang.*;
import org.gridgain.grid.util.typedef.*;

/**
 * Convenient out-closure subclass that allows for thrown grid exception. This class
 * implements {@link #apply()} method that calls {@link #applyx()} method and properly
 * wraps {@link GridException} into {@link GridClosureException} instance.
 */
public abstract class GridOutClosureX<T> implements GridOutClosure<T> {
    /** */
    private static final long serialVersionUID = 0L;


    /** {@inheritDoc} */
    @Override public T apply() {
        try {
            return applyx();
        }
        catch (GridException e) {
            throw F.wrap(e);
        }
    }

    /**
     * Out-closure body that can throw {@link GridException}.
     *
     * @return Element.
     * @throws GridException Thrown in case of any error condition inside of the closure.
     */
    public abstract T applyx() throws GridException;
}