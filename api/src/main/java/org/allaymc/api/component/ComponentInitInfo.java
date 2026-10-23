package org.allaymc.api.component;

/**
 * {@code ComponentInitInfo} marks data passed to component providers during
 * composed-object construction. Each provider defines its accepted implementation
 * and interprets that data directly; the marker adds no shared fields or
 * validation, so callers must supply the subtype their configured providers expect.
 *
 * @author daoge_cmd
 */
public interface ComponentInitInfo {
}
