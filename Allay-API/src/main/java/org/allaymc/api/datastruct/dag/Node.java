package org.allaymc.api.datastruct.dag;

import lombok.Getter;

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

    private final List<Node<T>> parents;
    private final List<Node<T>> children;
    @Getter
    private final T object;

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

    List<Node<T>> getParents() {
        return parents;
    }

    List<Node<T>> getChildren() {
        return children;
    }

    @SafeVarargs
    public final void addParents(Node<T>... parents) {
        for (Node<T> n : parents) {
            addParent(n);
        }
    }

    public void addParent(Node<T> parent) {
        if (parent == this) throw new CycleFoundException(this + "->" + this);
        parents.add(parent);
        if (parent.getChildren().contains(this)) return;
        parent.addChild(this);
    }

    public void addChild(Node<T> child) {
        if (child == this) throw new CycleFoundException(this + "->" + this);
        children.add(child);
        if (child.getParents().contains(this)) return;
        child.addParent(this);
    }

    @Override
    public String toString() {
        return "Node{" +
                "object=" + object.toString() +
                ", parents=" + parents.size() +
                ", children=" + children.size() +
                '}';
    }
}
