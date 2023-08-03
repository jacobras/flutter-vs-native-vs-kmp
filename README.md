# Flutter versus Native versus KMP Performance

Comparing the performance of the same app in Flutter, native and Kotlin Multiplatform (KMP) on Android and iOS.

## 🐈 Sample app

The app will contain a single screen, load cat pictures from a public API and show them in a horizontal list.
Each picture can be clicked to show it zoomed in below the list.

## 📂 Technology overview

| Folder            | Technology             | UI Framework | Network lib                                         | Image lib                                                     |
|-------------------|------------------------|--------------|-----------------------------------------------------|---------------------------------------------------------------|
| `/flutter`        | Cross-platform, Dart   | Flutter      | [http.dart](https://pub.dev/packages/http)          | Built-in image widget                                         |
| `/kmp`            | Cross-platform, Kotlin | Compose UI   | [Ktor](https://github.com/ktorio/ktor)              | [Kamel](https://github.com/Kamel-Media/Kamel)                 |
| `/native-ios`     | Native, Swift          | SwiftUI      | [Alamofire](https://github.com/Alamofire/Alamofire) | [AlamofireImage](https://github.com/Alamofire/AlamofireImage) |
| `/native-android` | Native, Kotlin         | Compose UI   | Built-in kotlin.io                                  | [Coil](https://github.com/coil-kt/coil)                       |

### 📦 Builds

Release builds are available in `/builds`.

## 📊 Benchmark results

### 📦 App size

Android APK created using Bundletool, for a Pixel 4a.

| Technology  | Android | iOS |
|-------------|---------|-----|
| **Native**  |         |     |
| **KMP**     |         |     |
| **Flutter** |         |     |

### 🚀 Startup

Android benchmarks were executed on a Pixel 4a.

| App                                     | min. | median | max. |
|-----------------------------------------|------|--------|------|
| **Native** Android<br/>**Native** iOS   |      |        |      |
| **KMP** Android<br/>**KMP** iOS         |      |        |      |
| **Flutter** Android<br/>**Flutter** iOS |      |        |      |

// TODO