# Flutter versus Native versus KMP Performance

Comparing the performance of the same app in Flutter, native and Kotlin Multiplatform (KMP) on Android and iOS.

## 🐈 Sample app

The app will contain a single screen, load cat pictures from a public API and show them in a horizontal list.
Each picture can be clicked to show it zoomed in below the list.

## 📂 Technology overview

| Folder            | Technology             | UI Framework             | Network lib                                         | Image lib                                                     |
|-------------------|------------------------|--------------------------|-----------------------------------------------------|---------------------------------------------------------------|
| `/flutter`        | Cross-platform, Dart   | Flutter                  | [http.dart](https://pub.dev/packages/http)          | Built-in image widget                                         |
| `/kmp`            | Cross-platform, Kotlin | Compose UI Multiplatform | [Ktor](https://github.com/ktorio/ktor)              | [Kamel](https://github.com/Kamel-Media/Kamel)                 |
| `/native-ios`     | Native, Swift          | SwiftUI                  | [Alamofire](https://github.com/Alamofire/Alamofire) | [AlamofireImage](https://github.com/Alamofire/AlamofireImage) |
| `/native-android` | Native, Kotlin         | Compose UI               | [Ktor](https://github.com/ktorio/ktor)              | [Coil](https://github.com/coil-kt/coil)                       |

### 📦 Builds

Release builds are available in `/builds`.

## 📊 Benchmark results

### 📦 App size

Android sizes were measured by running Bundletool* on the signed, minified release bundles to create an APK set for a
Pixel 4a, and then running `get-size` on that APK set.

| Technology                      | Android  | iOS     |
|---------------------------------|----------|---------|
| **Native** (Compose/SwiftUI)    | 1.113 MB | // TODO |
| **KMP** (Compose Multiplatform) | 1.463 MB | // TODO |
| **Flutter**                     | 6.828 MB | // TODO |

*commands used:

* `bundletool build-apks --connected-device --bundle=release.aab --output=release.apks`
* `bundletool get-size total --apks=release.apks`

### 🚀 Startup

Android startup benchmarks were executed
using [Macrobenchmark](https://developer.android.com/topic/performance/benchmarking/macrobenchmark-overview), 5 times on
a Pixel 4a. Test suite was run twice in alternating order.

| App                                     | min.                 | median               | max.                 |
|-----------------------------------------|----------------------|----------------------|----------------------|
| **Native** Android<br/>**Native** iOS   | 408.7 ms<br/>// TODO | 413.1 ms<br/>// TODO | 423.1 ms<br/>// TODO |
| **KMP** Android<br/>**KMP** iOS         | 403.6 ms<br/>// TODO | 425.3 ms<br/>// TODO | 466.4 ms<br/>// TODO |
| **Flutter** Android<br/>**Flutter** iOS | 600.5 ms<br/>// TODO | 634.2 ms<br/>// TODO | 649.8 ms<br/>// TODO |