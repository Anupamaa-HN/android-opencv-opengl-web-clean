cmake_minimum_required(VERSION 3.10)
project(opedge)

set(CMAKE_CXX_STANDARD 17)

# Path to OpenCV Android SDK installation (adjust on your system)
if(NOT DEFINED OpenCV_DIR)
    set(OpenCV_DIR "${CMAKE_SOURCE_DIR}/opencv-android/sdk/native/jni")
endif()

find_package(OpenCV REQUIRED core imgproc imgcodecs)

add_library(
    native-lib
    SHARED
    app/src/main/cpp/native.cpp
)

target_link_libraries(
    native-lib
    ${OpenCV_LIBS}
)
