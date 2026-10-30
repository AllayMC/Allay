package org.allaymc.server.blockentity.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.server.blockentity.component.*;
import org.allaymc.server.blockentity.component.campfire.BlockEntityCampfireBaseComponentImpl;
import org.allaymc.server.blockentity.component.campfire.BlockEntitySoulCampfireBaseComponentImpl;
import org.allaymc.server.blockentity.component.furnace.BlockEntityBlastFurnaceBaseComponentImpl;
import org.allaymc.server.blockentity.component.furnace.BlockEntityFurnaceBaseComponentImpl;
import org.allaymc.server.blockentity.component.furnace.BlockEntitySmokerFurnaceBaseComponentImpl;
import org.allaymc.server.blockentity.component.piston.BlockEntityMovingBlockBaseComponentImpl;
import org.allaymc.server.blockentity.component.piston.BlockEntityPistonArmBaseComponentImpl;
import org.allaymc.server.blockentity.component.shulkerbox.BlockEntityShulkerBoxBaseComponentImpl;
import org.allaymc.server.blockentity.component.shulkerbox.BlockEntityShulkerBoxContainerHolderComponentImpl;
import org.allaymc.server.blockentity.data.BlockEntityId;
import org.allaymc.server.blockentity.impl.*;
import org.allaymc.server.container.impl.*;

