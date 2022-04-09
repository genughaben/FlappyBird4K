package com.genughaben.games.flappybird

import com.badlogic.gdx.Game
import com.genughaben.games.flappybird.screens.GameScreen

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms.  */
class MainGame : Game() {

	companion object {
		val WIDTH: Int = 600
		val HEIGHT: Int = 1000
		val SPEED = 50f
	}

	override fun create() {
		setScreen(GameScreen(this))
	}

}