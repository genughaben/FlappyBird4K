package com.genughaben.games.flappybird.objects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Disposable

class Ground(initialWindowLeftBoundaryX: Float): Disposable {

    val GROUND_Y_OFFSET = -80f
    val GROUND_HEIGHT = 122f
    val GROUND_WIDTH = 400f
    val groundTexture: Texture = Texture("ground.png")
    val groundPositionFirst: Vector2 = Vector2(initialWindowLeftBoundaryX, GROUND_Y_OFFSET)
    val groundPositionSecond: Vector2 = Vector2(initialWindowLeftBoundaryX + GROUND_WIDTH, GROUND_Y_OFFSET)

    fun update(windowLeftBoundaryX: Float) {
        if(windowLeftBoundaryX > groundPositionFirst.x + GROUND_WIDTH) {
            groundPositionFirst.add(GROUND_WIDTH*2, 0f)
        }
        if(windowLeftBoundaryX > groundPositionSecond.x + GROUND_WIDTH) {
            groundPositionSecond.add(GROUND_WIDTH*2, 0f)
        }
    }

    fun draw(batch: SpriteBatch) {
        batch.draw(groundTexture, groundPositionFirst.x, groundPositionFirst.y, GROUND_WIDTH, GROUND_HEIGHT)
        batch.draw(groundTexture, groundPositionSecond.x, groundPositionSecond.y, GROUND_WIDTH, GROUND_HEIGHT)
    }

    override fun dispose() {
        groundTexture.dispose()
    }

    fun getGroundHeight(): Float {
        return GROUND_Y_OFFSET + GROUND_HEIGHT
    }

    fun above(positionY: Float): Boolean {
        return positionY >= this.getGroundHeight()
    }

}