/**
 * {@code BlockEntityTypeInitializer} registers built-in block-entity types and
 * publishes their API factory fields. Each initializer selects an
 * implementation class, persistence name, and component providers, and the type
 * builder registers the result before the field assignment. A mutable
 * block-entity registry must already exist. Registry population discovers these
 * entry points reflectively, and repeated calls replace mappings rather than
 * rejecting duplicates.
 *
 * <p>
 * Some related blocks share an implementation class while retaining distinct
 * persistence names. Ordinary and soul campfires also share the
 * {@code "Campfire"} persistence name: the second registration replaces
 * name-based lookup, while {@code BlockEntityTypes.CAMPFIRE} and
 * {@code BlockEntityTypes.SOUL_CAMPFIRE} keep their separate type objects.
 * Multi-type initializers publish incrementally, so a later failure leaves the
 * earlier registry and API-field assignments from that invocation in place.
 *
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class BlockEntityTypeInitializer {
    /**
     * Registers the banner factory and publishes its built-in API reference. A
     * mutable block-entity registry is required; the banner base provider is
     * retained for subsequent instance construction.
     */
    public static void initBanner() {
        BlockEntityTypes.BANNER = AllayBlockEntityType
                .builder(BlockEntityBannerImpl.class)
                .name(BlockEntityId.BANNER)
                .addComponent(BlockEntityBannerBaseComponentImpl::new, BlockEntityBannerBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the command-block factory with its specialized base component.
     * Publication stores the built type in
     * {@code BlockEntityTypes.COMMAND_BLOCK} after registry registration; a
     * failure leaves the prior field value intact.
     */
    public static void initCommandBlock() {
        BlockEntityTypes.COMMAND_BLOCK = AllayBlockEntityType
                .builder(BlockEntityCommandBlockImpl.class)
                .name(BlockEntityId.COMMAND_BLOCK)
                .addComponent(BlockEntityCommandBlockBaseComponentImpl::new, BlockEntityCommandBlockBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the jukebox factory with its specialized base component. The
     * type captures the provider for later construction and replaces any
     * existing registry mapping under the {@code "Jukebox"} persistence name.
     */
    public static void initJukebox() {
        BlockEntityTypes.JUKEBOX = AllayBlockEntityType
                .builder(BlockEntityJukeboxImpl.class)
                .name(BlockEntityId.JUKEBOX)
                .addComponent(BlockEntityJukeboxBaseComponentImpl::new, BlockEntityJukeboxBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers a barrel factory combining barrel behavior with an inventory
     * holder. Each supplied holder constructs a {@code BarrelContainerImpl},
     * and the completed type is published through the
     * {@code BlockEntityTypes.BARREL} API field.
     */
    public static void initBarrel() {
        BlockEntityTypes.BARREL = AllayBlockEntityType
                .builder(BlockEntityBarrelImpl.class)
                .name(BlockEntityId.BARREL)
                .addComponent(BlockEntityBarrelBaseComponentImpl::new, BlockEntityBarrelBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(BarrelContainerImpl::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }

    /**
     * Registers a hopper factory combining transfer behavior with an inventory
     * holder. The holder creates {@code HopperContainerImpl} instances, while
     * the hopper base component supplies the block entity's specialized
     * behavior.
     */
    public static void initHopper() {
        BlockEntityTypes.HOPPER = AllayBlockEntityType
                .builder(BlockEntityHopperImpl.class)
                .name(BlockEntityId.HOPPER)
                .addComponent(BlockEntityHopperBaseComponentImpl::new, BlockEntityHopperBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(HopperContainerImpl::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }

    /**
     * Registers a dispenser factory with its base behavior and container
     * holder. The holder constructs a {@code DispenserContainerImpl} for each
     * component instance; successful type registration precedes publication of
     * the {@code BlockEntityTypes.DISPENSER} API reference.
     */
    public static void initDispenser() {
        BlockEntityTypes.DISPENSER = AllayBlockEntityType
                .builder(BlockEntityDispenserImpl.class)
                .name(BlockEntityId.DISPENSER)
                .addComponent(BlockEntityDispenserBaseComponentImpl::new, BlockEntityDispenserBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(DispenserContainerImpl::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }

    /**
     * Registers a dropper factory with its base behavior and container holder.
     * The holder constructs a {@code DropperContainerImpl} for each component
     * instance, keeping its inventory selection separate from dispenser
     * construction.
     */
    public static void initDropper() {
        BlockEntityTypes.DROPPER = AllayBlockEntityType
                .builder(BlockEntityDropperImpl.class)
                .name(BlockEntityId.DROPPER)
                .addComponent(BlockEntityDropperBaseComponentImpl::new, BlockEntityDropperBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(DropperContainerImpl::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }

    /**
     * Registers the chest factory with base, container-holder, and pairing
     * components. The type captures all three providers for construction,
     * allowing chest inventory state and paired-block relationships to be
     * injected together.
     */
    public static void initChest() {
        BlockEntityTypes.CHEST = AllayBlockEntityType
                .builder(BlockEntityChestImpl.class)
                .name(BlockEntityId.CHEST)
                .addComponent(BlockEntityChestBaseComponentImpl::new, BlockEntityChestBaseComponentImpl.class)
                .addComponent(BlockEntityChestContainerHolderComponentImpl::new, BlockEntityChestContainerHolderComponentImpl.class)
                .addComponent(BlockEntityPairableComponentImpl::new, BlockEntityPairableComponentImpl.class)
                .build();
    }

    /**
     * Registers the shulker-box factory with its specialized base and container
     * holder. Both providers are retained for later construction; successful
     * registry publication precedes assignment of the
     * {@code BlockEntityTypes.SHULKER_BOX} API field.
     */
    public static void initShulkerBox() {
        BlockEntityTypes.SHULKER_BOX = AllayBlockEntityType
                .builder(BlockEntityShulkerBoxImpl.class)
                .name(BlockEntityId.SHULKER_BOX)
                .addComponent(BlockEntityShulkerBoxBaseComponentImpl::new, BlockEntityShulkerBoxBaseComponentImpl.class)
                .addComponent(BlockEntityShulkerBoxContainerHolderComponentImpl::new, BlockEntityShulkerBoxContainerHolderComponentImpl.class)
                .build();
    }

    /**
     * Registers ordinary, blast-furnace, and smoker factories using the shared
     * furnace implementation class. Each type selects its own base behavior and
     * container. Earlier registrations and API assignments remain if a later
     * build fails.
     */
    public static void initFurnace() {
        BlockEntityTypes.FURNACE = AllayBlockEntityType
                .builder(BlockEntityFurnaceImpl.class)
                .name(BlockEntityId.FURNACE)
                .addComponent(BlockEntityFurnaceBaseComponentImpl::new, BlockEntityFurnaceBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(FurnaceContainerImpl::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
        BlockEntityTypes.BLAST_FURNACE = AllayBlockEntityType
                .builder(BlockEntityFurnaceImpl.class)
                .name(BlockEntityId.BLAST_FURNACE)
                .addComponent(BlockEntityBlastFurnaceBaseComponentImpl::new, BlockEntityBlastFurnaceBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(BlastFurnaceContainerImpl::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
        BlockEntityTypes.SMOKER = AllayBlockEntityType
                .builder(BlockEntityFurnaceImpl.class)
                .name(BlockEntityId.SMOKER)
                .addComponent(BlockEntitySmokerFurnaceBaseComponentImpl::new, BlockEntitySmokerFurnaceBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(SmokerContainerImpl::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }

    /**
     * Registers standing and hanging sign types through the same sign
     * implementation. They use separate persistence names with the same base
     * provider; a failed second build does not undo the ordinary sign's
     * publication.
     */
    public static void initSign() {
        BlockEntityTypes.SIGN = AllayBlockEntityType
                .builder(BlockEntitySignImpl.class)
                .name(BlockEntityId.SIGN)
                .addComponent(BlockEntitySignBaseComponentImpl::new, BlockEntitySignBaseComponentImpl.class)
                .build();
        BlockEntityTypes.HANGING_SIGN = AllayBlockEntityType
                .builder(BlockEntitySignImpl.class)
                .name(BlockEntityId.HANGING_SIGN)
                .addComponent(BlockEntitySignBaseComponentImpl::new, BlockEntitySignBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the enchanting-table factory with its specialized base
     * component. The completed type is published through
     * {@code BlockEntityTypes.ENCHANT_TABLE} after registration under
     * {@code "EnchantTable"}, replacing a prior same-name mapping if one
     * exists.
     */
    public static void initEnchantTable() {
        BlockEntityTypes.ENCHANT_TABLE = AllayBlockEntityType
                .builder(BlockEntityEnchantTableImpl.class)
                .name(BlockEntityId.ENCHANT_TABLE)
                .addComponent(BlockEntityEnchantTableBaseComponentImpl::new, BlockEntityEnchantTableBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the beacon factory with its specialized base component. The
     * builder captures the provider for later instance creation and publishes
     * the resulting type through the {@code BlockEntityTypes.BEACON} API
     * reference.
     */
    public static void initBeacon() {
        BlockEntityTypes.BEACON = AllayBlockEntityType
                .builder(BlockEntityBeaconImpl.class)
                .name(BlockEntityId.BEACON)
                .addComponent(BlockEntityBeaconBaseComponentImpl::new, BlockEntityBeaconBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the brewing-stand factory with brewing behavior and a container
     * holder. The holder creates {@code BrewingStandContainerImpl} instances,
     * and successful registry registration precedes assignment of the
     * {@code BlockEntityTypes.BREWING_STAND} API reference.
     */
    public static void initBrewingStand() {
        BlockEntityTypes.BREWING_STAND = AllayBlockEntityType
                .builder(BlockEntityBrewingStandImpl.class)
                .name(BlockEntityId.BREWING_STAND)
                .addComponent(BlockEntityBrewingStandBaseComponentImpl::new, BlockEntityBrewingStandBaseComponentImpl.class)
                .addComponent(() -> new BlockEntityContainerHolderComponentImpl(BrewingStandContainerImpl::new), BlockEntityContainerHolderComponentImpl.class)
                .build();
    }

    /**
     * Registers the ender-chest factory with its specialized base component.
     * The resulting type is published under {@code "EnderChest"} and through
     * {@code BlockEntityTypes.ENDER_CHEST}; failures before assignment retain
     * the previous API field value.
     */
    public static void initEnderChest() {
        BlockEntityTypes.ENDER_CHEST = AllayBlockEntityType
                .builder(BlockEntityEnderChestImpl.class)
                .name(BlockEntityId.ENDER_CHEST)
                .addComponent(BlockEntityEnderChestBaseComponentImpl::new, BlockEntityEnderChestBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the flower-pot factory with its specialized base component.
     * Provider configuration is captured by the built type, and the
     * {@code BlockEntityTypes.FLOWER_POT} API field is assigned only after
     * registry registration succeeds.
     */
    public static void initFlowerPot() {
        BlockEntityTypes.FLOWER_POT = AllayBlockEntityType
                .builder(BlockEntityFlowerPotImpl.class)
                .name(BlockEntityId.FLOWER_POT)
                .addComponent(BlockEntityFlowerPotBaseComponentImpl::new, BlockEntityFlowerPotBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers ordinary and glow item frames through the same implementation
     * class. Both use the item-frame base provider but distinct persistence
     * names; failure during the glow build leaves ordinary-frame publication
     * intact.
     */
    public static void initItemFrame() {
        BlockEntityTypes.ITEM_FRAME = AllayBlockEntityType
                .builder(BlockEntityItemFrameImpl.class)
                .name(BlockEntityId.ITEM_FRAME)
                .addComponent(BlockEntityItemFrameBaseComponentImpl::new, BlockEntityItemFrameBaseComponentImpl.class)
                .build();
        BlockEntityTypes.GLOW_ITEM_FRAME = AllayBlockEntityType
                .builder(BlockEntityItemFrameImpl.class)
                .name(BlockEntityId.GLOW_ITEM_FRAME)
                .addComponent(BlockEntityItemFrameBaseComponentImpl::new, BlockEntityItemFrameBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the bed factory with its specialized base component. The
     * resulting type replaces any {@code "Bed"} registry mapping and is then
     * assigned to the built-in {@code BlockEntityTypes.BED} API reference for
     * callers.
     */
    public static void initBed() {
        BlockEntityTypes.BED = AllayBlockEntityType
                .builder(BlockEntityBedImpl.class)
                .name(BlockEntityId.BED)
                .addComponent(BlockEntityBedBaseComponentImpl::new, BlockEntityBedBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the bell factory with its specialized base component. The built
     * type retains its provider for later instances and is published through
     * {@code BlockEntityTypes.BELL} only after registration under the
     * {@code "Bell"} name.
     */
    public static void initBell() {
        BlockEntityTypes.BELL = AllayBlockEntityType
                .builder(BlockEntityBellImpl.class)
                .name(BlockEntityId.BELL)
                .addComponent(BlockEntityBellBaseComponentImpl::new, BlockEntityBellBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the lectern factory with its specialized base component. The
     * {@code BlockEntityTypes.LECTERN} API reference receives the built type
     * after registration, so a failed build leaves any earlier reference
     * unchanged.
     */
    public static void initLectern() {
        BlockEntityTypes.LECTERN = AllayBlockEntityType
                .builder(BlockEntityLecternImpl.class)
                .name(BlockEntityId.LECTERN)
                .addComponent(BlockEntityLecternBaseComponentImpl::new, BlockEntityLecternBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the note-block factory under Bedrock's {@code "Music"}
     * persistence identifier. The API field remains named
     * {@code BlockEntityTypes.NOTEBLOCK}, while stored NBT and registry lookup
     * use {@code "Music"} rather than the Java capability name.
     */
    public static void initNoteblock() {
        BlockEntityTypes.NOTEBLOCK = AllayBlockEntityType
                .builder(BlockEntityNoteblockImpl.class)
                .name(BlockEntityId.MUSIC)
                .addComponent(BlockEntityNoteblockBaseComponentImpl::new, BlockEntityNoteblockBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the head factory under Bedrock's {@code "Skull"} persistence
     * identifier. The API field remains named {@code BlockEntityTypes.HEAD},
     * while stored NBT and registry lookup use {@code "Skull"} rather than the
     * Java capability name.
     */
    public static void initHead() {
        BlockEntityTypes.HEAD = AllayBlockEntityType
                .builder(BlockEntityHeadImpl.class)
                .name(BlockEntityId.SKULL)
                .addComponent(BlockEntityHeadBaseComponentImpl::new, BlockEntityHeadBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the comparator factory with its specialized base component. The
     * resulting type captures that provider and replaces a same-name registry
     * mapping before the {@code BlockEntityTypes.COMPARATOR} API field is
     * assigned.
     */
    public static void initComparator() {
        BlockEntityTypes.COMPARATOR = AllayBlockEntityType
                .builder(BlockEntityComparatorImpl.class)
                .name(BlockEntityId.COMPARATOR)
                .addComponent(BlockEntityComparatorBaseComponentImpl::new, BlockEntityComparatorBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the target factory with its specialized base component.
     * Successful registration under {@code "Target"} precedes assignment to
     * {@code BlockEntityTypes.TARGET}, leaving the earlier field value intact
     * if construction setup or registration fails.
     */
    public static void initTarget() {
        BlockEntityTypes.TARGET = AllayBlockEntityType
                .builder(BlockEntityTargetImpl.class)
                .name(BlockEntityId.TARGET)
                .addComponent(BlockEntityTargetBaseComponentImpl::new, BlockEntityTargetBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the shelf factory with its specialized container-holder
     * provider. The builder supplies the default base component because this
     * configuration adds only the shelf holder, then publishes the
     * {@code BlockEntityTypes.SHELF} API reference.
     */
    public static void initShelf() {
        BlockEntityTypes.SHELF = AllayBlockEntityType
                .builder(BlockEntityShelfImpl.class)
                .name(BlockEntityId.SHELF)
                .addComponent(BlockEntityShelfContainerHolderComponentImpl::new, BlockEntityShelfContainerHolderComponentImpl.class)
                .build();
    }

    /**
     * Registers the chiseled-bookshelf factory with its specialized
     * inventory-holder provider. The builder adds default base behavior for
     * this holder-only configuration before publishing the completed
     * {@code BlockEntityTypes.CHISELED_BOOKSHELF} type in the API.
     */
    public static void initChiseledBookshelf() {
        BlockEntityTypes.CHISELED_BOOKSHELF = AllayBlockEntityType
                .builder(BlockEntityChiseledBookshelfImpl.class)
                .name(BlockEntityId.CHISELED_BOOKSHELF)
                .addComponent(BlockEntityChiseledBookshelfContainerHolderComponentImpl::new, BlockEntityChiseledBookshelfContainerHolderComponentImpl.class)
                .build();
    }

    /**
     * Registers ordinary and soul campfires while retaining separate API type
     * references. Both builders use the {@code "Campfire"} persistence name, so
     * the second registration replaces the first name-based registry mapping.
     * {@code BlockEntityTypes.CAMPFIRE} still holds the ordinary type and
     * {@code BlockEntityTypes.SOUL_CAMPFIRE} holds the soul type, each with its
     * own specialized base provider. Both serialize the same identifier; later
     * name-only loading therefore resolves the last registered type rather than
     * distinguishing the original API factory choice.
     */
    public static void initCampfire() {
        BlockEntityTypes.CAMPFIRE = AllayBlockEntityType
                .builder(BlockEntityCampfireImpl.class)
                .name(BlockEntityId.CAMPFIRE)
                .addComponent(BlockEntityCampfireBaseComponentImpl::new, BlockEntityCampfireBaseComponentImpl.class)
                .build();
        // Both variants use Campfire in persisted NBT; publishing the soul
        // variant replaces registry lookup while CAMPFIRE still retains the
        // ordinary factory.
        BlockEntityTypes.SOUL_CAMPFIRE = AllayBlockEntityType
                .builder(BlockEntityCampfireImpl.class)
                .name(BlockEntityId.CAMPFIRE)
                .addComponent(BlockEntitySoulCampfireBaseComponentImpl::new, BlockEntitySoulCampfireBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the piston-arm factory with its specialized movement-state base
     * component. The captured provider is used for later instances, and
     * successful registry registration precedes publication through the
     * {@code BlockEntityTypes.PISTON_ARM} API field.
     */
    public static void initPistonArm() {
        BlockEntityTypes.PISTON_ARM = AllayBlockEntityType
                .builder(BlockEntityPistonArmImpl.class)
                .name(BlockEntityId.PISTON_ARM)
                .addComponent(BlockEntityPistonArmBaseComponentImpl::new, BlockEntityPistonArmBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the moving-block factory with its specialized carried-state
     * base component. The type is registered under {@code "MovingBlock"} before
     * the {@code BlockEntityTypes.MOVING_BLOCK} API reference changes, so build
     * failure retains any earlier reference.
     */
    public static void initMovingBlock() {
        BlockEntityTypes.MOVING_BLOCK = AllayBlockEntityType
                .builder(BlockEntityMovingBlockImpl.class)
                .name(BlockEntityId.MOVING_BLOCK)
                .addComponent(BlockEntityMovingBlockBaseComponentImpl::new, BlockEntityMovingBlockBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the cauldron factory with its specialized base component. The
     * resulting type captures that provider for construction and replaces the
     * {@code "Cauldron"} registry entry before publishing the
     * {@code BlockEntityTypes.CAULDRON} API reference.
     */
    public static void initCauldron() {
        BlockEntityTypes.CAULDRON = AllayBlockEntityType
                .builder(BlockEntityCauldronImpl.class)
                .name(BlockEntityId.CAULDRON)
                .addComponent(BlockEntityCauldronBaseComponentImpl::new, BlockEntityCauldronBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the decorated-pot factory with its specialized base component.
     * Successful registry registration precedes assignment to
     * {@code BlockEntityTypes.DECORATED_POT}, allowing callers to construct
     * instances using the provider configuration captured by that type.
     */
    public static void initDecoratedPot() {
        BlockEntityTypes.DECORATED_POT = AllayBlockEntityType
                .builder(BlockEntityDecoratedPotImpl.class)
                .name(BlockEntityId.DECORATED_POT)
                .addComponent(BlockEntityDecoratedPotBaseComponentImpl::new, BlockEntityDecoratedPotBaseComponentImpl.class)
                .build();
    }
}
