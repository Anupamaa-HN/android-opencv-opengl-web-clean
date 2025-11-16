cmake_minimum_required(VERSION 3.10)
project(opedge)

set(CMAKE_CXX_STANDARD 17)

# Use OpenCV from repo
set(OpenCV_DIR "${CMAKE_SOURCE_DIR}/opencv-android/sdk/native/jni")

find_package(OpenCV REQUIRED core imgproc imgcodecs)

# Include OpenCV headers
include_directories(${OpenCV_INCLUDE_DIRS})

add_library(
    native-lib
    SHARED
    app/src/main/cpp/native.cpp
)

target_link_libraries(
    native-lib
    ${OpenCV_LIBS}
)
