package cn.allay.server.container;

import cn.allay.api.container.BaseContainer;
import cn.allay.api.container.Container;
import cn.allay.api.container.FullContainerType;
import cn.allay.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.atomic.AtomicBoolean;

import static cn.allay.api.container.Container.EMPTY_SLOT_PLACE_HOLDER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class ContainerTest {

    FullContainerType<Container> testContainerType = FullContainerType
            .builder()
            .size(36)
            // Here may throw an exception if we implemented lab table in the future
            .mapAllSlotToType(ContainerSlotType.LAB_TABLE_INPUT)
            .build();

    BaseContainer container = new BaseContainer(testContainerType);

    @Test
    void testEmptySlotPlaceHolder() {
        assertEquals(0, EMPTY_SLOT_PLACE_HOLDER.getCount());
        assertEquals(0, EMPTY_SLOT_PLACE_HOLDER.getStackNetworkId());
    }

    @Test
    void testContainerEmptySlot() {
        for (var item : container.getItemStacks()) {
            assertEquals(EMPTY_SLOT_PLACE_HOLDER, item);
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
