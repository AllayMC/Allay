package org.allaymc.server.item.component;

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
import org.joml.Vector3d;

/**
 * Implementation of the shield item component.
 * <p>
 * Handles shield blocking mechanics including:
 * <ul>
 *   <li>Directional blocking using dot product calculation</li>
 *   <li>Shield durability consumption after blocking</li>
 *   <li>Axe shield-breaking detection</li>
 * </ul>
 *
 * @author daoge_cmd
 */
public class ItemShieldBaseComponentImpl extends ItemBaseComponentImpl implements ItemShieldBaseComponent {

    public ItemShieldBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean tryBlockDamage(EntityPlayer player, DamageContainer damage) {
        // Must have a valid entity attacker
        if (!(damage.getAttacker() instanceof Entity attacker)) {
            return false;
        }

        // Damage must be reducible by armor (excludes magic, void, etc.)
        if (!damage.canBeReducedByArmor()) {
            return false;
        }

        // Calculate directional blocking using dot product
        var attackerLoc = attacker.getLocation();
        var playerLoc = player.getLocation();

        // Vector from player to attacker (normalized, ignoring Y)
        var toAttacker = new Vector3d(
                attackerLoc.x() - playerLoc.x(),
                0,
                attackerLoc.z() - playerLoc.z()
        ).normalize();

        // Player's view direction (normalized, ignoring Y)
        var viewDir = MathUtils.getDirectionVector(playerLoc);
        viewDir.setComponent(1, 0).normalize();

        // Dot product calculation:
        // - toAttacker points FROM player TO attacker
        // - viewDir points in the direction the player is looking
        // - When player faces attacker: both vectors point similar direction -> dot > 0
        // - When attacker is behind player: vectors point opposite directions -> dot < 0
        // Attack can be blocked if it comes from the front (dot > 0)
        double dot = toAttacker.x() * viewDir.x() + toAttacker.z() * viewDir.z();
        return dot > 0;
    }

    @Override
    public void applyBlockDurability(float blockedDamage) {
        // Following Minecraft mechanics:
        // If blocked damage >= 3: durability loss = (int)damage + 1
        // If blocked damage < 3: no durability loss
        if (blockedDamage >= 3) {
            int durabilityDamage = (int) blockedDamage + 1;
            tryIncreaseDamage(durabilityDamage);
        }
    }

    @Override
    public boolean shouldDisableShield(Object attacker) {
        // Warden always disables shields regardless of weapon
        if (attacker instanceof EntityWarden) {
            return true;
        }

        // Check if attacker is an entity holding an axe
        if (attacker instanceof EntityContainerHolderComponent holder) {
            if (holder.hasContainer(ContainerTypes.INVENTORY)) {
                var weapon = holder.getContainer(ContainerTypes.INVENTORY).getItemInHand();
                return weapon instanceof ItemAxeStack;
            }
        }

        return false;
    }
}
