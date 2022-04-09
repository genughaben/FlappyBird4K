package com.genughaben.games.flappybird.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.genughaben.games.flappybird.MainGame
import com.genughaben.games.flappybird.objects.*

class GameScreen(private var game: MainGame) : Screen {

    val TESTING = false

    private val tubes: MutableList<TubePair> = mutableListOf()
    val camera = OrthographicCamera()
    private val tubeCount = 4
    private val tubeDistance = 125f
    private val test: Texture = Texture("boundsBox.png")

    val batch: SpriteBatch = SpriteBatch()
    val bird: Bird = Bird(50f, 250f)

    val ground = Ground(this.getWindowLeftBoundary())
    var score = 0
    val scoreStage = ScoreStage()
    val mountain = Mountain()


    init {
        camera.setToOrtho(false, MainGame.WIDTH * 0.5f, MainGame.HEIGHT * 0.5f)
        for (i in 1 until tubeCount + 1) {
            tubes.add(TubePair(100 + i *(tubeDistance + TubePair.tubeWidth) ))
        }
        scoreStage.rebuild()
    }

    override fun show() {}

    override fun render(delta: Float) {
        update(delta)
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.8f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.projectionMatrix = camera.combined
        batch.begin()
        mountain.render(batch)
        if(TESTING) {
            batch.draw(test, bird.bounds.x, bird.bounds.y, bird.bounds.width, bird.bounds.height)
        }
        batch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y, bird.getSize(), bird.getSize())
        drawTubes()
        ground.draw(batch)

        batch.end()
        scoreStage.render(delta)
    }

    private fun update(delta: Float) {
        camera.position.x = bird.getPosition().x + 100f

        handleInput()
        ground.update(this.getWindowLeftBoundary())
        mountain.update(this.getWindowLeftBoundary())
        bird.update(delta)
        updateTubes(delta)

        if(!ground.above(bird.getPosition().y)) {
            gameOver()
        }
        scoreStage.rebuild()
        camera.update()
    }


    fun getWindowLeftBoundary() = camera.position.x - camera.viewportWidth * 0.5f

    fun drawTubes() {
        for (tubePair in tubes) {
            batch.draw(tubePair.lowerTubeTexture, tubePair.posLowerTube.x, tubePair.posLowerTube.y, TubePair.tubeWidth,TubePair.tubeHeight)
            batch.draw(tubePair.upperTubeTexture, tubePair.posUpperTube.x, tubePair.posUpperTube.y, TubePair.tubeWidth, TubePair.tubeHeight)
        }
    }

    fun updateTubes(delta: Float) {
        for (tubePair in tubes) {
            if( this.getWindowLeftBoundary() > tubePair.posLowerTube.x + TubePair.tubeWidth) {
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
        mountain.dispose()
    }
}