/**
 * {@code org.allaymc.server.entity.impl} hosts the shared entity base class
 * and compile-time generated delegation implementations for all entity
 * interfaces in {@code org.allaymc.api.entity.interfaces}.
 */
@GeneratePackageImpls(
        apiPackage = "org.allaymc.api.entity.interfaces",
        baseClass = "org.allaymc.server.entity.impl.EntityImpl"
)
package org.allaymc.server.entity.impl;

import org.allaymc.api.component.annotation.GeneratePackageImpls;
