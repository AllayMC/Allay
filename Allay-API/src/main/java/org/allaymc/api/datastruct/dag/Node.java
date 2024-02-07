package org.allaymc.api.datastruct.dag;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Parent --> Child
 *
 * @author KocproZ
 * Created 2018-08-14 at 10:35
 */
public class Node<T> {

    private List<Node<T>> parents;
    private List<Node<T>> children;
    private T object;

    protected Node(T object) {
        this.object = object;
        parents = new LinkedList<>();
        children = new LinkedList<>();
    }

    /**
     * Performs Depth-first search on children and executes lambda on every node
     *
     * @param consumer lambda to be executed on nodes
     */
    public void visit(Consumer<Node<T>> consumer) {
        Set<Node<T>> visited = new HashSet<>();
        consumer.accept(this);
        for (Node<T> node : children) {
            consumer.accept(node);
            node.getChildren().forEach(n -> n.visit(consumer, visited));
        }
    }

    void visit(Consumer<Node<T>> consumer, Set<Node<T>> visited) {
        consumer.accept(this);
        visited.add(this);
        for (Node<T> node : children) {
            if (visited.contains(node)) continue;
            node.visit(consumer, visited);
        }
    }

    public T getObject() {
        return object;
    }

    List<Node<T>> getParents() {
        return parents;
    }

    List<Node<T>> getChildren() {
        return children;
    }

    public void addParents(Node<T>... par) {
        for (Node<T> n : par) {
            addParent(n);
        }
    }

    public void addParent(Node<T> parent) {
        if (parent == this) throw new CycleFoundException(this.toString() + "->" + this.toString());
        parents.add(parent);
        if (parent.getChildren().contains(this)) return;
        parent.addChild(this);
    }

    public void addChild(Node<T> child) {
        if (child == this) throw new CycleFoundException(this.toString() + "->" + this.toString());
        children.add(child);
        if (child.getParents().contains(this)) return;
        child.addParent(this);
    }

    @Override
    public String toString() {
        return "Node{" +
                "object=" + object.toString() +
//                ", parents=" + parents.size() +
//                ", children=" + children.size() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node)
            return object.equals(((Node<T>) obj).getObject());
        else return false;
    }
}
