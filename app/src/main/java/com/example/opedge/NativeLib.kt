package com.example.opedge

object NativeLib {

    init {
        System.loadLibrary("native-lib")
    }

    external fun processFrame(data: ByteArray, width: Int, height: Int): ByteArray
}
