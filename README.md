[![Test, Build, and Distribute App (android for now)](https://github.com/alexiyous/Weatherio-KMP/actions/workflows/distribute_app.yml/badge.svg)](https://github.com/alexiyous/Weatherio-KMP/actions/workflows/distribute_app.yml)


# Weatherio 🌦️

**Weatherio** is a modern, cross-platform weather application built with **Kotlin Multiplatform (KMP)** and **Compose Multiplatform**. It provides real-time weather updates, forecasts, and background notifications across Android, iOS, and Desktop.

This project demonstrates the power of sharing logic and UI code between platforms while accessing platform-specific features like WorkManager, Notifications, and System Tray integration.

> **Note:** This project was inspired by [Forecastly](https://github.com/Hoodlab/Forecastly). While the core structure provided a learning foundation, **Weatherio** includes significant customizations, new features (like background sync, cross-platform notifications, etc), and improvements aligned with a specific product vision.

## ✨ Features

*   **Cross-Platform Support**: Single codebase running seamlessly on Android, iOS, and Desktop (JVM).
*   **Neumorphism Design**: A customized, modern UI aesthetic featuring soft shadows and depth effects for a unique visual experience.
*   **Real-Time Weather**: Accurate current weather conditions including temperature, wind, UV index, and sunset times.
*   **Android Home Screen Widget**: Get a glance at the current weather directly from your home screen with a beautiful, responsive widget.
*   **Detailed Forecasts**:
    *   Hourly temperature trends visualized with custom line graphs.
    *   Daily weather summaries.
*   **Background Synchronization**:
    *   **Android**: Uses Jetpack **WorkManager** for reliable periodic background updates (every 30 mins).
    *   **Desktop/iOS**: Custom coroutine-based schedulers for active-session updates.
*   **Smart Notifications**:
    *   Get notified about the next hour's forecast automatically.
    *   **Android**: Native Notification Channels & Permissions (Android 13+ support).
    *   **iOS**: `UNUserNotificationCenter` integration.
    *   **Desktop**: System Tray notifications.
*   **Robust Error Handling**: Custom Ktor implementation to gracefully handle network issues, API errors, and edge cases.

## 🛠️ Tech Stack

### Core
*   **Language**: [Kotlin](https://kotlinlang.org/)
*   **UI Framework**: [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) (Material 3)
*   **Architecture**: MVVM with Clean Architecture principles

### Libraries & Tools
*   **Dependency Injection**: [Koin](https://insert-koin.io/)
*   **Networking**: [Ktor](https://ktor.io/) (with Custom Error Handling)
*   **Image Loading**: [Coil 3](https://coil-kt.github.io/coil/)
*   **App Widgets**: [Glance](https://developer.android.com/jetpack/compose/glance)
*   **Local Storage**: [Room](https://developer.android.com/kotlin/multiplatform/room) (SQLite)
*   **Logging**: [Napier](https://github.com/AAkira/Napier)
*   **Background Work**:
    *   `androidx.work` (Android)
    *   Kotlin Coroutines (Shared)

## 🔄 CI/CD Pipeline (Android)

Fully automated GitHub Actions + Fastlane workflow that delivers builds to Firebase App Distribution in <3 minutes post-merge.

### Pipeline Flow

1. **Trigger**: Push to `dev`/`staging`/`main` branches
2. **Extract PR description** → Becomes Firebase release notes
3. **Run flavor-specific tests** → `testDevDebugUnitTest`, `testStagingDebugUnitTest`, or `testProdDebugUnitTest`
4. **Smart version bump**:
   - Analyzes last 5 commits' Git diff
   - >1000 lines or >50 files = **MAJOR** (1.0.0 → 2.0.0)
   - >300 lines or >15 files = **MINOR** (1.2.0 → 1.3.0)
   - Otherwise = **PATCH** (1.2.3 → 1.2.4)
5. **Fetch current version** → Firebase API (falls back to Gradle on first build)
6. **Auto-increment** `versionCode` and `versionName` in `build.gradle.kts`
7. **Build signed APK** → `assembleDevRelease`, `assembleStagingRelease`, or `assembleProdRelease`
8. **Upload to Firebase** → Separate tester groups per environment
9. **Notify testers** → Automatic push with release notes

### Environment Mapping

- `dev` → Dev testers (test builds only)
- `staging` → QA testers (full release cycle)
- `main`/`master` → Internal release team (production candidates)

### Secrets Configuration

Required GitHub secrets (base64-encoded):
- `GOOGLE_SERVICES_JSON_B64` → Firebase config
- `RELEASE_KEYSTORE_B64` → Signing keystore
- `FIREBASE_SERVICE_ACCOUNT_B64` → App Distribution credentials
- `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD` → Keystore credentials
- `FIREBASE_APP_ID` → Per-environment app ID

### Tech Stack

- **GitHub Actions** (Ubuntu runner, JDK 17)
- **Fastlane** (Ruby) + Firebase App Distribution plugin
- **Gradle** (Kotlin DSL) + ktlint enforcement

### Impact

- **<3 min** from merge to tester hands
- **Zero** version conflicts (automated increment + Firebase validation)
- **100%** traceable releases (PR → release notes → notifications)

### Diagram Flow
<img width="1385" height="1757" alt="android cicd" src="https://github.com/user-attachments/assets/70c67eb3-1bdc-4358-9b32-cacf118281da" />

## 📸 Demo

### Android

* https://github.com/user-attachments/assets/ae495330-a0bb-41ef-9914-b737ec92f0e7

* <img width="270" height="600" alt="image" src="https://github.com/user-attachments/assets/5b010053-7dcd-4432-b4f5-49fa930c5387" />
* <img width="270" height="600" alt="image" src="https://github.com/user-attachments/assets/7c9d9a75-3113-4754-8113-2b95cae97209" />
* <img width="270" height="600" alt="image" src="https://github.com/user-attachments/assets/a184cfcc-1756-469c-990c-9748af9c7e6c" />
* <img width="270" height="600" alt="image" src="https://github.com/user-attachments/assets/3978901f-f7ae-4983-856a-ffc49e086cf9" />

### Desktop

* <img width="2560" height="1528" alt="image" src="https://github.com/user-attachments/assets/c273dc50-dd65-4d58-a8af-acdd6313cf9f" />
* <img width="2560" height="1528" alt="image" src="https://github.com/user-attachments/assets/841d38cb-0edb-4c4d-950c-b944bb8e5c95" />
* <img width="2560" height="1528" alt="image" src="https://github.com/user-attachments/assets/78468c2e-c6ce-4527-9d61-8008af6b3bef" />
* <img width="2560" height="1528" alt="image" src="https://github.com/user-attachments/assets/870b3895-f81a-46be-9c5e-109244e43e58" />

## 🚀 Getting Started

### Prerequisites
*   **Android Studio** (Koala or newer recommended)
*   **JDK 17** or higher
*   **Xcode** (for iOS target)

### Installation
1.  **Clone the repository**
    ```bash
    git clone https://github.com/yourusername/weatherio.git
    cd weatherio
    ```

2.  **Open in Android Studio**
    *   Wait for Gradle sync to complete.

3.  **Run the App**
    *   **Android**: Select `composeApp` configuration and run on an emulator/device.
    *   **Desktop**: Run the Gradle task `composeApp:run`.
    *   **iOS**: Open `iosApp/iosApp.xcodeproj` in Xcode or run via KMM plugin in Android Studio.

## 🤝 Acknowledgements

Special shoutout to **[Hoodlab](https://github.com/Hoodlab)** and their project **[Forecastly](https://github.com/Hoodlab/Forecastly)**. This project served as an excellent learning resource and starting point for Weatherio's development.
