package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BannerPattern;
import org.allaymc.api.block.data.BannerPatternType;
import org.allaymc.api.block.data.BannerType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.eventbus.event.container.LoomTakeResultEvent;
import org.allaymc.api.item.component.ItemBannerBaseComponent;
import org.allaymc.api.item.component.ItemDyeComponent;
import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftLoomAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.Map;

/**
 * {@code CraftLoomActionProcessor} prepares patterned banner output for an opened loom.
 * The processor checks available banner and dye counts, banner eligibility, the pattern
 * limit, and any required pattern item before staging the listener-approved result;
 * subsequent request actions consume ingredients.
 *
 * @author daoge_cmd
 */
@Slf4j
public class CraftLoomActionProcessor implements ContainerActionProcessor<CraftLoomAction> {

    /**
     * {@code MAX_BANNER_PATTERNS} limits existing patterns before another is applied.
     * The processor rejects a banner already at this count rather than extending its
     * pattern list.
     */
    private static final int MAX_BANNER_PATTERNS = 6;

    /**
     * Prepares a banner result for the positive requested craft count after validating
     * loom inputs and the selected pattern. The result event can replace or cancel the
     * proposed output; success stages it with ordinary viewer sending suppressed and
     * returns {@code null} without consuming ingredient slots.
     */
    @Override
    public ActionResponse handle(CraftLoomAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var container = player.getOpenedContainer(ContainerTypes.LOOM);
        if (container == null) {
            log.warn("Received a CraftLoomAction without an opened loom container!");
            return error();
        }

        var timesCrafted = action.timesCrafted();
        if (timesCrafted < 1) {
            log.warn("Times crafted must be at least 1");
            return error();
        }

        var inputBanner = container.getInput();
        var dyeItem = container.getDye();
        var materialItem = container.getMaterial();

        if (inputBanner.isEmptyOrAir()) {
            log.warn("Loom input banner is empty");
            return error();
        }

        if (inputBanner.getCount() < timesCrafted) {
            log.warn("Input banner count ({}) is less than times crafted ({})", inputBanner.getCount(), timesCrafted);
            return error();
        }

        if (dyeItem.isEmptyOrAir()) {
            log.warn("Loom dye is empty");
            return error();
        }

        if (dyeItem.getCount() < timesCrafted) {
            log.warn("Dye item count ({}) is less than times crafted ({})", dyeItem.getCount(), timesCrafted);
            return error();
        }

        if (!(inputBanner instanceof ItemBannerBaseComponent bannerComponent)) {
            log.warn("Input item is not a banner");
            return error();
        }

        if (bannerComponent.getBannerType() == BannerType.OMINOUS) {
            log.warn("Cannot apply patterns to an ominous banner");
            return error();
        }

        if (bannerComponent.getPatternCount() >= MAX_BANNER_PATTERNS) {
            log.warn("Banner already has maximum number of patterns ({})", MAX_BANNER_PATTERNS);
            return error();
        }

        if (!(dyeItem instanceof ItemDyeComponent dyeComponent)) {
            log.warn("Invalid dye item in loom: {}", dyeItem.getItemType().getIdentifier());
            return error();
        }
        var dyeColor = dyeComponent.getDyeColor();

        var patternId = action.patternId();
        var patternType = BannerPatternType.fromId(patternId);
        if (patternType == null) {
            log.warn("Invalid pattern type: {}", patternId);
            return error();
        }

        var requiredPatternItem = patternType.getRequiredPatternItem();
        if (requiredPatternItem != null) {
            if (materialItem.isEmptyOrAir() || materialItem.getItemType() != requiredPatternItem) {
                log.warn("Pattern {} requires pattern item {}, but material slot contains: {}",
                        patternType, requiredPatternItem.getIdentifier(),
                        materialItem.isEmptyOrAir() ? "nothing" : materialItem.getItemType().getIdentifier());
                return error();
            }
        }

        var resultBanner = inputBanner.copy();
        if (!(resultBanner instanceof ItemBannerBaseComponent resultBannerComponent)) {
            // Copying is delegated to the item implementation; reject an unexpected
            // missing banner component before applying a pattern to the result.
            log.error("Result banner is not a banner item - this should never happen");
            return error();
        }

        resultBannerComponent.addPattern(new BannerPattern(patternType, dyeColor));
        resultBanner.setCount(timesCrafted);

        var event = new LoomTakeResultEvent(player, container, resultBanner);
        if (!event.call()) {
            return error();
        }

        player.getControlledEntity().getContainer(ContainerTypes.CREATED_OUTPUT)
                .setItemStack(0, event.getResultItem(), false);

        return null;
    }

    /**
     * Returns the stack-request action type dispatched to {@code
     * CraftLoomActionProcessor}.
     */
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_LOOM;
    }
}
