/*
 * Copyright 2009 Stephen Winnall, CH-8143 Stallikon. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "Licence");
 * you may not use this file except in compliance with the Licence.
 * You may obtain a copy of the Licence at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 *
 */

/*
 * All matters arising in law from the use of this software are subject
 * exclusively to the jurisdiction and venue of the courts located in
 * Zurich, Switzerland. See
 *
 *	http://www.vimia.org/licences/JURISDICTION-1.0
 */
package org.allaymc.server.datastruct.dag;

import java.util.List;
import java.util.Set;

/**
 * DirectedAcyclicGraph is an implementation of a <a href="http://en.wikipedia.org/wiki/Directed_acyclic_graph">directed acyclic graph</a>
 * that conforms to {@link Set}.
 *
 * @param <B> Node type.
 */
public interface DirectedAcyclicGraph<B> extends Set<B> {

    /**
     * set the parent node of the "after" node as the "before" node.
     * <p>
     * Note that before set, you need to call the {@link Set#add(Object)} method to add nodes.
     * <p>
     * If the node specified in the parameter is not in the graph, it will throw an {@link IllegalArgumentException}
     *
     * @param before the before node
     * @param after  the after node
     *
     * @throws DAGCycleException if a cycle occurs, this exception will be thrown
     */
    void setBefore(B before, B after) throws DAGCycleException;

    /**
     * If the node specified in the parameter is not in the graph, it will throw an {@link IllegalArgumentException}
     *
     * @param member a node
     *
     * @return all parent nodes of this node. Note that if this node is not in the graph, an {@link NullPointerException} will be thrown.
     */
    Set<B> getBefore(B member);

    /**
     * If the node specified in the parameter is not in the graph, it will throw an {@link IllegalArgumentException}
     *
     * @param before the before node
     * @param after  the after node
     *
     * @return Whether the "before" node is a parent node of the "after" node.
     */
    boolean isBefore(B before, B after);

    /**
     * @return The result of topological sorting of this directed acyclic graph (DAG).
     */
    List<B> getSortedList();

    /**
     * If the node specified in the parameter is null, it will throw an {@link NullPointerException}
     */
    boolean add(B member);
}
