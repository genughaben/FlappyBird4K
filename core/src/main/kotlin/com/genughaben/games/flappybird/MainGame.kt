package com.genughaben.games.flappybird

import com.badlogic.gdx.Game
import com.genughaben.games.flappybird.screens.GameScreen

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms.  */
class MainGame : Game() {

	companion object {
		val WIDTH: Int = 480
		val HEIGHT: Int = 800
		val SPEED = 100f;
	}

	override fun create() {
		setScreen(GameScreen(this))
	}

}