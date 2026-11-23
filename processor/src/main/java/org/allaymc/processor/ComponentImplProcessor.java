package org.allaymc.processor;

import com.palantir.javapoet.AnnotationSpec;
import com.palantir.javapoet.ClassName;
import com.palantir.javapoet.FieldSpec;
import com.palantir.javapoet.JavaFile;
import com.palantir.javapoet.MethodSpec;
import com.palantir.javapoet.ParameterSpec;
import com.palantir.javapoet.TypeName;
import com.palantir.javapoet.TypeSpec;
import org.allaymc.api.component.annotation.GenerateImpl;
import org.allaymc.api.component.annotation.GeneratePackageImpls;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * {@code ComponentImplProcessor} generates concrete {@code <InterfaceName>Impl}
 * delegation classes at compile time for composed interfaces annotated with
 * {@link GenerateImpl} and for compiled API packages referenced by
 * {@link GeneratePackageImpls}. Generated classes extend the resolved server
 * base implementation, declare strongly typed component delegate fields, expose
 * non-conflicting component getters, and forward component contract methods in
 * plain Java without relying on Lombok delegation.
 *
 * <p>
 * The processor inspects only declared interface hierarchies rather than
 * Lombok-synthesized methods, avoiding annotation-processor round-ordering
 * fragility. Pure component contracts are distinguished from composed owner
 * interfaces by checking whether a superinterface extends {@code Component}
 * without extending {@code Entity}, {@code ItemStack}, {@code BlockBehavior}, or
 * {@code BlockEntity}. Parameterized component contracts are resolved through
 * {@link Types#directSupertypes(TypeMirror)} and {@link Types#asMemberOf} so
 * generic methods emit exact concrete signatures.
 *
 * @author daoge_cmd
 */
public final class ComponentImplProcessor extends AbstractProcessor {

    /**
     * {@code COMPONENT_FQN} identifies the root component marker interface.
     * The remaining constants name the domain marker interfaces, composed owner
     * root interfaces, and default server base implementation classes used during
     * automatic superclass inference.
     */
    private static final String COMPONENT_FQN = "org.allaymc.api.component.Component";
    /** Root marker interfaces excluded when collecting concrete component contracts. */
    private static final Set<String> ROOT_MARKER_FQNS = Set.of(
            COMPONENT_FQN,
            "org.allaymc.api.block.component.BlockComponent",
            "org.allaymc.api.item.component.ItemComponent",
            "org.allaymc.api.entity.component.EntityComponent",
            "org.allaymc.api.blockentity.component.BlockEntityComponent"
    );
    /** Domain owner interfaces mapped to their default server base classes. */
    private static final Map<String, String> OWNER_TO_BASE_IMPL = Map.of(
            "org.allaymc.api.entity.Entity", "org.allaymc.server.entity.impl.EntityImpl",
            "org.allaymc.api.item.ItemStack", "org.allaymc.server.item.impl.ItemStackImpl",
            "org.allaymc.api.block.BlockBehavior", "org.allaymc.server.block.impl.BlockBehaviorImpl",
            "org.allaymc.api.blockentity.BlockEntity", "org.allaymc.server.blockentity.impl.BlockEntityImpl"
    );
    /** Domain prefixes stripped when deriving component field and getter names. */
    private static final List<String> DOMAIN_PREFIXES = List.of("BlockEntity", "Entity", "Block", "Item");
    /** Server dependency annotation used to mark optional owner delegate fields for default-only contracts. */
    private static final ClassName DEPENDENCY_ANNO = ClassName.get("org.allaymc.server.component.annotation", "Dependency");
    /** {@code Object} method names never delegated to component fields. */
    private static final Set<String> OBJECT_METHODS = Set.of(
            "equals", "hashCode", "toString", "getClass", "notify", "notifyAll", "wait", "clone", "finalize"
    );

    /** Annotation processing type utilities. */
    private Types types;
    /** Annotation processing element utilities. */
    private Elements elements;
    /** Annotation processing source filer. */
    private Filer filer;
    /** Annotation processing diagnostic messager. */
    private Messager messager;
    /** Qualified names emitted during this compiler invocation to prevent duplicate writes across rounds. */
    private final Set<String> generatedClasses = new HashSet<>();

    /**
     * Initializes compiler utilities from {@code processingEnv}.
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.types = processingEnv.getTypeUtils();
        this.elements = processingEnv.getElementUtils();
        this.filer = processingEnv.getFiler();
        this.messager = processingEnv.getMessager();
    }

    /**
     * Returns the two annotations handled by this processor.
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(GenerateImpl.class.getCanonicalName(), GeneratePackageImpls.class.getCanonicalName());
    }

    /**
     * Returns the latest supported Java source version.
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * Processes {@link GeneratePackageImpls} package triggers and individual
     * {@link GenerateImpl} interface declarations, emitting one plain Java
     * implementation class per discovered composed interface.
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return false;
        }

        for (Element element : roundEnv.getElementsAnnotatedWith(GeneratePackageImpls.class)) {
            if (element.getKind() != ElementKind.PACKAGE) {
                messager.printMessage(Diagnostic.Kind.ERROR, "@GeneratePackageImpls may only annotate package declarations", element);
                continue;
            }
            var targetPkg = ((PackageElement) element).getQualifiedName().toString();
            var anno = element.getAnnotation(GeneratePackageImpls.class);
            var apiPkgElement = elements.getPackageElement(anno.apiPackage());
            if (apiPkgElement == null) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Cannot find API package: " + anno.apiPackage(), element);
                continue;
            }
            var baseClassElement = elements.getTypeElement(anno.baseClass());
            if (baseClassElement == null) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Cannot find base implementation class: " + anno.baseClass(), element);
                continue;
            }

            for (Element enclosed : apiPkgElement.getEnclosedElements()) {
                if (enclosed.getKind() == ElementKind.INTERFACE && enclosed instanceof TypeElement iface) {
                    generateImplClass(iface, targetPkg, baseClassElement, element);
                }
            }
        }

        for (Element element : roundEnv.getElementsAnnotatedWith(GenerateImpl.class)) {
            if (element.getKind() != ElementKind.INTERFACE || !(element instanceof TypeElement iface)) {
                messager.printMessage(Diagnostic.Kind.ERROR, "@GenerateImpl may only annotate interface declarations", element);
                continue;
            }
            var anno = iface.getAnnotation(GenerateImpl.class);
            var baseClassElement = resolveBaseClass(iface, anno.baseClass());
            if (baseClassElement == null) {
                continue;
            }
            var targetPkg = elements.getPackageOf(iface).getQualifiedName().toString();
            generateImplClass(iface, targetPkg, baseClassElement, iface);
        }

        return true;
    }

    /**
     * Resolves the superclass element from an explicit binary name or by matching
     * {@code iface} against the four built-in owner root interfaces.
     */
    private TypeElement resolveBaseClass(TypeElement iface, String explicitBaseClass) {
        if (!explicitBaseClass.isBlank()) {
            var resolved = elements.getTypeElement(explicitBaseClass);
            if (resolved == null) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Cannot find baseClass " + explicitBaseClass, iface);
            }
            return resolved;
        }

        var ifaceType = iface.asType();
        for (var entry : OWNER_TO_BASE_IMPL.entrySet()) {
            var ownerRoot = elements.getTypeElement(entry.getKey());
            if (ownerRoot != null && types.isAssignable(types.erasure(ifaceType), types.erasure(ownerRoot.asType()))) {
                var base = elements.getTypeElement(entry.getValue());
                if (base == null) {
                    messager.printMessage(Diagnostic.Kind.ERROR, "Cannot find default base class " + entry.getValue() + " on classpath", iface);
                }
                return base;
            }
        }

        messager.printMessage(
                Diagnostic.Kind.ERROR,
                "Cannot infer base implementation class for " + iface.getQualifiedName()
                + "; specify @GenerateImpl(baseClass = \"...\") explicitly",
                iface
        );
        return null;
    }

    /**
     * Emits {@code <InterfaceName>Impl} into {@code targetPkg} extending
     * {@code baseClass} and delegating all component contracts inherited by
     * {@code composedInterface} that are not already covered by {@code baseClass}.
     */
    private void generateImplClass(TypeElement composedInterface, String targetPkg, TypeElement baseClass, Element originatingElement) {
        var implSimpleName = composedInterface.getSimpleName().toString() + "Impl";
        var targetFqn = targetPkg.isEmpty() ? implSimpleName : targetPkg + "." + implSimpleName;
        if (!generatedClasses.add(targetFqn)) {
            return;
        }

        var componentRoot = elements.getTypeElement(COMPONENT_FQN);
        var ownerRoots = resolveOwnerRootErasures();
        var composedType = (DeclaredType) composedInterface.asType();

        // Discover pure component contracts already satisfied by baseClass versus
        // those exposed by composedInterface.
        var baseContracts = collectPureComponentContracts((DeclaredType) baseClass.asType(), componentRoot, ownerRoots);
        var composedContracts = collectPureComponentContracts(composedType, componentRoot, ownerRoots);

        // Retain only the most specific contract per subtyping chain and drop
        // contracts whose erased type is already delegated by baseClass.
        var mostSpecificContracts = keepMostSpecificContracts(composedContracts);
        Set<String> baseContractFqns = new HashSet<>();
        for (var c : baseContracts) {
            baseContractFqns.add(((TypeElement) c.asElement()).getQualifiedName().toString());
        }

        List<DeclaredType> delegateContracts = new ArrayList<>();
        for (var contract : mostSpecificContracts) {
            var fqn = ((TypeElement) contract.asElement()).getQualifiedName().toString();
            if (!baseContractFqns.contains(fqn)) {
                delegateContracts.add(contract);
            }
        }

        // Track methods already implemented by baseClass's contracts and default
        // methods explicitly declared on owner interfaces so we never shadow them.
        Map<String, TypeMirror> baseImplementedMethods = collectImplementedMethodReturns(baseContracts);
        Set<String> ownerDefaultMethods = collectOwnerDeclaredDefaultMethods(composedType, ownerRoots);
        Set<String> existingZeroArgMethodNames = collectAllMethodNames(composedType, baseClass);

        var classBuilder = TypeSpec.classBuilder(implSimpleName)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(SuppressWarnings.class)
                        .addMember("value", "{$S, $S}", "unchecked", "deprecation")
                        .build())
                .superclass(ClassName.get(baseClass))
                .addSuperinterface(ClassName.get(composedInterface));

        addConstructors(classBuilder, baseClass);

        Set<String> emittedMethodKeys = new HashSet<>();
        Set<String> usedFieldNames = new HashSet<>();

        for (var contract : delegateContracts) {
            var contractElement = (TypeElement) contract.asElement();
            var contractMethods = collectContractMethods(contract);

            // Collect methods that actually need forwarding on this generated class.
            List<ExecutableElement> methodsToForward = new ArrayList<>();
            boolean hasAbstractMethod = false;
            for (var method : contractMethods) {
                var key = methodSignatureKey(method, composedType);
                if (ownerDefaultMethods.contains(key)) {
                    // Preserve default helper methods explicitly declared on the
                    // composed owner interface (e.g., EntityPlayer or ItemArrowStack).
                    continue;
                }

                var resolvedExec = (ExecutableType) types.asMemberOf(composedType, method);
                var baseReturn = baseImplementedMethods.get(key);
                if (baseReturn != null && types.isAssignable(types.erasure(baseReturn), types.erasure(resolvedExec.getReturnType()))) {
                    // Base class already implements this signature with the same or more specific return type.
                    continue;
                }

                if (!emittedMethodKeys.add(key)) {
                    continue;
                }

                if (!method.getModifiers().contains(Modifier.DEFAULT)) {
                    hasAbstractMethod = true;
                }
                methodsToForward.add(method);
            }

            // Pure zero-method marker interfaces (such as ItemToolComponent) require
            // no delegate field; default-only contracts (such as EntityUndeadComponent)
            // emit an optional delegate field that falls back to the interface default.
            if (methodsToForward.isEmpty() && contractMethods.isEmpty()) {
                continue;
            }
            boolean optionalDelegate = !hasAbstractMethod;

            var baseFieldName = deriveFieldName(contractElement.getSimpleName().toString());
            var fieldName = deduplicateName(baseFieldName, usedFieldNames);
            var fieldType = TypeName.get(contract);

            var fieldBuilder = FieldSpec.builder(fieldType, fieldName, Modifier.PRIVATE);
            if (optionalDelegate) {
                fieldBuilder.addAnnotation(AnnotationSpec.builder(DEPENDENCY_ANNO)
                        .addMember("optional", "$L", true)
                        .build());
            }
            classBuilder.addField(fieldBuilder.build());

            var getterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            if (!existingZeroArgMethodNames.contains(getterName)) {
                existingZeroArgMethodNames.add(getterName);
                classBuilder.addMethod(MethodSpec.methodBuilder(getterName)
                        .addModifiers(Modifier.PUBLIC)
                        .returns(fieldType)
                        .addStatement("return this.$N", fieldName)
                        .build());
            }

            for (var method : methodsToForward) {
                classBuilder.addMethod(buildDelegateMethod(method, composedType, fieldName, optionalDelegate, composedInterface));
            }
        }

        var javaFile = JavaFile.builder(targetPkg, classBuilder.build())
                .indent("    ")
                .skipJavaLangImports(true)
                .build();
        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "Failed to write generated implementation " + targetFqn + ": " + e.getMessage(),
                    originatingElement
            );
        }
    }

    /**
     * Emits the no-argument blueprint constructor plus matching forwarding
     * constructors for every public constructor exposed by {@code baseClass}.
     */
    private void addConstructors(TypeSpec.Builder classBuilder, TypeElement baseClass) {
        classBuilder.addMethod(MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addStatement("super()")
                .build());

        for (Element enclosed : baseClass.getEnclosedElements()) {
            if (enclosed.getKind() != ElementKind.CONSTRUCTOR || !(enclosed instanceof ExecutableElement ctor)) {
                continue;
            }
            if (!ctor.getModifiers().contains(Modifier.PUBLIC) || ctor.getParameters().isEmpty()) {
                continue;
            }
            var ctorBuilder = MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC);
            List<String> argNames = new ArrayList<>();
            int idx = 0;
            for (var param : ctor.getParameters()) {
                var pName = param.getSimpleName().toString();
                if (pName.isEmpty()) {
                    pName = "arg" + idx;
                }
                ctorBuilder.addParameter(ParameterSpec.builder(TypeName.get(param.asType()), pName).build());
                argNames.add(pName);
                idx++;
            }
            ctorBuilder.addStatement("super($L)", String.join(", ", argNames));
            classBuilder.addMethod(ctorBuilder.build());
        }
    }

    /**
     * Builds one overriding method that forwards directly to {@code fieldName}.
     * When {@code optionalDelegate} is {@code true}, a {@code null} delegate falls
     * back to the declaring contract interface's {@code default} implementation.
     */
    private MethodSpec buildDelegateMethod(
            ExecutableElement method,
            DeclaredType ownerType,
            String fieldName,
            boolean optionalDelegate,
            TypeElement directSuperInterface
    ) {
        var builder = MethodSpec.overriding(method, ownerType, types);
        var resolvedExec = (ExecutableType) types.asMemberOf(ownerType, method);
        List<String> args = new ArrayList<>();
        for (var param : method.getParameters()) {
            args.add(param.getSimpleName().toString());
        }
        var callArgs = String.join(", ", args);
        var methodName = method.getSimpleName().toString();
        if (optionalDelegate) {
            // JLS 15.12.1 requires a direct superinterface before .super; composedInterface
            // inherits the default method from the declaring component contract.
            var superIface = ClassName.get(directSuperInterface);
            if (resolvedExec.getReturnType().getKind() == TypeKind.VOID) {
                builder.beginControlFlow("if (this.$N != null)", fieldName)
                        .addStatement("this.$N.$N($L)", fieldName, methodName, callArgs)
                        .nextControlFlow("else")
                        .addStatement("$T.super.$N($L)", superIface, methodName, callArgs)
                        .endControlFlow();
            } else {
                builder.addStatement(
                        "return this.$N != null ? this.$N.$N($L) : $T.super.$N($L)",
                        fieldName, fieldName, methodName, callArgs, superIface, methodName, callArgs
                );
            }
        } else if (resolvedExec.getReturnType().getKind() == TypeKind.VOID) {
            builder.addStatement("this.$N.$N($L)", fieldName, methodName, callArgs);
        } else {
            builder.addStatement("return this.$N.$N($L)", fieldName, methodName, callArgs);
        }
        return builder.build();
    }

    /**
     * Resolves the erased mirrors of the four domain owner root interfaces so
     * composed owner interfaces are excluded when collecting component contracts.
     */
    private List<TypeMirror> resolveOwnerRootErasures() {
        List<TypeMirror> result = new ArrayList<>();
        for (var fqn : OWNER_TO_BASE_IMPL.keySet()) {
            var el = elements.getTypeElement(fqn);
            if (el != null) {
                result.add(types.erasure(el.asType()));
            }
        }
        return result;
    }

    /**
     * Collects all pure component contract superinterfaces reachable from
     * {@code startType}, retaining their resolved parameterized types.
     */
    private List<DeclaredType> collectPureComponentContracts(
            DeclaredType startType,
            TypeElement componentRoot,
            List<TypeMirror> ownerRoots
    ) {
        var componentErasure = types.erasure(componentRoot.asType());
        Map<String, DeclaredType> contracts = new LinkedHashMap<>();
        Set<String> visited = new HashSet<>();
        Queue<DeclaredType> queue = new ArrayDeque<>();
        queue.add(startType);

        while (!queue.isEmpty()) {
            var current = queue.poll();
            var currentEl = (TypeElement) current.asElement();
            var currentFqn = currentEl.getQualifiedName().toString();
            if (!visited.add(currentFqn)) {
                continue;
            }

            if (currentEl.getKind() == ElementKind.INTERFACE
                    && !types.isSameType(types.erasure(current), types.erasure(startType))
                    && !ROOT_MARKER_FQNS.contains(currentFqn)
                    && types.isAssignable(types.erasure(current), componentErasure)
                    && !isOwnerInterface(current, ownerRoots)) {
                contracts.putIfAbsent(currentFqn, current);
            }

            for (TypeMirror superType : types.directSupertypes(current)) {
                if (superType instanceof DeclaredType dt) {
                    queue.add(dt);
                }
            }
        }
        return new ArrayList<>(contracts.values());
    }

    /**
     * Returns whether {@code candidate} is assignable to any composed domain
     * owner root interface ({@code Entity}, {@code ItemStack},
     * {@code BlockBehavior}, or {@code BlockEntity}).
     */
    private boolean isOwnerInterface(DeclaredType candidate, List<TypeMirror> ownerRoots) {
        var erased = types.erasure(candidate);
        for (var ownerRoot : ownerRoots) {
            if (types.isAssignable(erased, ownerRoot)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Filters {@code contracts} to keep only the most specific contract in each
     * subtyping hierarchy so base contracts are not duplicated alongside their
     * specialized subinterfaces.
     */
    private List<DeclaredType> keepMostSpecificContracts(List<DeclaredType> contracts) {
        List<DeclaredType> result = new ArrayList<>();
        for (var candidate : contracts) {
            var candidateErasure = types.erasure(candidate);
            boolean shadowedBySubtype = false;
            for (var other : contracts) {
                if (candidate == other) {
                    continue;
                }
                var otherErasure = types.erasure(other);
                if (!types.isSameType(candidateErasure, otherErasure)
                        && types.isSubtype(otherErasure, candidateErasure)) {
                    shadowedBySubtype = true;
                    break;
                }
            }
            if (!shadowedBySubtype) {
                result.add(candidate);
            }
        }
        return result;
    }

    /**
     * Collects all non-static, non-private methods reachable from a component
     * contract and its superinterfaces, keeping the declaration with the most
     * specific return type when a subinterface narrows an inherited method.
     */
    private List<ExecutableElement> collectContractMethods(DeclaredType contractType) {
        Map<String, ExecutableElement> bySignature = new LinkedHashMap<>();
        Map<String, TypeMirror> returnBySignature = new HashMap<>();
        Set<String> visitedTypes = new HashSet<>();
        Queue<DeclaredType> queue = new ArrayDeque<>();
        queue.add(contractType);

        while (!queue.isEmpty()) {
            var current = queue.poll();
            var el = (TypeElement) current.asElement();
            if (!visitedTypes.add(el.getQualifiedName().toString())) {
                continue;
            }
            for (Element member : el.getEnclosedElements()) {
                if (member.getKind() != ElementKind.METHOD || !(member instanceof ExecutableElement exec)) {
                    continue;
                }
                var mods = exec.getModifiers();
                if (mods.contains(Modifier.STATIC) || mods.contains(Modifier.PRIVATE)) {
                    continue;
                }
                if (OBJECT_METHODS.contains(exec.getSimpleName().toString())) {
                    continue;
                }
                var key = methodSignatureKey(exec, contractType);
                var ret = ((ExecutableType) types.asMemberOf(contractType, exec)).getReturnType();
                var existingRet = returnBySignature.get(key);
                if (existingRet == null || types.isAssignable(types.erasure(ret), types.erasure(existingRet))) {
                    bySignature.put(key, exec);
                    returnBySignature.put(key, ret);
                }
            }
            for (TypeMirror superType : types.directSupertypes(current)) {
                if (superType instanceof DeclaredType dt) {
                    queue.add(dt);
                }
            }
        }
        return new ArrayList<>(bySignature.values());
    }

    /**
     * Records erased-signature keys to their most specific return types for
     * methods already provided by {@code baseContracts}.
     */
    private Map<String, TypeMirror> collectImplementedMethodReturns(List<DeclaredType> baseContracts) {
        Map<String, TypeMirror> map = new HashMap<>();
        for (var contract : baseContracts) {
            for (var method : collectContractMethods(contract)) {
                var key = methodSignatureKey(method, contract);
                var resolved = (ExecutableType) types.asMemberOf(contract, method);
                var ret = resolved.getReturnType();
                var existing = map.get(key);
                if (existing == null || types.isAssignable(types.erasure(ret), types.erasure(existing))) {
                    map.put(key, ret);
                }
            }
        }
        return map;
    }

    /**
     * Collects signature keys of {@code default} methods declared directly on
     * composed owner interfaces in {@code composedType}'s hierarchy so generated
     * classes preserve those owner-level default methods.
     */
    private Set<String> collectOwnerDeclaredDefaultMethods(DeclaredType composedType, List<TypeMirror> ownerRoots) {
        Set<String> defaults = new HashSet<>();
        Set<String> visited = new HashSet<>();
        Queue<DeclaredType> queue = new ArrayDeque<>();
        queue.add(composedType);

        while (!queue.isEmpty()) {
            var current = queue.poll();
            var el = (TypeElement) current.asElement();
            if (!visited.add(el.getQualifiedName().toString())) {
                continue;
            }
            if (types.isSameType(types.erasure(current), types.erasure(composedType)) || isOwnerInterface(current, ownerRoots)) {
                for (Element member : el.getEnclosedElements()) {
                    if (member.getKind() == ElementKind.METHOD && member instanceof ExecutableElement exec
                            && exec.getModifiers().contains(Modifier.DEFAULT)) {
                        defaults.add(methodSignatureKey(exec, composedType));
                    }
                }
            }
            for (TypeMirror superType : types.directSupertypes(current)) {
                if (superType instanceof DeclaredType dt) {
                    queue.add(dt);
                }
            }
        }
        return defaults;
    }

    /**
     * Collects zero-argument method names visible on {@code composedType} and
     * {@code baseClass} so generated component getters never collide with API or
     * superclass methods.
     */
    private Set<String> collectAllMethodNames(DeclaredType composedType, TypeElement baseClass) {
        Set<String> names = new LinkedHashSet<>();
        Set<String> visited = new HashSet<>();
        Queue<DeclaredType> queue = new ArrayDeque<>();
        queue.add(composedType);
        queue.add((DeclaredType) baseClass.asType());

        while (!queue.isEmpty()) {
            var current = queue.poll();
            var el = (TypeElement) current.asElement();
            if (!visited.add(el.getQualifiedName().toString())) {
                continue;
            }
            for (Element member : el.getEnclosedElements()) {
                if (member.getKind() == ElementKind.METHOD && member instanceof ExecutableElement exec
                        && exec.getParameters().isEmpty()) {
                    names.add(exec.getSimpleName().toString());
                }
            }
            for (TypeMirror superType : types.directSupertypes(current)) {
                if (superType instanceof DeclaredType dt) {
                    queue.add(dt);
                }
            }
        }
        return names;
    }

    /**
     * Builds an erasure-based method signature key matching Java override rules.
     */
    private String methodSignatureKey(ExecutableElement method, DeclaredType container) {
        var resolved = (ExecutableType) types.asMemberOf(container, method);
        var sb = new StringBuilder(method.getSimpleName().toString()).append('(');
        var params = resolved.getParameterTypes();
        for (int i = 0; i < params.size(); i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(types.erasure(params.get(i)).toString());
        }
        return sb.append(')').toString();
    }

    /**
     * Derives a concise camelCase field name from a component interface simple
     * name by stripping the leading domain prefix when present.
     */
    private static String deriveFieldName(String simpleName) {
        String trimmed = simpleName;
        for (var prefix : DOMAIN_PREFIXES) {
            if (trimmed.startsWith(prefix) && trimmed.length() > prefix.length()) {
                trimmed = trimmed.substring(prefix.length());
                break;
            }
        }
        // Lowercase leading acronyms gracefully (e.g., AIComponent -> aiComponent).
        if (trimmed.length() >= 2 && Character.isUpperCase(trimmed.charAt(0)) && Character.isUpperCase(trimmed.charAt(1))) {
            int i = 0;
            while (i < trimmed.length() && Character.isUpperCase(trimmed.charAt(i))) {
                i++;
            }
            int split = (i == trimmed.length()) ? i : i - 1;
            return trimmed.substring(0, split).toLowerCase(Locale.ROOT) + trimmed.substring(split);
        }
        return Character.toLowerCase(trimmed.charAt(0)) + trimmed.substring(1);
    }

    /**
     * Ensures generated field names remain unique within one class.
     */
    private static String deduplicateName(String base, Set<String> used) {
        if (used.add(base)) {
            return base;
        }
        int suffix = 2;
        while (!used.add(base + suffix)) {
            suffix++;
        }
        return base + suffix;
    }
}
