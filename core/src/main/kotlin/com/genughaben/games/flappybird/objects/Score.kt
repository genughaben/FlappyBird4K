package com.genughaben.games.flappybird.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Stack
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.genughaben.games.flappybird.MainGame

class ScoreStage(): Disposable {

    val stage: Stage = Stage(StretchViewport(MainGame.WIDTH.toFloat(), MainGame.HEIGHT.toFloat()))
    val skin: Label.LabelStyle = Label.LabelStyle()
    var score: Int = 0

    init {
        skin.font = BitmapFont(Gdx.files.internal("skin.fnt"), Gdx.files.internal("skin.png"), false)
    }

    fun rebuild() {
        stage.clear()
        val stack = Stack()
        stack.setSize(MainGame.WIDTH.toFloat(), MainGame.HEIGHT.toFloat())
        stack.add(addScoreLablel())
        stage.addActor(stack)
    }

    private fun addScoreLablel(): Table {
        val table = Table()
        table.top()
        table.setFillParent(true)
        table.add(Label("Score: $score", skin)).expandX().padTop(10f)
        return table
    }

    fun render(delta: Float) {
        stage.act(delta)
        stage.draw()
    }

    fun updateScore(score: Int) {
        this.score = score
    }

    override fun dispose() {
        skin.font.dispose()
        stage.dispose()
    }
}