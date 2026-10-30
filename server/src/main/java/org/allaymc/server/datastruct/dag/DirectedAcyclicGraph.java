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
 * {@code DirectedAcyclicGraph} models set members with directed precedence relationships
 * and topological ordering. Members must be added before relationships are established.
 * Immediate predecessor queries and transitive reachability queries answer different
 * questions, while sorted output places every predecessor before the members that depend
 * on it.
 * @param <B> graph member type
 */
public interface DirectedAcyclicGraph<B> extends Set<B> {

    /**
     * Adds a precedence edge from {@code before} to {@code after}. Both endpoints must
     * already belong to the graph; an edge that closes a cycle throws
     * {@code DAGCycleException} instead of establishing that relationship.
     */
    void setBefore(B before, B after) throws DAGCycleException;

    /**
     * Returns the immediate predecessors recorded for {@code member}. The member must
     * already belong to the graph; this result does not include every transitively
     * reachable predecessor unless that predecessor also has a direct edge.
     */
    Set<B> getBefore(B member);

    /**
     * Returns whether {@code after} is reachable from {@code before} through one or more
     * precedence edges. Both endpoints must already belong to the graph; a member does not
     * precede itself in an acyclic graph.
     */
    boolean isBefore(B before, B after);

    /**
     * Returns a new list of graph members in topological order. Independent members can
     * appear in any order, while every established precedence edge places its before
     * endpoint earlier than its after endpoint.
     */
    List<B> getSortedList();

    /**
     * Adds a non-null member without establishing any precedence edges. An already present
     * member returns {@code false}, while {@code null} is rejected with
     * {@code NullPointerException} instead of joining the graph.
     */
    boolean add(B member);
}
