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
 *
 */


package org.allaymc.server.datastruct.dag;

import com.google.common.base.Preconditions;

import java.util.*;


/**
 * {@code HashDirectedAcyclicGraph} stores graph members in a hash set and tracks direct
 * predecessor and follower sets for precedence queries. A separate root index identifies
 * nodes without predecessors, providing the starting points for topological ordering. Edge
 * insertion checks reachability before changing either adjacency set, and node-level
 * changes update the root index.
 *
 * <p>
 * Sorting copies the node records and adjacency containers before consuming edges, so
 * repeated sorts preserve the graph's recorded relationships. Member objects themselves
 * remain shared. Callers must use stable equality and hash codes and reuse the stored
 * endpoint instances for cycle checks, which compare identity. The inherited hash-set
 * mutation paths are not all connected to adjacency bookkeeping; direct predecessor-set
 * edits, iterator removal, or clearing can leave those representations inconsistent. The
 * graph supplies no concurrent-mutation coordination.
 * @param <B> graph member type
 */
public class HashDirectedAcyclicGraph<B> extends HashSet<B> implements DirectedAcyclicGraph<B> {

    /**
     * {@code nodeMap} and {@code nodePathSet} describe the same members: the map owns
     * adjacency records, while the root set indexes records whose predecessor set is
     * empty.
     */
    private final NodeMap nodeMap; // member-to-adjacency record mapping
    /**
     * Members currently lacking any immediate predecessor.
     */
    private final NodePathSet nodePathSet;


    /**
     * Creates an empty graph with separate adjacency records and root indexing.
     */
    public HashDirectedAcyclicGraph() {
        nodeMap = new NodeMap();
        nodePathSet = new NodePathSet();
    }

    /**
     * Creates a graph containing the supplied members without any precedence edges.
     * Members are added through {@code add}, so duplicates are ignored and {@code null}
     * members are rejected.
     */
    @SafeVarargs
    public HashDirectedAcyclicGraph(B... member) {
        this(Arrays.asList(member));
    }

    /**
     * Creates a graph containing the supplied members without any precedence edges.
     * Members are added through {@code add}, so duplicates are ignored and {@code null}
     * members are rejected.
     */
    public HashDirectedAcyclicGraph(Collection<? extends B> collection) {
        this();
        addAll(collection);
    }


    /**
     * Adds an edge requiring {@code before} to precede {@code after}. Both endpoints must
     * already have adjacency records, and the cycle check runs before either edge set
     * changes. Callers must use the stored endpoint instances because cycle detection
     * compares object identity.
     *
     * <p>
     * The two adjacency updates describe the same directed edge from opposite endpoints.
     * Updating the follower's predecessor set also removes that follower from the root
     * index when necessary. The operation is not synchronized, so callers must prevent
     * concurrent queries or mutations from observing its intermediate state.
     */
    public void setBefore(B before, B after) throws DAGCycleException {
        Preconditions.checkArgument(nodeMap.containsKey(after), "The \"after\" node is not in the DAG graph.");
        Preconditions.checkArgument(nodeMap.containsKey(before), "The \"before\" node is not in the DAG graph.");
        assertNoLoop(before, after);
        nodeMap.get(before).addAfter(after);
        nodeMap.get(after).addBefore(before);
    }

    /**
     * Returns the live immediate-predecessor set for a recorded member. An absent member
     * throws {@code IllegalArgumentException}; callers must leave the returned set
     * unchanged because direct edits bypass reciprocal edges and root-index updates.
     */
    public Set<B> getBefore(B member) {
        Preconditions.checkArgument(nodeMap.containsKey(member), "The \"member\" node is not in the DAG graph.");
        return nodeMap.get(member).getBeforeSet();
    }

    /**
     * Returns whether one or more recorded follower edges lead from {@code before} to
     * {@code after}. Both endpoints must exist; absent endpoints throw
     * {@code IllegalArgumentException}, and the traversal assumes an acyclic graph.
     */
    public boolean isBefore(B before, B after) {
        Preconditions.checkArgument(nodeMap.containsKey(before), "The \"before\" node is not in the DAG graph.");
        Preconditions.checkArgument(nodeMap.containsKey(after), "The \"after\" node is not in the DAG graph.");
        return isBeforeWorker(before, after);
    }

    /**
     * Returns a topological ordering computed from copied adjacency records. Independent
     * nodes follow the iteration order of hash-backed root and follower sets; the original
     * graph's recorded edges are not consumed by sorting.
     */
    public List<B> getSortedList() {
        return new TopologicalSort(nodeMap).sort();
    }

