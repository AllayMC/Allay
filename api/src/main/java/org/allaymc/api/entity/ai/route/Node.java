package org.allaymc.api.entity.ai.route;

import lombok.Getter;
import lombok.Setter;
import org.joml.Vector3d;
import org.joml.Vector3dc;

/**
 * {@code Node} represents a mutable route waypoint with A* traversal and
 * heuristic costs. Equality and hashing depend only on its coordinates, while
 * ordering uses the sum of its costs; callers must keep coordinates stable
 * while a node belongs to a hash collection.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class Node implements Comparable<Node> {

    /**
     * {@code vector} stores copied waypoint coordinates. The generated getter
     * exposes the mutable vector, so callers must preserve coordinates while
     * hashed collections contain this node.
     */
    protected final Vector3d vector;
    /**
     * Accumulated route traversal cost.
     */
    protected double G;
    /**
     * Estimated remaining route cost.
     */
    protected double H;
    /**
     * Preceding route search node.
     */
    protected Node parent;

    /**
     * Creates a route node by copying the supplied vector coordinates.
     */
    public Node(Vector3dc vector) {
        this.vector = new Vector3d(vector);
    }

    /**
     * Creates a route node at the supplied three world coordinates.
     */
    public Node(double x, double y, double z) {
        this.vector = new Vector3d(x, y, z);
    }

    /**
     * Returns the sum of traversal cost and estimated remaining cost.
     */
    public double getF() {
        return G + H;
    }

    /**
     * Compares this node and another by their total route costs.
     */
    @Override
    public int compareTo(Node o) {
        return Double.compare(this.getF(), o.getF());
    }

    /**
     * Returns whether another node has exactly equal waypoint coordinates.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Node other)) return false;
        return Double.compare(vector.x(), other.vector.x()) == 0
                && Double.compare(vector.y(), other.vector.y()) == 0
                && Double.compare(vector.z(), other.vector.z()) == 0;
    }

    /**
     * Computes a coordinate-based hash consistent with this node's equality
     * checks.
     */
    @Override
    public int hashCode() {
        long bits = Double.doubleToLongBits(vector.x());
        bits = 31 * bits + Double.doubleToLongBits(vector.y());
        bits = 31 * bits + Double.doubleToLongBits(vector.z());
        return Long.hashCode(bits);
    }

    /**
     * Returns diagnostic coordinates together with this node's route cost
     * values.
     */
    @Override
    public String toString() {
        return "Node{" + vector.x() + ", " + vector.y() + ", " + vector.z()
                + ", G=" + G + ", H=" + H + ", F=" + getF() + "}";
    }
}
