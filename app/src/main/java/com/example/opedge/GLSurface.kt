package com.example.opedge

import android.content.Context
import android.opengl.GLSurfaceView

class GLSurface(context: Context) : GLSurfaceView(context) {

    private val renderer: GLRenderer

    init {
        setEGLContextClientVersion(2)
        renderer = GLRenderer(context)
        setRenderer(renderer)
        renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
    }
}
