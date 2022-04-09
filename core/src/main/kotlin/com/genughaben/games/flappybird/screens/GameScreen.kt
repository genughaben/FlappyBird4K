package com.genughaben.games.flappybird.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.genughaben.games.flappybird.MainGame
import com.genughaben.games.flappybird.objects.Bird
import com.genughaben.games.flappybird.objects.Ground
import com.genughaben.games.flappybird.objects.ScoreStage
import com.genughaben.games.flappybird.objects.TubePair

class GameScreen(private var game: MainGame) : Screen {

    val TESTING = false

    private val tubes: MutableList<TubePair> = mutableListOf()
    val camera = OrthographicCamera()
    private val tubeCount = 4
    private val tubeDistance = 125f
    private val test: Texture = Texture("boundsBox.png")

    val batch: SpriteBatch = SpriteBatch()
    val bird: Bird = Bird(50f, 250f)

    val ground = Ground(this.getLeftWindowBoundary())
    var score = 0
    val scoreStage = ScoreStage()


    init {
        camera.setToOrtho(false, MainGame.WIDTH * 0.5f, MainGame.HEIGHT * 0.5f)
        for (i in 1 until tubeCount + 1) {
            tubes.add(TubePair(100 + i *(tubeDistance + TubePair.tubeWidth) ))
        }
        scoreStage.rebuild()
    }

    fun getLeftWindowBoundary() = camera.position.x - camera.viewportWidth * 0.5f

    override fun show() {}

    override fun render(delta: Float) {
        update(delta)
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.projectionMatrix = camera.combined
        batch.begin()
        if(TESTING) {
            batch.draw(test, bird.bounds.x, bird.bounds.y, bird.bounds.width, bird.bounds.height)
        }
        batch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y, bird.getSize(), bird.getSize())
        drawTubes()
        ground.draw(batch)
        batch.end()

        scoreStage.render(delta)
    }

    fun drawTubes() {
        for (tubePair in tubes) {
            batch.draw(tubePair.lowerTubeTexture, tubePair.posLowerTube.x, tubePair.posLowerTube.y, TubePair.tubeWidth,TubePair.tubeHeight)
            batch.draw(tubePair.upperTubeTexture, tubePair.posUpperTube.x, tubePair.posUpperTube.y, TubePair.tubeWidth, TubePair.tubeHeight)
        }
    }

    private fun update(delta: Float) {
        handleInput()
        bird.update(delta)
        updateTubes(delta)
        camera.position.x = bird.getPosition().x + 100f
        camera.update()
        ground.update(this.getLeftWindowBoundary())
        if(!ground.above(bird.getPosition().y)) {
            gameOver()
        }
        scoreStage.rebuild()
    }

    fun updateTubes(delta: Float) {
        for (tubePair in tubes) {
            if( this.getLeftWindowBoundary() > tubePair.posLowerTube.x + TubePair.tubeWidth) {
                score++
                scoreStage.updateScore(score)
                // sound effekt
                tubePair.reposition(tubePair.posLowerTube.x + ((tubeDistance + TubePair.tubeWidth) * tubeCount))
            }
            tubePair.update(delta)
            if(tubePair.collide(bird)) {
                gameOver()
            }
        }
    }

    private fun gameOver() {
        score--
        scoreStage.updateScore(score)
//        if(score > game.getHighScore()) {
//            game.setHighScore(score)
//        }
        if(score < 0){
            game.screen = GameScreen(game)
        }
    }

    private fun handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            bird.jump()
        }
    }

    override fun resize(width: Int, height: Int) {}
    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
    override fun dispose() {
        bird.dispose()
        ground.dispose()
        tubes.forEach(TubePair::dispose)
        test.dispose()
        batch.dispose()
        scoreStage.dispose()
    }
}