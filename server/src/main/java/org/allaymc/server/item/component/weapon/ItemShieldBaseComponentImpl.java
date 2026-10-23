package org.allaymc.server.item.component.weapon;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityWarden;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemShieldBaseComponent;
import org.allaymc.api.item.interfaces.ItemAxeStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.joml.Vector3d;

/**
 * {@code ItemShieldBaseComponentImpl} evaluates directional shield eligibility
 * and supplies the durability and attacker rules used after a block. The
 * eligibility check projects the attacker direction and player view into the
 * horizontal plane, then accepts only a strictly positive computed dot product.
 * The comparison uses no angular tolerance and does not validate finite values
 * before normalization, so floating-point residuals influence boundary cases.
 *
 * <p>
 * The player damage handler owns the surrounding sneaking, cooldown,
 * cancellation, and damage-suppression rules. This component only answers the
 * directional check, requests durability loss for sufficiently large blocked
 * damage, and identifies wardens or inventory-held axes as shield-disabling
 * attackers; these operations do not apply the cooldown themselves.
 *
 * @author daoge_cmd
 */
public class ItemShieldBaseComponentImpl extends ItemBaseComponentImpl implements ItemShieldBaseComponent {

    /**
     * Creates a shield base component from the supplied stack initialization data.
     */
    public ItemShieldBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Returns whether armor-reducible damage has an entity attacker and passes
     * the computed horizontal direction check. The method zeros vertical
     * components, normalizes the attacker and viewing directions, and accepts
     * only {@code dot > 0} from their horizontal dot product.
     *
     * <p>
     * A computed zero or {@code NaN} dot returns {@code false}. Floating-point
     * trigonometric residuals can remain at nominal right angles or vertical
     * viewing angles, so these cases have no exact geometric exclusion
     * guarantee. The method does not mutate {@code damage} or check whether the
     * player is sneaking or on cooldown; the caller owns those rules.
     */
    @Override
    public boolean tryBlockDamage(EntityPlayer player, DamageContainer damage) {
        if (!(damage.getAttacker() instanceof Entity attacker)) {
            return false;
        }

        if (!damage.canBeReducedByArmor()) {
            return false;
        }

        var attackerLoc = attacker.getLocation();
        var playerLoc = player.getLocation();

        // Project both directions horizontally so attacker height does not influence
        // eligibility. The later dot comparison uses no angular tolerance for rounding residuals.
        var toAttacker = new Vector3d(
                attackerLoc.x() - playerLoc.x(),
                0,
                attackerLoc.z() - playerLoc.z()
        ).normalize();

        var viewDir = MathUtils.getDirectionVector(playerLoc);
        viewDir.setComponent(1, 0).normalize();

        double dot = toAttacker.x() * viewDir.x() + toAttacker.z() * viewDir.z();
        return dot > 0;
    }

    /**
     * Requests shield damage when {@code blockedDamage} is at least three points.
     * The request uses {@code (int) blockedDamage + 1}; smaller values do nothing,
     * and inherited Unbreaking and item damage events can still prevent or change
     * the actual durability increase.
     */
    @Override
    public void applyBlockDurability(float blockedDamage) {
        if (blockedDamage >= 3) {
            // A 3.9-point block requests the same four durability points as a
            // 3.0-point block; fractional damage does not round this cost upward.
            int durabilityDamage = (int) blockedDamage + 1;
            tryIncreaseDamage(durabilityDamage);
        }
    }

    /**
     * Returns whether the attacker disables a shield after a successful block.
     * Wardens qualify directly; other attackers need an inventory container whose
     * held item is an {@code ItemAxeStack}. A {@code null} or unsupported attacker
     * returns {@code false}, and this method starts no cooldown itself.
     */
    @Override
    public boolean shouldDisableShield(Object attacker) {
        if (attacker instanceof EntityWarden) {
            return true;
        }

        if (attacker instanceof EntityContainerHolderComponent holder) {
            if (holder.hasContainer(ContainerTypes.INVENTORY)) {
                var weapon = holder.getContainer(ContainerTypes.INVENTORY).getItemInHand();
                return weapon instanceof ItemAxeStack;
            }
        }

        return false;
    }
}
