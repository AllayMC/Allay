package org.allaymc.api.entity.ai.route;

import lombok.Getter;
import lombok.Setter;
import org.joml.Vector3d;
import org.joml.Vector3dc;

/**
 * A node in a pathfinding route with A* cost fields.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class Node implements Comparable<Node> {

    protected final Vector3d vector;
    protected double G;
    protected double H;
    protected Node parent;

    public Node(Vector3dc vector) {
        this.vector = new Vector3d(vector);
    }

    public Node(double x, double y, double z) {
        this.vector = new Vector3d(x, y, z);
    }

    public double getF() {
        return G + H;
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(this.getF(), o.getF());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Node other)) return false;
        return Double.compare(vector.x(), other.vector.x()) == 0
                && Double.compare(vector.y(), other.vector.y()) == 0
                && Double.compare(vector.z(), other.vector.z()) == 0;
    }

    @Override
    public int hashCode() {
        long bits = Double.doubleToLongBits(vector.x());
        bits = 31 * bits + Double.doubleToLongBits(vector.y());
        bits = 31 * bits + Double.doubleToLongBits(vector.z());
        return (int) (bits ^ (bits >>> 32));
    }

    @Override
    public String toString() {
        return "Node{" + vector.x() + ", " + vector.y() + ", " + vector.z()
                + ", G=" + G + ", H=" + H + ", F=" + getF() + "}";
    }
}
