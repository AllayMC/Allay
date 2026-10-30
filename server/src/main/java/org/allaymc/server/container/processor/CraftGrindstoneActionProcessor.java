package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.eventbus.event.container.GrindstoneTakeResultEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftGrindstoneAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.joml.Vector3d;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 * {@code CraftGrindstoneActionProcessor} builds a grindstone result from the currently
 * opened container and computes an experience proposal from removable enchantments.
 * Same-type inputs merge item data with input precedence and a durability bonus; curse
 * enchantments survive the result's enchantment rebuild.
 *
 * <p>
 * The result event can replace the output, adjust experience, or cancel before either is
 * published. Successful dispatch drops positive experience before staging the created
 * output, while later request actions remain responsible for consumption and transfer.
 * Failures after the experience drop do not restore that earlier effect.
 *
 * @author IWareQ
 */
@Slf4j
public class CraftGrindstoneActionProcessor implements ContainerActionProcessor<CraftGrindstoneAction> {
    /**
     * Builds the proposed result for an opened grindstone and returns an error when both
     * inputs are empty. Enchanted books without curses become ordinary books; same-type
     * inputs merge data with input precedence and a five-percent durability bonus before
     * curse enchantments are restored.
     *
     * <p>
     * The result event controls output and experience before publication. Positive
     * event-adjusted experience drops before created-output assignment, with no rollback
     * if that later assignment fails. Success returns {@code null} without consuming
     * input slots.
     */
    @Override
    public ActionResponse handle(CraftGrindstoneAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var container = player.getOpenedContainer(ContainerTypes.GRINDSTONE);
        if (container == null) {
            log.warn("Received a CraftGrindstoneAction without an opened container!");
            return error();
        }

        var inputItem = container.getInput();
        var additionalItem = container.getAdditional();
        if (inputItem.isEmptyOrAir() && additionalItem.isEmptyOrAir()) {
            log.warn("Input and additional item is empty");
            return error();
        }

        var resultItem = !inputItem.isEmptyOrAir() ? inputItem.copy() : additionalItem.copy();

        if (resultItem.getItemType() == ItemTypes.ENCHANTED_BOOK) {
            var hasCurse = resultItem.getEnchantments().stream().anyMatch(enchantment ->
                    enchantment.getType().isCursed()
            );
            if (!hasCurse) {
                resultItem = ItemTypes.BOOK.createItemStack();
            }
        } else if (inputItem.getItemType() == additionalItem.getItemType()) {
            // Apply input metadata after additional-item metadata so conflicting keys
            // preserve input precedence when constructing the merged grindstone
            // result.
            var inputNbt = inputItem.saveNBT();
            var additionalNbt = additionalItem.saveNBT();

            var mergedNbt = additionalNbt.toBuilder();
            mergedNbt.putAll(inputNbt);
            resultItem.loadExtraTag(mergedNbt.build());

            // The grindstone merge bonus is five percent of maximum durability.
            var maxDamage = resultItem.getMaxDamage();
            var bonusDamage = maxDamage * 5 / 100;

            var mergedDamage = inputItem.getDamage() + additionalItem.getDamage() - bonusDamage;
            resultItem.setDamage(Math.max(0, mergedDamage - maxDamage));
        }

        // Rebuild enchantments from both inputs after clearing the result; only
        // curses survive disenchanting, including curses supplied by the additional
        // item.
        resultItem.removeAllEnchantments();
        for (var enchantment : Stream.concat(inputItem.getEnchantments().stream(), additionalItem.getEnchantments().stream()).toList()) {
            if (enchantment.getType().isCursed()) {
                resultItem.addEnchantment(enchantment.getType(), enchantment.getLevel());
            }
        }

        var xp = calculateExperienceFromEnchantments(inputItem);
        xp += calculateExperienceFromEnchantments(additionalItem);

        var event = new GrindstoneTakeResultEvent(player, container, resultItem, xp);
        if (!event.call()) {
            return error();
        }

        xp = event.getExperienceAmount();

        // Publish experience before staging the output; an exception during the later
        // container assignment does not retract an already spawned experience orb.
        if (xp > 0) {
            var blockPos = container.getBlockPos();
            player.getControlledEntity().getDimension().dropXpOrb(new Vector3d(
                    blockPos.x() + 0.5,
                    blockPos.y() + 1.5,
                    blockPos.z() + 0.5
            ), xp);
        }

        player.getControlledEntity().getContainer(ContainerTypes.CREATED_OUTPUT).setItemStack(0, event.getResultItem(), false);
        return null;
    }

    /**
     * Calculates a random experience proposal from noncurse enchantments. Zero summed
     * minimum modified levels returns zero; otherwise the result ranges from the
     * rounded-up half total through twice that value minus one.
     */
    protected int calculateExperienceFromEnchantments(ItemStack itemStack) {
        int totalCost = 0;
        var enchantments = itemStack.getEnchantments();
        for (var enchantment : enchantments) {
            if (!enchantment.getType().isCursed()) {
                totalCost += enchantment.getType().getMinModifiedLevel(enchantment.getLevel());
            }
        }

        if (totalCost == 0) {
            return 0;
        }

        // Round half the enchantment cost upward before selecting the bounded
        // experience bonus.
        var minExperience = (int) Math.ceil(totalCost / 2d);
        return minExperience + ThreadLocalRandom.current().nextInt(minExperience);
    }

    /**
     * Returns the stack-request action type dispatched to {@code
     * CraftGrindstoneActionProcessor}.
     */
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_REPAIR_AND_DISENCHANT;
    }
}
