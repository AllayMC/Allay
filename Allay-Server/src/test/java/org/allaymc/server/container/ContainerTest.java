package org.allaymc.server.container;

import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.allaymc.api.item.interfaces.ItemAirStack.AIR_STACK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Allay Project 2023/10/14
 *
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
        container.addOnOpenListener(viewer -> {
            openFlag.set(true);
        });
        container.onOpen(null);
        assertTrue(openFlag.get());

        // Close listener
        AtomicBoolean closeFlag = new AtomicBoolean(false);
        container.addOnCloseListener(viewer -> {
            closeFlag.set(true);
        });
        container.onClose(null);
        assertTrue(closeFlag.get());
    }
}
