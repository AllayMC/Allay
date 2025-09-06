package org.allaymc.server.container;

import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.allaymc.api.item.interfaces.ItemAirStack.AIR_STACK;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class ContainerTest {
    static FullContainerType<Container> testContainerType = FullContainerType
            .builder()
            .size(36)
            // Here may throw an exception if we implemented lab table in the future
            .mapAllSlotToType(ContainerSlotType.LAB_TABLE_INPUT)
            .build();
    static BaseContainer container = new BaseContainer(testContainerType);
    static ContainerViewer viewer = new FakeContainerViewer();

    @Test
    void testEmptySlotPlaceHolder() {
        assertEquals(0, AIR_STACK.getCount());
        assertEquals(0, AIR_STACK.getStackNetworkId());
    }

    @Test
    void testContainerEmptySlot() {
        for (var item : container.getItemStacks()) {
            assertEquals(AIR_STACK, item);
        }
    }

    @Test
    void testContainerOpenAndCloseListener() {
        // Open listener
        AtomicBoolean openFlag = new AtomicBoolean(false);
        container.addOpenListener(viewer -> {
            openFlag.set(true);
        });
        container.addViewer(viewer);
        assertTrue(openFlag.get());

        // Close listener
        AtomicBoolean closeFlag = new AtomicBoolean(false);
        container.addCloseListener(viewer -> {
            closeFlag.set(true);
        });
        container.removeViewer(viewer);
        assertTrue(closeFlag.get());
    }

    @Test
    void testContainerSlotListener() {
        AtomicBoolean slotFlag = new AtomicBoolean(false);
        container.addSlotChangeListener(0, newItem -> {
            slotFlag.set(true);
        });
        container.addSlotChangeListener(1, newItem -> {
            slotFlag.set(false);
        });
        container.setItemStack(0, AIR_STACK);
        assertTrue(slotFlag.get());

        container.setItemStack(1, AIR_STACK);
        assertFalse(slotFlag.get());

        // No listener on slot 2
        container.setItemStack(2, AIR_STACK);
        assertFalse(slotFlag.get());
    }

    @Test
    void testContainerException() {
        assertThrows(NullPointerException.class, () -> {
            container.setItemStack(0, null);
        });
    }
}
