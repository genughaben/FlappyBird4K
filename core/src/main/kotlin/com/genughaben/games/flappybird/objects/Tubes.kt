package com.genughaben.games.flappybird.objects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Disposable
import com.genughaben.games.flappybird.MainGame
import java.util.*

class Tubes(x: Float): Disposable {



    val heightRange = 220;
    val tubeGap = 100;
    val lowestOpening = 70
    val tubeWidth = 52

    val random: Random = java.util.Random()

    val downTubeTexture: Texture = Texture("TubeDown.png")
    val upTubeTexture: Texture = Texture("TubeUp.png")

    val topTubeHeight = random.nextInt(heightRange) + tubeGap + lowestOpening
    private var posTopTube: Vector2 = Vector2(x, (topTubeHeight).toFloat())
    private var posDownTube: Vector2 = Vector2(x, MainGame.HEIGHT.toFloat() - topTubeHeight)

    fun update(delta: Float) {
        posTopTube.x -= delta * MainGame.SPEED
        posDownTube.x -= delta * MainGame.SPEED
    }

    override fun dispose() {
    }
}