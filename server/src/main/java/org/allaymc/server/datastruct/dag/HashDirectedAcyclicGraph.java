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
 * HashDirectedAcyclicGraph is an implementation of a <a href="http://en.wikipedia.org/wiki/Directed_acyclic_graph">directed acyclic graph</a>
 * that conforms to {@link Set}.
 *
 * @param <B> Node type
 */
public class HashDirectedAcyclicGraph<B> extends HashSet<B> implements DirectedAcyclicGraph<B> {
    //~ Instance fields ========================================================

    private final NodeMap nodeMap;
    private final NodePathSet nodePathSet;

    //~ Constructors ===========================================================

    /**
     * Creates a new DAG object.
     */
    public HashDirectedAcyclicGraph() {
        nodeMap = new NodeMap();
        nodePathSet = new NodePathSet();
    }

    /**
     * Creates a new DAG object, which initially contains the members specified
     * its parameter list.
     *
     * @param member list of members, which may be empty, which are initially contained by the DAG object
     */
    @SafeVarargs
    public HashDirectedAcyclicGraph(B... member) {
        this(Arrays.asList(member));
    }

    /**
     * Creates a new DAG object, which initially contains the members specified
     * its parameter list.
     *
     * @param collection collection of members, which may be empty, which are initially contained by the DAG object
     */
    public HashDirectedAcyclicGraph(Collection<? extends B> collection) {
        this();
        addAll(collection);
    }

    //~ Methods ================================================================

    public void setBefore(B before, B after) throws DAGCycleException {
        Preconditions.checkArgument(nodeMap.containsKey(after), "The \"after\" node is not in the DAG graph.");
        Preconditions.checkArgument(nodeMap.containsKey(before), "The \"before\" node is not in the DAG graph.");
        assertNoLoop(before, after);
        nodeMap.get(before).addAfter(after);
        nodeMap.get(after).addBefore(before);
    }

    public Set<B> getBefore(B member) {
        Preconditions.checkArgument(nodeMap.containsKey(member), "The \"member\" node is not in the DAG graph.");
        return nodeMap.get(member).getBeforeSet();
    }

    public boolean isBefore(B before, B after) {
        Preconditions.checkArgument(nodeMap.containsKey(before), "The \"before\" node is not in the DAG graph.");
        Preconditions.checkArgument(nodeMap.containsKey(after), "The \"after\" node is not in the DAG graph.");
        return isBeforeWorker(before, after);
    }

    public List<B> getSortedList() {
        return new TopologicalSort(nodeMap).sort();
    }

    @Override
    public boolean add(B member) {
        Preconditions.checkNotNull(member);

        if (!contains(member)) {
            return (nodeMap.put(member, new Node(member)) == null) & super.add(member);
        }

        return false;
    }

    @Override
    public boolean remove(Object member) {
        if (contains(member)) {
            if ((nodeMap.remove(member) != null) & super.remove(member)) {
                return this.nodePathSet.remove(member);
            } else return false;
        }

        return false;
    }

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

    private void assertNoLoop(B before, B after) throws DAGCycleException {
        if (before != after) {
            // recursion terminates when afterSet.isEmpty(), i.e. the following loop
            // has zero iterations
            for (B follower : nodeMap.get(after).getAfterSet()) {
                assertNoLoop(before, follower);
            }

            return;
        }

        throw new DAGCycleException(before, after);
    }

    //~ Inner Classes ==========================================================

    /**
     * Node contains the beforeSet and afterSet of a set member, i.e.
     * the set of other members which are immediate predecessors of the
     * member, and the set of other members which are immediate followers of
     * the member.
     */
    protected class Node {
        //~ Instance fields ====================================================

        B self;
        private Set<B> afterSet;
        private Set<B> beforeSet;

        //~ Constructors =======================================================

        /**
         * Make a copy of an initialNode
         *
         * @param node
         */
        protected Node(Node node) {
            beforeSet = node.beforeSet;
            afterSet = node.afterSet;
            self = node.self;
        }

        Node() {
        }

        Node(B member) {
            beforeSet = new HashSet<>();
            afterSet = new HashSet<>();
            self = member;
            notifyChange();
        }

        //~ Methods ============================================================

        public Set<B> getAfterSet() {
            return afterSet;
        }

        public Set<B> getBeforeSet() {
            return beforeSet;
        }

        public B getSelf() {
            return self;
        }

        public boolean addAfter(B member) {
            boolean result = afterSet.add(member);

            if (result) {
                notifyChange();
            }

            return result;
        }

        public boolean addBefore(B member) {
            boolean result = beforeSet.add(member);

            if (result) {
                notifyChange();
            }

            return result;
        }

        public boolean removeAfter(B member) {
            boolean result = afterSet.remove(member);

            if (result) {
                notifyChange();
            }

            return result;
        }

        public boolean removeBefore(B member) {
            boolean result = beforeSet.remove(member);

            if (result) {
                notifyChange();
            }

            return result;
        }

        private void notifyChange() {
            nodePathSet.update(this);
        }
    }

    /**
     * NodeMap contains all the members of the PartiallyOrderedSetX.
     */
    protected class NodeMap extends HashMap<B, Node> {
        //~ Methods ============================================================

        @Override
        public Node get(Object key) {
            return super.get(key);
        }

        @Override
        public Node remove(Object key) {
            Node member = get(key);

            // remove any references to this key
            // remove it from afterSet of antecedents
            for (B before : member.getBeforeSet()) {
                get(before).removeAfter((B) key);
            }

            // remove it from the beforeSet of followers
            for (B after : member.getAfterSet()) {
                get(after).removeBefore((B) key);
            }

            return super.remove(key);
        }
    }

    /**
     * NodePathSet contains all elements of DAG where
     * beforeSet.isEmpty(), i.e. the first elements of all the sublists of
     * DAG.
     */
    protected class NodePathSet extends HashSet<B> {
        //~ Constructors =======================================================

        NodePathSet() {
        }

        //~ Methods ============================================================

        public void update(Node node) {
            if (node.getBeforeSet().isEmpty()) {
                add(node.self);
            } else {
                remove(node.self);
            }
        }
    }

    /**
     * <a href="https://en.wikipedia.org/wiki/Topological_sorting">Topological_sorting</a>
     */
    protected class TopologicalSort extends HashMap<B, Node> {
        //~ Constructors =======================================================

        TopologicalSort(NodeMap nodeMap) {
            for (B member : nodeMap.keySet()) {
                // create a copy to preserve the user's original
                put(member, new Node(nodeMap.get(member)));
            }
        }

        //~ Methods ============================================================

        List<B> sort() {
            return sort(nodePathSet);
        }

        List<B> sort(NodePathSet nodePathSet) {
            List<B> result = new ArrayList<>();
            List<B> initialNodes = new LinkedList<>(nodePathSet);

            while (!initialNodes.isEmpty()) {
                B initialNode = initialNodes.removeFirst();
                result.add(initialNode);

                Iterator<B> iterator = get(initialNode).getAfterSet().iterator();

                while (iterator.hasNext()) {
                    B after = iterator.next();
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