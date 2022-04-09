package com.genughaben.games.flappybird.objects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Disposable

class Mountain: Disposable {

    val mountain: Texture = Texture("mountain.png")
    val position = Vector2()
    val positionMountainForeground1 = Vector2(0f, 25f)
    val positionMountainForeground2 = Vector2(1000f, 25f)
    val positionMountainMidgroup1 = Vector2(-10f, 85f)
    val positionMountainMidgroup2 = Vector2(990f, 85f)
    val positionMountainBackground1 = Vector2(-20f, 135f)
    val positionMountainBackground2 = Vector2(980f, 135f)

    val width = 1000f
    val height = 230f

    fun draw(
        batch: SpriteBatch,
        offsetX1: Float,
        offsetX2: Float,
        offsetY: Float,
        tintColor: Float,
        parallaxSpeedX: Float
    ) {
        batch.setColor(tintColor, tintColor, tintColor, 1f)
        batch.draw(mountain, position.x*parallaxSpeedX + offsetX1, position.y + offsetY, width, height)
        batch.draw(mountain, position.x*parallaxSpeedX + offsetX2, position.y + offsetY, width, height)
    }

    fun update(x: Float) {
        position.x = x
        position.y = 0f
        if(position.x * 0.7f > positionMountainForeground1.x + width) {
            println("a")
            positionMountainForeground1.x += 2000
        }
        else if(position.x * 0.7f > positionMountainForeground2.x + width) {
            println("b")
            positionMountainForeground2.x += 2000
        }
        else if(position.x * 0.5f > positionMountainMidgroup1.x + width) {
            println("c")
            positionMountainMidgroup1.x += 2000
        }
        else if(position.x * 0.5f > positionMountainMidgroup2.x + width) {
            println("d")
            positionMountainMidgroup2.x += 2000
        }
        else if(position.x * 0.2f > positionMountainBackground1.x + width) {
            println("e")
            positionMountainBackground1.x += 2000
        }
        else if(position.x * 0.2f > positionMountainBackground2.x + width) {
            println("f")
            positionMountainBackground2.x += 2000
        }
    }

    fun render(batch: SpriteBatch) {
        draw(batch, positionMountainBackground1.x, positionMountainBackground2.x, positionMountainBackground1.y, 0.3f, 0.8f)
        draw(batch, positionMountainMidgroup1.x, positionMountainMidgroup2.x, positionMountainMidgroup1.y, 0.5f, 0.5f)
        draw(batch, positionMountainForeground1.x, positionMountainForeground2.x, positionMountainForeground1.y, 0.7f, 0.3f)
    }

    override fun dispose() {
        mountain.dispose()
    }
}