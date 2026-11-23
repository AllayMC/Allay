package org.allaymc.api.component.annotation;

import org.jetbrains.annotations.ApiStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code GeneratePackageImpls} instructs the Allay component annotation
 * processor to generate concrete {@code <InterfaceName>Impl} delegation classes
 * into the annotated server package for every composed interface present in
 * {@link #apiPackage()}. Keeping this package trigger inside the server module
 * allows API interfaces to remain free of server implementation references while
 * preserving a single source of truth for component delegation.
 *
 * @author daoge_cmd
 */
@ApiStatus.Internal
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.PACKAGE)
public @interface GeneratePackageImpls {

    /**
     * Returns the compiled API package whose composed interfaces should receive
     * generated implementation classes in the annotated package.
     *
     * @return fully qualified API package name
     */
    String apiPackage();

    /**
     * Returns the fully qualified superclass name extended by generated
     * implementation classes in this package.
     *
     * @return fully qualified base implementation class name
     */
    String baseClass();
}
