package org.allaymc.api.blockentity.component;

/**
 * {@code BlockEntityDispenserBaseComponent} exposes one dispensing attempt
 * against a block entity's inventory. The selected dispensing behavior decides
 * whether an item is used; event cancellation or an unsuccessful behavior
 * leaves normal source consumption to a later attempt.
 *
 * @author daoge_cmd
 */
public interface BlockEntityDispenserBaseComponent extends BlockEntityBaseComponent {
    /**
     * Attempts to dispense one item through the selected behavior. The result
     * is {@code false} for an empty inventory, event cancellation, or
     * unsuccessful behavior; successful behavior permits source consumption and
     * handling of any remaining item.
     */
    boolean tryDispense();
}
