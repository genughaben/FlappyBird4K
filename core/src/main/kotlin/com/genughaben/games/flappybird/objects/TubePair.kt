package com.genughaben.games.flappybird.objects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Disposable
import com.genughaben.games.flappybird.MainGame
import java.util.*

class TubePair(x: Float): Disposable {

    companion object {
        val minHeight = 50f
        val minGap = 120f
        val heightRange = 120
        val gapRange = 70
        val tubeWidth = 51f
        val tubeHeight = 160/30 * tubeWidth * 1.5f
    }

    val random: Random = Random()

    val lowerTubeTexture: Texture = Texture("LowerTubeFacingUp.png")
    val upperTubeTexture: Texture = Texture("UpperTubeFacingDown.png")

    var heightLowerTube = (minHeight + random.nextInt(heightRange))
    var posLowerTube: Vector2 = Vector2(x, -tubeHeight + heightLowerTube)

    var gap = minGap + random.nextInt(gapRange)

    var posUpperTube: Vector2 = Vector2(x, (heightLowerTube + gap))

    var boundsLower: Rectangle = Rectangle(posLowerTube.x, posLowerTube.y, tubeWidth, tubeHeight)
    var boundsUpper: Rectangle = Rectangle(posUpperTube.x, posUpperTube.y, tubeWidth, tubeHeight)

    fun reposition(x: Float) {
        heightLowerTube = (minHeight + random.nextInt(heightRange))
        posLowerTube = Vector2(x, -tubeHeight + heightLowerTube)

        gap = minGap + random.nextInt(gapRange)

        posUpperTube = Vector2(x, (heightLowerTube + gap))
    }

    override fun dispose() {
        lowerTubeTexture.dispose()
        upperTubeTexture.dispose()
    }

    fun update(delta: Float) {
        posUpperTube.x -= delta * MainGame.SPEED
        posLowerTube.x -= delta * MainGame.SPEED
        boundsLower.setPosition(posLowerTube.x, posLowerTube.y)
        boundsUpper.setPosition(posUpperTube.x, posUpperTube.y)
    }

    fun collide(bird: Bird): Boolean {
        return bird.bounds.overlaps(boundsLower) || bird.bounds.overlaps(boundsUpper)
    }
}