package org.allaymc.api.datastruct.dag;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author KocproZ
 * Created 2018-08-14 at 20:02
 */
public class DAGTest {

    public static DAG<String> DAG;

    @BeforeEach
    public void prepare() {
        DAG = new DAG<>();
    }

    @Test
    public void createNodeTest() {
        Node<String> node = DAG.createNode("Node0");
        assertEquals(1, DAG.getNodes().size());
    }

    @Test
    public void edgeTest() {
        DAG.addEdge("par", "chi");
        assertNotNull(DAG.getNode("par"));
        assertNotNull(DAG.getNode("chi"));
        assertEquals(DAG.getNode("par").getChildren().size(), 1);
        assertEquals(DAG.getNode("par").getParents().size(), 0);
        assertEquals(DAG.getNode("chi").getChildren().size(), 0);
        assertEquals(DAG.getNode("chi").getParents().size(), 1);
    }

    @Test
    public void shouldAddAsParent() {
        Node<String> a = DAG.createNode("a");
        Node<String> b = DAG.createNode("b");
        a.addChild(b);
        assertEquals(a.getChildren().size(), 1);
        assertEquals(b.getParents().size(), 1);
    }

    @Test
    public void shouldAddAsChild() {
        Node<String> a = DAG.createNode("a");
        Node<String> b = DAG.createNode("b");
        b.addParent(a);
        assertEquals(a.getChildren().size(), 1);
        assertEquals(b.getParents().size(), 1);
    }

    @Test
    public void shouldFindCycleOnItselfAsParent() {
        Node<String> a = DAG.createNode("Node1");
        assertThrows(CycleFoundException.class, () -> a.addParent(a));
    }

    @Test
    public void shouldFindCycleOnItselfAsChild() {
        Node<String> a = DAG.createNode("Node1");
        assertThrows(CycleFoundException.class, () -> a.addChild(a));
    }

    @Test
    public void shouldFindCycle() {
        DAG.addEdge("a", "b");
        DAG.addEdge("b", "a");
        assertThrows(CycleFoundException.class, () -> DAG.update());
    }

    @Test
    public void shouldNotFindCycle() {
        DAG.createNode("1");
        DAG.update();
    }
}
