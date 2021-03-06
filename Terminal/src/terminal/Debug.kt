package terminal

import gameloader.*
import gameloader.base.Point

fun main(args: Array<String>) {
    GameList().set(0)
    Game.setup()

    while(Game.inPlay) {
        debug()
        println(Game.redTurn)
        input()
    }
}

private fun debug() {
    println()
    for(p in Game.changed) {
        println(Game.get(p).debug())
    }
}

private fun input() {
    val line = readLine()?.split(" ")
    if(line != null) {
        val p = Point(line[0].toInt(), line[1].toInt())
        Game.select(p)
    }
}

