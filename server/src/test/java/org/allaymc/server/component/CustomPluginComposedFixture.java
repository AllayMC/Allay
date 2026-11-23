package org.allaymc.server.component;

import org.allaymc.api.component.annotation.GenerateImpl;

/**
 * {@code CustomPluginComposedFixture} exercises top-level {@link GenerateImpl}
 * annotation processing for custom composed interfaces outside Allay's built-in
 * API packages.
 *
 * @author daoge_cmd
 */
@GenerateImpl(baseClass = "org.allaymc.server.component.ComponentBlueprintTest.CustomBaseFixture")
public interface CustomPluginComposedFixture extends ComponentBlueprintTest.CustomBadgeComponent {

    /**
     * Returns a constant tag verifying that owner-declared {@code default}
     * methods remain intact on the generated implementation class.
     */
    default String ownerDefaultTag() {
        return "default-tag";
    }
}
