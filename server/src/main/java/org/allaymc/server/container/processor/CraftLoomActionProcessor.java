package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BannerPattern;
import org.allaymc.api.block.data.BannerPatternType;
import org.allaymc.api.block.data.BannerType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.eventbus.event.container.LoomTakeResultEvent;
import org.allaymc.api.item.component.ItemBannerBaseComponent;
import org.allaymc.server.item.data.ItemId;
import org.allaymc.api.player.Player;
import org.allaymc.api.utils.DyeColor;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftLoomAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.Map;

/**
 * Handles loom crafting actions for applying patterns to banners.
 *
 * @author daoge_cmd
 */
@Slf4j
public class CraftLoomActionProcessor implements ContainerActionProcessor<CraftLoomAction> {

    private static final int MAX_BANNER_PATTERNS = 6;

    @Override
    public ActionResponse handle(CraftLoomAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var container = player.getOpenedContainer(ContainerTypes.LOOM);
        if (container == null) {
            log.warn("Received a CraftLoomAction without an opened loom container!");
            return error();
        }

        var timesCrafted = action.getTimesCrafted();
        if (timesCrafted < 1) {
            log.warn("Times crafted must be at least 1");
            return error();
        }

        var inputBanner = container.getInput();
        var dyeItem = container.getDye();
        var materialItem = container.getMaterial();

        // Validate input banner
        if (inputBanner.isEmptyOrAir()) {
            log.warn("Loom input banner is empty");
            return error();
        }

        if (inputBanner.getCount() < timesCrafted) {
            log.warn("Input banner count ({}) is less than times crafted ({})", inputBanner.getCount(), timesCrafted);
            return error();
        }

        // Validate dye
        if (dyeItem.isEmptyOrAir()) {
            log.warn("Loom dye is empty");
            return error();
        }

        if (dyeItem.getCount() < timesCrafted) {
            log.warn("Dye item count ({}) is less than times crafted ({})", dyeItem.getCount(), timesCrafted);
            return error();
        }

        // Validate banner has banner component
        if (!(inputBanner instanceof ItemBannerBaseComponent bannerComponent)) {
            log.warn("Input item is not a banner");
            return error();
        }

        // Check if this is an ominous banner (cannot be patterned)
        if (bannerComponent.getBannerType() == BannerType.OMINOUS) {
            log.warn("Cannot apply patterns to an ominous banner");
            return error();
        }

        // Check pattern limit
        if (bannerComponent.getPatternCount() >= MAX_BANNER_PATTERNS) {
            log.warn("Banner already has maximum number of patterns ({})", MAX_BANNER_PATTERNS);
            return error();
        }

        // Extract dye color from dye item
        var dyeItemId = ItemId.fromIdentifier(dyeItem.getItemType().getIdentifier());
        DyeColor dyeColor = getDyeColorFromItem(dyeItemId);
        if (dyeColor == null) {
            log.warn("Invalid dye item in loom: {}", dyeItem.getItemType().getIdentifier());
            return error();
        }

        // Get pattern type from action
        var patternId = action.getPatternId();
        var patternType = BannerPatternType.fromId(patternId);
        if (patternType == null) {
            log.warn("Invalid pattern type: {}", patternId);
            return error();
        }

        // Validate pattern item requirement
        var requiredPatternItem = patternType.getRequiredPatternItem();
        if (requiredPatternItem != null) {
            // Special pattern - requires pattern item in material slot
            if (materialItem.isEmptyOrAir() || materialItem.getItemType() != requiredPatternItem) {
                log.warn("Pattern {} requires pattern item {}, but material slot contains: {}",
                        patternType, requiredPatternItem.getIdentifier(),
                        materialItem.isEmptyOrAir() ? "nothing" : materialItem.getItemType().getIdentifier());
                return error();
            }
        }

        // Create result banner with count = timesCrafted
        var resultBanner = inputBanner.copy();
        if (!(resultBanner instanceof ItemBannerBaseComponent resultBannerComponent)) {
            // This should never happen since we already validated the input
            log.error("Result banner is not a banner item - this should never happen");
            return error();
        }

        // Add new pattern and set result count
        resultBannerComponent.addPattern(new BannerPattern(patternType, dyeColor));
        resultBanner.setCount(timesCrafted);

        // Fire event
        var event = new LoomTakeResultEvent(player, container, resultBanner);
        if (!event.call()) {
            return error();
        }

        // Place result in created output container
        player.getControlledEntity().getContainer(ContainerTypes.CREATED_OUTPUT)
                .setItemStack(0, event.getResultItem(), false);

        return null;
    }

    /**
     * Gets the dye color from a dye item ID.
     *
     * @param itemId the item ID
     * @return the dye color, or null if not a valid dye
     */
    private DyeColor getDyeColorFromItem(ItemId itemId) {
        if (itemId == null) {
            return null;
        }
        return switch (itemId) {
            case WHITE_DYE -> DyeColor.WHITE;
            case ORANGE_DYE -> DyeColor.ORANGE;
            case MAGENTA_DYE -> DyeColor.MAGENTA;
            case LIGHT_BLUE_DYE -> DyeColor.LIGHT_BLUE;
            case YELLOW_DYE -> DyeColor.YELLOW;
            case LIME_DYE -> DyeColor.LIME;
            case PINK_DYE -> DyeColor.PINK;
            case GRAY_DYE -> DyeColor.GRAY;
            case LIGHT_GRAY_DYE -> DyeColor.LIGHT_GRAY;
            case CYAN_DYE -> DyeColor.CYAN;
            case PURPLE_DYE -> DyeColor.PURPLE;
            case BLUE_DYE -> DyeColor.BLUE;
            case BROWN_DYE -> DyeColor.BROWN;
            case GREEN_DYE -> DyeColor.GREEN;
            case RED_DYE -> DyeColor.RED;
            case BLACK_DYE -> DyeColor.BLACK;
            default -> null;
        };
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_LOOM;
    }
}
