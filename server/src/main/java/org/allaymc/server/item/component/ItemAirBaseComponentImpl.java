package org.allaymc.server.item.component;

import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.server.component.annotation.OnInitFinish;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * {@code ItemAirBaseComponentImpl} supplies the base state for the shared air item
 * stack. Its predefined initialization uses count {@code 0} and disables network-ID
 * allocation. The initialization callback ignores factory input instead of loading
 * extra tags into the air stack.
 *
 * @author daoge_cmd
 */
public class ItemAirBaseComponentImpl extends ItemBaseComponentImpl {

    /**
     * {@code AIR_TYPE_INIT_INFO} supplies shared zero-count initialization for air.
     * The static initializer assigns {@code AIR} as its type before the constructor
     * passes these values to the base component.
     */
    public static ItemStackInitInfo AIR_TYPE_INIT_INFO =
            ItemStackInitInfo
                    .builder()
                    .assignUniqueId(false)
                    .build();

    // Associate the shared air initialization with AIR before an air base
    // component reads its item type during construction.
    static {
        AIR_TYPE_INIT_INFO.setItemType(AIR);
    }

    /**
     * Creates the air base component from its shared initialization values.
     */
    public ItemAirBaseComponentImpl() {
        super(AIR_TYPE_INIT_INFO);
    }

    /**
     * Leaves the shared air stack unchanged after component injection finishes.
     * The callback ignores {@code initInfo}, including {@code null}, because air
     * uses predefined initialization and does not load factory extra tags.
     */
    @Override
    @OnInitFinish
    public void onInitFinish(ItemStackInitInfo initInfo) {
    }
}
