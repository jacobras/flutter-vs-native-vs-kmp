# Flutter versus Native versus KMP: a Performance Comparison

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
| `/native-android` | Native, Kotlin         | Compose UI               | [Ktor](https://github.com/ktorio/ktor)              | [Kamel](https://github.com/Kamel-Media/Kamel)                 |

### 📦 Builds

Release builds are available in `/builds`.

## 📊 Benchmark results

### 📦 App size

Android sizes were measured by running Bundletool* on the signed, minified release bundles to create an APK set for a
Pixel 4a, and then running `get-size` on that APK set. iOS sizes were measured running the App Thinning Size report.

| Technology                   | Android  | iOS     | Remarks                                                      |
|------------------------------|----------|---------|--------------------------------------------------------------|
| **Native** (Compose/SwiftUI) | 1.463 MB | 1.7 MB  |                                                              |
| **KMP** (Compose)            | 1.463 MB | 24.8 MB | Includes Skia on iOS, where Android relies on built-in Skia. |
| **Flutter**                  | 6.828 MB | 48.1 MB | Also includes Skia on iOS + Flutter framework.               |

Note: due to https://github.com/Kamel-Media/Kamel/issues/47 the Kamel library isn't being minified. Once that's fixed,
the Android native and Android KMP builds will both be smaller.

*commands used:

* `bundletool build-apks --connected-device --bundle=release.aab --output=release.apks`
* `bundletool get-size total --apks=release.apks`

### 🚀 Startup

Android startup benchmarks were executed
using [Macrobenchmark](https://developer.android.com/topic/performance/benchmarking/macrobenchmark-overview), 5 times on
a Pixel 4a. Test suite was run twice
in alternating order. I couldn't get iOS benchmarks to run. They ran, but didn't collect any reports.

| App                 | min.     | median   | max.     |
|---------------------|----------|----------|----------|
| **Native** Android  | 408.7 ms | 413.1 ms | 423.1 ms |
| **KMP** Android     | 403.6 ms | 425.3 ms | 466.4 ms |
| **Flutter** Android | 600.5 ms | 634.2 ms | 649.8 ms |