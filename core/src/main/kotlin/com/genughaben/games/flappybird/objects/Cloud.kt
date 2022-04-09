package objectives

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Disposable
import com.genughaben.games.flappybird.MainGame

private class Cloud {
    private var regCloud: Texture? = null
    val position = Vector2()
    val speed = Vector2()
    private val width = 65
    private val height = 50

    fun setRegion(region: Texture?) {
        regCloud = region
    }

    fun render(batch: SpriteBatch) {
        batch.draw(regCloud, position.x, position.y, width.toFloat(), height.toFloat())
    }

    fun updateCloud(deltaTime: Float) {
        position.x += speed.x * deltaTime
    }
}

class Clouds(camPosX: Float) : Disposable {

    private val cloud1 = Texture(Gdx.files.internal("cloud01.png"))
    private val cloud2 = Texture(Gdx.files.internal("cloud02.png"))
    private val cloud3 = Texture(Gdx.files.internal("cloud03.png"))
    private val regClouds: Array<Texture> = Array(3)
    val numClouds = 10
    private val clouds: Array<Cloud> = Array(2 * numClouds)

    init {
        regClouds.add(cloud1)
        regClouds.add(cloud2)
        regClouds.add(cloud3)
        for (i in 0 until numClouds) {
            val cloud = spawnCloud(camPosX)
            cloud.position.x = (i * 100).toFloat()
            clouds.add(cloud)
        }
    }

    private fun spawnCloud(camPosX: Float): Cloud {
        val cloud = Cloud()
        cloud.setRegion(regClouds.random())
        val pos = Vector2(
            camPosX + MainGame.WIDTH * 0.5f + 10,
            MainGame.HEIGHT * 0.5f - 85 + MathUtils.random(0.0f, 35.5f) * if (MathUtils.randomBoolean()) 1 else -1
        )
        cloud.position.set(pos)
        val speed = Vector2()
        speed.x = -1f * (3.5f + MathUtils.random(0.5f, 3.5f))
        cloud.speed.set(speed)
        return cloud
    }

    fun update(camPosX: Float, campWidth: Float, deltaTime: Float) {
        for (i in clouds.size - 1 downTo 0) {
            val cloud = clouds[i]
            cloud.updateCloud(deltaTime)
            if (cloud.position.x < camPosX - (campWidth + 10)) {
                clouds.removeIndex(i)
                clouds.add(spawnCloud(camPosX))
            }
        }
    }

    fun render(batch: SpriteBatch) {
        for (cloud in clouds) cloud.render(batch)
    }

    override fun dispose() {
        clouds.clear()
        cloud1.dispose()
        cloud2.dispose()
        cloud3.dispose()
        regClouds.clear()
    }
}