# AGENTS.md

> Guidance for AI agents (e.g. Copilot, Antigravity, Cursor, Claude) working in this repository.

---

## Table of Contents

1. [Project Overview](#1-project-overview)
2. [Repository Layout](#2-repository-layout)
3. [Build System](#3-build-system)
4. [Coding Standards](#4-coding-standards)
5. [CI/CD Pipeline](#5-cicd-pipeline)
6. [Important Constraints](#6-important-constraints)

---

## 1. Project Overview

**Thoth** is a note-taking app for Android.

| Property       | Value                                        |
|----------------|----------------------------------------------|
| Language       | Kotlin                                       |
| Build system   | Gradle                                       |
| Android SDK    | `compileSdk` 37, `minSdk` 24, `targetSdk` 37 |
| Application ID | `com.oussamateyib.thoth`                     |
| Version        | 1.0.0 (versionCode 1)                        |
| License        | MIT                                          |

---

## 2. Repository Layout

```plaintext
Thoth/
├── .github/
│   ├── ISSUE_TEMPLATE/                              # Bug report & feature request templates
│   ├── workflows/
│   │   ├── build.yml                                # CI: build, lint, and upload artifacts
│   │   ├── release.yml                              # CD: create GitHub releases
│   │   ├── codeql.yml                               # CI: Run static analysis
│   │   └── dependency-submission.yml
│   ├── CODE_OF_CONDUCT.md
│   ├── CONTRIBUTING.md
│   ├── pull_request_template.md
│   └── SECURITY.md
├── app/
│   ├── build.gradle.kts                             # App-level Gradle build script
│   ├── proguard-rules.pro                           # Proguard configuration
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── kotlin/
│       │   └── com/oussamateyib/thoth/
│       │       ├── MainActivity.kt                  # App entry point
│       │       ├── ThothApplication.kt              # Application-level setup
│       │       ├── navigation/                      # Top level navigation items
│       │       └── ui/
│       │           ├── ThothApp.kt                  # Root composable
│       │           └── ThothAppState.kt             # App-level state
│       └── res/                                     # Launcher icons, strings, themes
├── build-logic/                                     # Custom Gradle build logic
│   └── convention/                                  # Shared build configuration plugins
│       ├── build.gradle.kts
│       └── src/main/kotlin/
│           ├── com/oussamateyib/thoth/              # Shared configuration logic
│           └── *ConventionPlugin.kt                 # Project convention plugins
├── core/
│   ├── data/                                        # Central data layer
│   │   ├── build.gradle.kts
│   │   └── src/main/kotlin/com/oussamateyib/thoth/core/data/
│   │       ├── di/                                  # Repository bindings
│   │       ├── model/                               # Mappers
│   │       └── repository/                          # Repositories (interfaces and implementations)
│   ├── database/                                    # Local persistent storage
│   │   ├── build.gradle.kts
│   │   ├── schemas/                                 # Room database schema snapshots
│   │   └── src/main/kotlin/com/oussamateyib/thoth/core/database/
│   │       ├── dao/                                 # Room DAOs
│   │       ├── di/                                  # Database Hilt modules
│   │       ├── model/                               # Room entities
│   │       └── ThothDatabase.kt
│   ├── designsystem/                                # Core UI components, themes
│   │   ├── build.gradle.kts
│   │   └── src/main/kotlin/com/oussamateyib/thoth/core/designsystem/
│   │       ├── theme/
│   │       └── components/
│   ├── domain/                                      # Business logic wrappers
│   │   ├── build.gradle.kts
│   │   └── src/main/kotlin/com/oussamateyib/thoth/core/domain
│   │       └── *UseCase.kt                          # use cases
│   ├── model/                                       # Pure Kotlin data objects
│   │   ├── build.gradle.kts
│   │   └── src/main/kotlin/com/oussamateyib/thoth/core/model/
│   │       └── data/                                # models
│   ├── navigation/                                  # Navigation state and engine
│   │   ├── build.gradle.kts
│   │   └── src/main/kotlin/com/oussamateyib/thoth/core/navigation/
│   │       ├── NavigationState.kt
│   │       └── Navigator.kt
│   └── ui/                                          # Shared data-driven UI components
│       ├── build.gradle.kts
│       └── src/main
│           ├── kotlin/com/oussamateyib/thoth/core/ui
│           └── res/                                 # Drawables, strings
├── feature/
│   └── notes/                                       # Note-taking feature
│       ├── api                                      # Public API
│       │   ├── build.gradle.kts
│       │   └── src/main/
│       │       ├── kotlin/com/oussamateyib/thoth/feature/api/
│       │       │   └── NotesNavKey.kt               # Navigation keys
│       │       └── res/                             # Drawables, strings
│       └── impl
│           ├── build.gradle.kts
│           └── src/main/
│               ├── kotlin/com/oussamateyib/thoth/feature/impl/
│               │   ├── navigation/                  # Navigation entries
│               │   ├── editor/                      # Note editor screen
│               │   └── list/                        # Note list screen
│               └── res/                             # Drawables, strings
├── gradle/                                          # Wrapper and version catalog
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── lint.xml                                         # Lint configuration
├── renovate.json
├── gradlew / gradlew.bat
├── LICENSE
├── README.md
└── AGENTS.md
```

---

## 3. Build System

### Prerequisites

| Tool        | Version                              |
|-------------|--------------------------------------|
| JDK         | 21 (Managed automatically by Gradle) |
| Android SDK | Managed automatically by AGP         |

### Gradle tasks

```bash
# Build debug and release APKs + AABs
./gradlew build
./gradlew bundle

# Build only release artifacts
./gradlew assembleRelease
./gradlew bundleRelease

# Install on a connected device or emulator
./gradlew installDebug
./gradlew installRelease

# Uninstall
./gradlew uninstallDebug
./gradlew uninstallRelease

# Run lint checks
./gradlew lint lintRelease

# Analyze dependencies
./gradlew buildHealth

# Clean all build outputs
./gradlew clean
```

### Release signing

Release builds read signing credentials from the following **environment variables**:

| Variable         | Description                                            |
|------------------|--------------------------------------------------------|
| `STORE_FILE`     | Absolute path to the `.jks` / `.p12` keystore          |
| `STORE_PASSWORD` | Keystore password                                      |
| `KEY_ALIAS`      | Key alias inside the keystore                          |
| `KEY_PASSWORD`   | Key password (falls back to `STORE_PASSWORD` if unset) |

If none of these are set, the release build falls back to the **debug keystore** automatically.

### Gradle performance settings (`gradle.properties`)

- Build cache and configuration cache are enabled, running in parallel.
- JVM heap is set to 2 GB.
- Non-transitive R classes are enabled (`android.nonTransitiveRClass=true`).

---

## 4. Coding Standards

### Kotlin code (`app/src/main/kotlin/`)

- **Style**: Follow official Kotlin style.

### Gradle (`.gradle.kts` files)

- Use **Kotlin DSL** (`.kts`) — do not introduce Groovy build scripts.
- Add new dependencies through the **version catalog** (`gradle/libs.versions.toml`), not as inline
  strings.

### XML / Resources

- All string resources belong in `app/src/main/res/values/strings.xml`.
- Follow Android resource naming conventions (`snake_case` for resource IDs).

---

## 5. CI/CD Pipeline

All workflows are defined in `.github/workflows/`.

### `build.yml` — triggered on

- Push to `main`
- Push of a `v*.*.*` tag
- Pull requests targeting `main`
- Manual dispatch

**Steps summary:**

1. Check out code.
2. Set up Gradle.
3. Build APKs and AABs, signing with keystore secrets.
4. Verify Room schemas are up to date.
5. Run lint
6. Analyze dependencies.
7. Rename artifacts to a consistent `Thoth_*` naming scheme.
8. Upload artifacts: debug/release APKs, debug/release AABs, ProGuard mapping, build logs, lint
   reports, dependency analysis reports.
9. Generate build-provenance attestations for all output artifacts.

### `release.yml` — triggered on version tag push

1. Build and sign release APKs and AABs.
2. Rename artifacts to `Thoth_<version>_*`, move ProGuard mapping.
3. Generate `SHA256SUMS` and sign it with GPG.
4. Create a GitHub Release with auto-generated notes, attach APKs, AAB, mapping, `SHA256SUMS`, and
   `SHA256SUMS.asc`. Also opens a discussion under **Announcements**.

### `dependency-submission.yml`  — triggered on

- Push to `main`
- Manual dispatch

Submits the dependency graph to GitHub for security analysis.

### `codeql.yml` — triggered on

- Push to `main`
- Push of a `v*.*.*` tag
- Pull requests targeting `main`
- Manual dispatch

Runs GitHub's CodeQL static analysis on `java-kotlin`.

---

## 6. Important Constraints

| Rule                                        | Reason                                                                                                  |
|---------------------------------------------|---------------------------------------------------------------------------------------------------------|
| **Always use `./gradlew`, never `gradle`.** | The wrapper pins the exact Gradle version required for reproducible builds.                             |
| **Lint is treated as errors.**              | `warningsAsErrors = true` in the lint configuration. All lint warnings must be resolved before merging. |
