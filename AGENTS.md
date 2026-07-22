# Repository Guidelines

Allay is an open-source Minecraft: Bedrock Edition server written in Java 21, built for reliability, performance,
features, and JVM plugin development.

## Project Structure & Module Organization

- `api/`: Public plugin contracts; implementations belong in `server/`.
- `server/`: Runtime implementation and resources, JUnit tests under `src/test/`, and JMH benchmarks under `src/jmh/`.
- `data/`: Data-processing tools and runtime data under `data/resources/`.
- `codegen/`: Java source-generation tools.
- `docs/`: MkDocs content; dependency versions are in `gradle/libs.versions.toml` and CI workflows in
  `.github/workflows/`.

## Build, Test, and Development Commands

Use the checked-in Gradle wrapper and a Java 21 JDK (`gradlew.bat` on Windows).

- `./gradlew build` compiles every module, runs tests, and produces the server artifacts.
- `./gradlew :server:test` runs the JUnit suite using `.test/` as its working directory.
- `./gradlew :server:jacocoTestReport` writes the XML coverage report under `.jacoco/`.
- `./gradlew :server:shadowJar` builds the distributable `allay-server-*-shaded.jar`.
- `./gradlew :server:runShadow` launches the shaded server from `.run/`.
- `./gradlew :server:jmh` runs performance benchmarks in `server/src/jmh/java`.

## Coding Style & Naming Conventions

Use UTF-8, four-space indentation, and the existing Java brace style. Keep packages lowercase beneath `org.allaymc`;
use `PascalCase` for types, `camelCase` for methods and fields, and `UPPER_SNAKE_CASE` for constants. Match nearby code
for imports and Lombok usage because no repository-wide formatter or linter is configured. Use English for all Javadoc
and implementation comments. In `api/`, every class, interface, and method requires Javadoc. In `server/`, also
document internal interfaces commonly used by downstream developers. Explain complex, non-obvious implementation logic
with concise comments. Do not introduce nullability annotations such as `@NonNull` or `@Nullable`; they can cause
misleading IDE diagnostics and do not prevent null-pointer errors. For parameters or return values that may be `null`,
document their null behavior explicitly in Javadoc instead. Preserve relevant thread-safety and API-status annotations.

## Testing Guidelines

Tests use JUnit Jupiter and Mockito. Place them in `server/src/test/java`, mirror the production package, and name test
classes `*Test`. Store fixtures in `server/src/test/resources`. Add focused regression tests for behavior changes; there
is no enforced coverage percentage, but CI publishes JaCoCo results. Run `./gradlew build` before submitting.

## Commit & Pull Request Guidelines

Recent history follows Conventional Commit-style prefixes such as `feat:`, `fix:`, and `chore:`. Write concise,
imperative subjects and reference an issue or PR when applicable. Pull requests should explain the problem and behavior
change, link related issues, list verification commands, and call out API, protocol, configuration, or generated-data
impacts. Include screenshots for visible GUI or documentation changes. Never report security vulnerabilities publicly;
use the private contact documented in `CONTRIBUTING.md`.
