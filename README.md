# Flutter versus Native versus KMP Performance

Comparing the performance of the same app in Flutter, native and Kotlin Multiplatform (KMP) on Android and iOS.

## 🐈 Sample app/architecture

The app will contain a single screen, load cat pictures from a public API and show them in a horizontal list.
Each picture can be clicked to show it zoomed in below the list. The architecture will be MVVM with a single
ViewModel.

## 📂 Technology overview

| Folder            | Technology             | UI Framework | Network lib                                         | Image lib                                                     |
|-------------------|------------------------|--------------|-----------------------------------------------------|---------------------------------------------------------------|
| `/flutter`        | Cross-platform, Dart   | Flutter      | [http.dart](https://pub.dev/packages/http)          | Flutter Image widget                                          |
| `/kmp`            | Cross-platform, Kotlin | Compose UI   | [Ktor](https://github.com/ktorio/ktor)              | [Kamel](https://github.com/Kamel-Media/Kamel)                 |
| `/native-ios`     | Native, Swift          | SwiftUI      | [Alamofire](https://github.com/Alamofire/Alamofire) | [AlamofireImage](https://github.com/Alamofire/AlamofireImage) |
| `/native-android` | Native, Kotlin         | Compose UI   | [Ktor](https://github.com/ktorio/ktor)              | [Kamel](https://github.com/Kamel-Media/Kamel)                 |

### 📦 Builds

Release builds are available in `/builds`.

## 📊 Benchmark results

// TODO