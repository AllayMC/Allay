package org.allaymc.server.entity.component.item;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPaintingBaseComponent;
import org.allaymc.api.entity.data.PaintingType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.particle.BlockBreakParticle;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.component.event.CEntityAfterDamageEvent;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * {@code EntityPaintingBaseComponentImpl} represents a painting motive, its
 * oriented bounds, and damage-driven removal. New paintings use the kebab
 * motive, persisted titles select restored motives, and damage normally drops
 * a painting item unless the recorded attacker is a creative player.
 *
 * @author PMMP Team | daoge_cmd
 */
public class EntityPaintingBaseComponentImpl extends EntityBaseComponentImpl implements EntityPaintingBaseComponent {

    /**
     * {@code TAG_MOTIVE} names the persisted painting title used to restore
     * motive selection from entity data.
     */
    protected static final String TAG_MOTIVE = "Motive";

    /**
     * Injected recorded damage state component.
     */
    @Dependency
    protected EntityLivingComponent livingComponent;

    /**
     * {@code paintingType} holds the selected motive. Unknown persisted titles
     * can set it to {@code null}, although explicit setter calls reject that
     * value.
     */
    @Getter
    protected PaintingType paintingType;

    /**
     * Creates a painting with {@code PaintingType.KEBAB} as its initial
     * motive. Inherited initialization may subsequently load a stored title,
     * including a title that cannot resolve to a known motive.
     */
    public EntityPaintingBaseComponentImpl(EntityInitInfo info) {
        super(info);
        this.paintingType = PaintingType.KEBAB;
    }

    /**
     * Replaces the retained motive after rejecting {@code null}. This setter
     * does not broadcast state, and callers supplying {@code null} receive a
     * {@code NullPointerException} before the previous motive selection
     * changes.
     */
    @Override
    public void setPaintingType(PaintingType paintingType) {
        Preconditions.checkNotNull(paintingType, "PaintingType cannot be null");
        this.paintingType = paintingType;
    }

    /**
     * Returns motive bounds oriented to the current horizontal face. A missing
     * motive returns a zero-sized box, while a nonhorizontal face can fail in
     * the motive bounding-box calculation.
     */
    @Override
    public AABBdc getBaseAABB() {
        if (this.paintingType == null) {
            // Unknown persisted motives have no dimensions, so collision queries
            // receive an empty box until a valid motive is assigned.
            return new AABBd(0, 0, 0, 0, 0, 0);
        }

        return this.paintingType.getAABB(getHorizontalFace());
    }

    /**
     * Loads inherited entity data and resolves a present motive title. An
     * unknown title sets {@code paintingType} to {@code null}, while a missing
     * tag leaves the current selection unchanged.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForString(TAG_MOTIVE, value -> this.paintingType = PaintingType.fromTitle(value));
    }

    /**
     * Returns inherited entity data with the selected motive title. The motive
     * must be nonnull because serialization accesses its title directly,
     * including after loading an unknown persisted title.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putString(TAG_MOTIVE, this.paintingType.getTitle())
                .build();
    }

    /**
     * Removes a spawned painting after emitting break particles and any
     * permitted item drop. A creative player recorded as the last attacker
     * suppresses the item, and the handler assumes recorded damage exists.
     */
    @EventHandler
    protected void onDamage(CEntityAfterDamageEvent event) {
        if (!isSpawned()) {
            return;
        }

        var drops = true;
        var lastDamage = this.livingComponent.getLastDamage();
        if (lastDamage.getAttacker() instanceof EntityPlayer player &&
            player.getGameMode() == GameMode.CREATIVE) {
            drops = false;
        }

        var dimension = getDimension();
        dimension.addParticle(this.location.add(0.5, 0.5, 0.5, new Vector3d()), new BlockBreakParticle(BlockTypes.OAK_PLANKS.getDefaultState()));
        if (drops) {
            dimension.dropItem(ItemTypes.PAINTING.createItemStack(), this.location);
        }
        remove();
    }
}
