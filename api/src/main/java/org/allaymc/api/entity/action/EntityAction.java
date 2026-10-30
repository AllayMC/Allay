package org.allaymc.api.entity.action;

/**
 * {@code EntityAction} identifies an entity animation or particle action that
 * can be sent to viewers. Concrete actions supply either an enum value or
 * action-specific data; the entity packet encoder translates those values into
 * client protocol events.
 *
 * @author daoge_cmd
 */
public interface EntityAction {
}
