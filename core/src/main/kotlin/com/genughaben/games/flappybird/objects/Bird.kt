package com.genughaben.games.flappybird.objects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Disposable
import com.genughaben.games.flappybird.MainGame

class Bird(x: Float, y: Float): Disposable {

    private val gravity = -9.8f
    val movement = MainGame.SPEED
    private val position: Vector2 = Vector2(x, y)
    private val velocity: Vector2 = Vector2(0f, 0f)
    private val size: Float = 30f
    private val texture: Texture = Texture("slice1.png")
    val bounds: Rectangle = Rectangle(position.x, position.y, size, size)

    override fun dispose() {
        texture.dispose()
    }

    fun update(delta: Float) {
        if (delta > 0) {
            if(position.y > 0) {
                velocity.add(0f, gravity)
            }
            velocity.scl(delta)
            if(position.y < MainGame.HEIGHT.toFloat()/2 - size/2) {
                position.add(movement * delta, velocity.y)
            } else {
                position.add(movement * delta, gravity)
            }

            if(position.y < 0f) {
                position.y = 0f
            }
            velocity.scl(1f / delta)
            bounds.setPosition(position.x, position.y)
        }
    }

    fun jump() {
        if(position.y < MainGame.HEIGHT.toFloat()/2 - size) {
            velocity.y = 250f
        } else {
            velocity.y = 0f
        }
    }

    fun getPosition(): Vector2 {
        return position
    }

    fun getSize(): Float {
        return size
    }

    fun getTexture(): Texture {
        return texture
    }


}