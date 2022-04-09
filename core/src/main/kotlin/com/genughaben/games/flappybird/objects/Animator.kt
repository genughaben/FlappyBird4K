package com.genughaben.games.flappybird.objects

import com.badlogic.gdx.graphics.g2d.TextureRegion


class Animator(
    private val region: TextureRegion,
    private val frameCount: Int,
    private val cycleTime: Float) {

    private var currentFrameTime: Float = 0f

    val frameWidth = region.regionWidth / frameCount
    val frames: MutableList<TextureRegion> = (0 until frameCount).toList()
        .map {
            TextureRegion(region, it * frameWidth, 0, frameWidth, region.regionHeight)
        }
        .toMutableList()

    val maxFrameTime = this.cycleTime / frameCount
    var frame = 0

    fun update(delta: Float) {
        currentFrameTime += delta
        if (currentFrameTime > maxFrameTime) {
            frame += 1
            currentFrameTime = 0f
        }

        if (frame >= this.frameCount) {
            frame = 0
        }
    }

    fun getFrame(): TextureRegion {
        return frames[frame]
    }
}