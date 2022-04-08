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

class GameScreen(private var game: MainGame) : Screen {
    private var batch: SpriteBatch
    private val camera: OrthographicCamera
    private val bird: Bird
    private val img: Texture

    init {
        this.game = game
        camera = OrthographicCamera()
        camera.setToOrtho(
            false,
            MainGame.WIDTH * 0.5f,
            MainGame.HEIGHT * 0.5f)
        batch = SpriteBatch();
        bird = Bird(50f, 50f)
        img = Texture("libgdx.png")

    }

    override fun show() {}
    override fun render(delta: Float) {
        update(delta)
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.projectionMatrix = camera.combined
        batch.begin()

        batch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y, bird.getSize(), bird.getSize()*1.7f)
        batch.end()
    }

    private fun update(delta: Float) {
        handleInput()
        bird.update(delta)
        camera.position.x = bird.getPosition().x + 80f
        camera.update()
    }

    private fun handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            bird.jump()
            System.out.println("Bird flapps")
        }
    }

    override fun resize(width: Int, height: Int) {}
    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
    override fun dispose() {}
}