    /**
     * Adds a non-null member with empty adjacency sets when not already present. The new
     * node joins the root index before insertion completes; duplicate members return
     * {@code false} without replacing their existing relationships.
     */
    @Override
    public boolean add(B member) {
        Preconditions.checkNotNull(member);

        if (!contains(member)) {
            return (nodeMap.put(member, new Node(member)) == null) & super.add(member);
        }

        return false;
    }

    /**
     * Removes a present member and its reciprocal adjacency references. The returned
     * boolean is the result of removing that member from the root index, so a non-root
     * removal can return {@code false} after membership changed.
     */
    @Override
    public boolean remove(Object member) {
        if (contains(member)) {
            if ((nodeMap.remove(member) != null) & super.remove(member)) {
                return this.nodePathSet.remove(member);
            } else return false;
        }

        return false;
    }

    /**
     * Returns whether follower traversal reaches {@code after} from the supplied node. The
     * traversal assumes every referenced follower has an adjacency record and the graph
     * has no cycles; it does not maintain a visited set.
     */
    private boolean isBeforeWorker(B before, final B after) {
        Set<B> followers = nodeMap.get(before).getAfterSet();

        if (followers.contains(after)) {
            return true;
        } else {
            for (B follower : followers) {
                if (isBeforeWorker(follower, after)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Rejects a path that would return from {@code after} to the proposed {@code before}
     * endpoint. The search recursively follows direct followers, and an identical endpoint
     * object throws {@code DAGCycleException}.
     *
     * <p>
     * This method must finish before the proposed edge is recorded, otherwise the new edge
     * would participate in its own validation. It compares endpoint identity rather than
     * equality, so callers must pass the stored member objects consistently. The traversal
     * assumes existing edges are acyclic and does not independently track visited nodes.
     */
    private void assertNoLoop(B before, B after) throws DAGCycleException {
        if (before != after) {
            // Existing leaves terminate the search naturally; checking descendants first
            // prevents a proposed edge from closing a path back to before.
            for (B follower : nodeMap.get(after).getAfterSet()) {
                assertNoLoop(before, follower);
            }

            return;
        }

        throw new DAGCycleException(before, after);
    }


    /**
     * {@code Node} stores one member and its immediate predecessor and follower sets.
     * Copies duplicate the adjacency containers but continue to share the member objects.
     */
    protected class Node {

        /**
         * Member represented by this adjacency record.
         */
        B self;
        // afterSet and beforeSet hold reciprocal direct edges. Mutators refresh the outer root
        // index whenever predecessor membership can change.
        /**
         * Members immediately following this node.
         */
        private Set<B> afterSet;
        /**
         * Members immediately preceding this node.
         */
        private Set<B> beforeSet;


        /**
         * Creates a node copy with independently mutable adjacency containers.
         * {@code self} and the member references in both sets remain shared, and copying
         * does not notify the original graph's root index.
         */
        protected Node(Node node) {
            beforeSet = new HashSet<>(node.beforeSet);
            afterSet = new HashSet<>(node.afterSet);
            self = node.self;
        }

        /**
         * Creates an uninitialized node without adjacency sets or root registration.
         */
        Node() {
        }

        /**
         * Creates an isolated node for {@code member} and registers it as a root. The
         * predecessor and follower sets start empty, and {@code notifyChange()} updates
         * the owning graph's root index.
         */
        Node(B member) {
            beforeSet = new HashSet<>();
            afterSet = new HashSet<>();
            self = member;
            notifyChange();
        }


        /**
         * Returns the live follower set stored in this record. Internal traversal can read
         * it directly, but mutations must preserve reciprocal edges and refresh the owning
         * graph's root index where required.
         */
        public Set<B> getAfterSet() {
            return afterSet;
        }

        /**
         * Returns the live predecessor set stored in this record. Internal traversal can
         * read it directly, but mutations must preserve reciprocal edges and refresh the
         * owning graph's root index where required.
         */
        public Set<B> getBeforeSet() {
            return beforeSet;
        }

        /**
         * Returns the graph member represented by this adjacency record.
         */
        public B getSelf() {
            return self;
        }

        /**
         * Adds one direct follower relationship and refreshes root indexing when
         * membership changes. The caller must update the other endpoint separately because
         * this method changes only one adjacency set.
         */
        public boolean addAfter(B member) {
            boolean result = afterSet.add(member);

            if (result) {
                notifyChange();
            }

            return result;
        }

        /**
         * Adds one direct predecessor relationship and refreshes root indexing when
         * membership changes. The caller must update the other endpoint separately because
         * this method changes only one adjacency set.
         */
        public boolean addBefore(B member) {
            boolean result = beforeSet.add(member);

            if (result) {
                notifyChange();
            }

            return result;
        }

        /**
         * Removes one direct follower relationship and refreshes root indexing when
         * membership changes. The caller must update the other endpoint separately because
         * this method changes only one adjacency set.
         */
        public boolean removeAfter(B member) {
            boolean result = afterSet.remove(member);

            if (result) {
                notifyChange();
            }

            return result;
        }

        /**
         * Removes one direct predecessor relationship and refreshes root indexing when
         * membership changes. The caller must update the other endpoint separately because
         * this method changes only one adjacency set.
         */
        public boolean removeBefore(B member) {
            boolean result = beforeSet.remove(member);

            if (result) {
                notifyChange();
            }

            return result;
        }

        /**
         * Refreshes this node's root membership from its current predecessor set.
         */
        private void notifyChange() {
            nodePathSet.update(this);
        }
    }

    /**
     * {@code NodeMap} maps graph members to their adjacency records and removes reciprocal
     * references when deleting a recorded member.
     */
    protected class NodeMap extends HashMap<B, Node> {

        /**
         * Returns the adjacency record mapped to the supplied graph member key.
         */
        @Override
        public Node get(Object key) {
            return super.get(key);
        }

        /**
         * Removes reciprocal references to the supplied key before deleting its record.
         * The key must already exist; predecessor and follower mutators refresh root
         * indexing as their adjacency sets change.
         */
        @Override
        public Node remove(Object key) {
            Node member = get(key);

            // Remove both reciprocal edge directions before discarding the record, while its
            // predecessor and follower sets still identify the affected neighbors.
            for (B before : member.getBeforeSet()) {
                get(before).removeAfter((B) key);
            }

            for (B after : member.getAfterSet()) {
                get(after).removeBefore((B) key);
            }

            return super.remove(key);
        }
    }

    /**
     * {@code NodePathSet} indexes members whose immediate predecessor sets are empty,
     * providing the initial roots for topological traversal.
     */
    protected class NodePathSet extends HashSet<B> {

        /**
         * Creates the initially empty index of members without immediate predecessors.
         */
        NodePathSet() {
        }


        /**
         * Synchronizes this node's root membership with its immediate-predecessor set. An
         * empty set adds {@code node.self}, while any predecessor removes it; callers
         * invoke this after node-level adjacency changes.
         */
        public void update(Node node) {
            if (node.getBeforeSet().isEmpty()) {
                add(node.self);
            } else {
                remove(node.self);
            }
        }
    }

    /**
     * {@code TopologicalSort} holds copied adjacency records consumed during one
     * topological traversal, preserving the owning graph's original edge containers.
     */
    protected class TopologicalSort extends HashMap<B, Node> {

        /**
         * Creates copied adjacency records for a topological traversal. Each node copy
         * duplicates its predecessor and follower sets so consuming edges while sorting
         * leaves the graph's original edge containers available.
         */
        TopologicalSort(NodeMap nodeMap) {
            for (B member : nodeMap.keySet()) {
                // Sorter edge removal must operate on copied containers so later graph queries
                // and repeated sorts retain the original relationships.
                put(member, new Node(nodeMap.get(member)));
            }
        }


        /**
         * Returns a topological ordering seeded from the owning graph's current roots. The
         * worker consumes this sorter's copied adjacency records, so a sorter instance
         * should be used for one traversal only.
         */
        List<B> sort() {
            return sort(nodePathSet);
        }

        /**
         * Returns a topological ordering by consuming copied edges from the supplied
         * roots. A follower becomes ready only after all of its copied predecessors have
         * been removed, ensuring earlier output satisfies its recorded dependencies.
         *
         * <p>
         * The work list copies the root set before traversal, and only this sorter's
         * adjacency containers are mutated. Nodes with no reachable ready root never enter
         * the result, so callers must preserve a consistent acyclic graph and root index.
         * Independent nodes retain no guaranteed relative order because the source sets
         * are hash-backed.
         */
        List<B> sort(NodePathSet nodePathSet) {
            List<B> result = new ArrayList<>();
            List<B> initialNodes = new LinkedList<>(nodePathSet);

            while (!initialNodes.isEmpty()) {
                B initialNode = initialNodes.removeFirst();
                result.add(initialNode);

                Iterator<B> iterator = get(initialNode).getAfterSet().iterator();

                while (iterator.hasNext()) {
                    B after = iterator.next();
                    // Consume both copied directions before checking readiness, so a follower
                    // is scheduled only after its final dependency has been removed.
                    iterator.remove();
                    get(after).getBeforeSet().remove(initialNode);

                    if (get(after).getBeforeSet().isEmpty()) {
                        initialNodes.add(after);
                    }
                }
            }

            return result;
        }
    }
}
