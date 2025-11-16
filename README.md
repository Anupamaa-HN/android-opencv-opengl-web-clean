📱 Android + OpenCV (C++) + OpenGL ES + Web Viewer
Real-Time Edge Detection Viewer — RnD Intern Assessment

This project implements a complete pipeline for real-time camera frame processing on Android using:

OpenCV (C++)
JNI (NDK)
OpenGL ES 2.0
TypeScript (Web Viewer)

The app captures camera frames, sends them to C++ for Canny Edge Detection, and renders the processed output using OpenGL ES.
A separate web viewer displays a sample processed frame with FPS/resolution overlay.

⭐ Features Implemented
📸 Android (Kotlin / Java)

1. Camera2 + TextureView based camera capture
2. Frame extraction via ImageReader → ByteArray
3. Smooth pipeline designed for real-time (10–20 FPS)
4. JNI bridge to native C++ for processing
5. OpenGL ES 2.0 renderer:
   Textured quad
   Updates texture with processed frame
   Grayscale edge-detection rendering

🧪 Native C++ (OpenCV + NDK)

1. Full OpenCV C++ pipeline
2. Conversion from NV21 → BGR
3. cv::Canny() edge detection
4. Returns processed grayscale data to Kotlin
5. Integrated using:
   CMake
   OpenCV shared libraries (.so)
   OpenCV headers (opencv2/*)

ABIs included:

1. arm64-v8a
2. armeabi-v7a
3. x86
4. x86_64
Fully compatible with devices + emulators.

🎨 OpenGL ES Rendering

1. 2D textured quad renderer
2. Uploads processed frame as texture
3. Efficient GLSurface + GLRenderer classes
4. Updates per frame for real-time edge preview

🌐 Web Viewer (TypeScript)

Simple debugging UI that:
Displays a base64 sample processed frame
Shows FPS counter
Shows frame resolution
Compiles using TypeScript → JavaScript
Runs using a simple local web server

Commands:

npm install
npm run build
npm run start


🧠 Architecture Overview
📌 Frame Flow Pipeline
Camera2 (NV21)
      ↓
TextureView + ImageReader
      ↓
ByteArray (Kotlin)
      ↓
JNI → C++ (native.cpp)
      ↓
OpenCV Processing (Canny edges)
      ↓
Processed Buffer (grayscale)
      ↓
GLRenderer (OpenGL ES)
      ↓
Rendered to screen



📌 JNI Layer

1. Kotlin → C++
Passes raw NV21 camera bytes
Passes width + height
2. C++ → Kotlin
Returns processed edge data
As a jbyteArray


📌 Native C++ (OpenCV) Logic
cv::Mat yuv(height + height/2, width, CV_8UC1, inputBytes);
cv::cvtColor(yuv, bgr, cv::COLOR_YUV2BGR_NV21);
cv::Canny(bgr, edges, 80, 150);

The result is sent back for OpenGL rendering.


📌 Web Viewer
index.html
src/index.ts
dist/index.js


Displays:
Processed frame (base64)
FPS (via requestAnimationFrame)
Resolution


🗂 Repository Structure
/
├── app/
│   ├── src/main/java/com/example/opedge/
│   │   ├── MainActivity.kt
│   │   ├── GLSurface.kt
│   │   ├── GLRenderer.kt
│   │   ├── NativeLib.kt
│   │   └── ImageUtils.kt
│   ├── src/main/cpp/native.cpp
│   └── build.gradle
│
├── opencv-android/
│   └── sdk/native/jni/
│       ├── include/
│       ├── abi-arm64-v8a/
│       ├── abi-armeabi-v7a/
│       ├── abi-x86/
│       └── abi-x86_64/
│
├── web/
│   ├── index.html
│   ├── package.json
│   ├── tsconfig.json
│   └── src/index.ts
│
├── CMakeLists.txt
└── README.md

⚙️ Setup Instructions
1. Prerequisites
Android Studio / SDK
Android NDK r25+
CMake 3.10+
Node.js (for web viewer)

2. Android Setup

OpenCV SDK already included in:
opencv-android/sdk/native/jni/

Build APP:
Windows:

Bash
cd app
gradlew.bat assembleDebug


Linux/macOS:
bash
cd app
./gradlew assembleDebug


Output APK:
app/build/outputs/apk/debug/app-debug.apk


Install:
adb install -r app-debug.apk


3. Web Viewer Setup
cd web
npm install
npm run build
npm run start

Open browser:
http://localhost:8080

📝 Notes

All OpenCV native libraries (.so files) and headers are included.
Project uses OpenGL ES 2.0 for portability.
The code is organized for clarity, not production-grade UI.
Separate modules for Android, native, and web components.

🎯 Final Deliverable

This repository includes:

✔ Working Android app with real-time OpenCV edge detection
✔ Native C++ integration via JNI
✔ OpenGL rendering pipeline
✔ Web viewer built using TypeScript
✔ Full OpenCV Android SDK (headers + libs)
✔ Clean commit history & documentation
