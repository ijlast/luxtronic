# Copilot instructions for luxtronic

Quick commands

- Build (full):
  - Unix/macOS: ./gradlew clean build
  - Windows (PowerShell/CMD): .\gradlew.bat clean build
- Run tests (all):
  - ./gradlew test
- Run a single test class (TestNG):
  - ./gradlew test --tests "enums.EnumConstantsMapTest"
  - Pattern example: ./gradlew test --tests "*EnumConstantsMapTest"
- Run a single test method:
  - ./gradlew test --tests "com.example.YourTestClass.yourTestMethod"
- Run the application locally (Spring Boot):
  - ./gradlew bootRun
- Create runnable jar (bootJar):
  - ./gradlew bootJar  -> build/libs/luxtronic-<version>.jar
  - Run the jar: java -jar build/libs/luxtronic-<version>.jar
- Docker (Raspberry Pi image referenced in README):
  - docker build -t luxtronic:local .

Notes about linting

- Spotless is configured for Java formatting using Eclipse formatter (max line length 180). Config file: .github/eclipse-formatter.xml
  - ./gradlew spotlessApply   # apply formatting
  - ./gradlew spotlessCheck   # verify formatting (CI should run this)
  - To use Checkstyle instead, add the Checkstyle Gradle plugin and a ruleset.

High-level architecture

- Purpose: Spring Boot REST service acting as a virtual Fibaro HC2 device to control a Luxtronic II (v1.6.1) heatpump.
- Main entry point: github.ijl.luxtronic.Application (Spring Boot @SpringBootApplication).
- Core components:
  - HeatPumpController: REST endpoints that present virtual device behaviour expected by Fibaro HC2.
  - HeatPumpSocketWrapper: low-level TCP socket communication with the physical heatpump (protocol framing, request/response management).
  - format.*: converters and mappers that translate between domain values and the heatpump protocol representations.
  - config/v161: versioned protocol parsing and parameter models for Luxtronic v1.6.1.
  - ServiceProperties (configuration binding): central place for heatpump.ip, port and parameter bounds from application.yml.
- Runtime config: src/main/resources/application.yml (heatpump.* values) — change values here or via Spring profiles / environment variables.
- Tests: TestNG-based tests under src/test/java. Some examples live in package `enums`.

Key conventions & repository patterns

- Versioned protocol code: add new protocol versions under src/main/java/.../config/v{version} following the v161 layout.
- Converters: implement new mappings under format.input or format.output and wire through FormatConverter / OneToOneConverter patterns used across the codebase.
- Exceptions: domain and validation errors have dedicated exception classes in the exception package (e.g., InvalidParameterException). Prefer these for validation failures.
- Configuration: use ServiceProperties to centralize bounds and defaults (application.yml keys under `heatpump:`).
- Tests: prefer TestNG patterns used in the project and run via Gradle wrapper.
- Build environment: build.gradle declares a Java toolchain (languageVersion = 21). CI workflow (.github/workflows/gradle.yml) sets up JDK 17 — validate local JDK/toolchain compatibility if builds fail.
- Always use the Gradle wrapper (./gradlew) to ensure consistent Gradle and toolchain behaviour.

Assistant / agent configs discovered

- Existing file: .github/copilot-instructions.md (this file).
- No CLAUDE.md, .cursorrules, AGENTS.md, .windsurfrules, or other assistant rule files were found.

Where to look next

- src/main/java/github/ijl/luxtronic — main application and controller logic
- src/main/java/github/ijl/luxtronic/format — converters and parsing
- src/main/java/github/ijl/luxtronic/config/v161 — protocol/version-specific logic
- src/main/resources/application.yml — runtime config
- .github/workflows/gradle.yml — CI build steps

Need changes?

If you'd like, suggestions can be made to:
- Add Spotless/Checkstyle configuration and example commands
- Add a small CONTRIBUTING.md or a PR checklist
- Align CI JDK with the Gradle toolchain (or adjust the toolchain)

(End of copilot-instructions.md)
