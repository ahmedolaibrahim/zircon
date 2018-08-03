package org.codetome.zircon.gui.swing.impl

import org.codetome.zircon.api.grid.TileGrid
import java.awt.Canvas
import javax.swing.JFrame

class SwingFrame(val grid: TileGrid,
                 canvas: Canvas = Canvas()) : JFrame() {

    val renderer: SwingCanvasRenderer

    init {
        add(canvas)
        renderer = SwingCanvasRenderer(canvas, this, grid)
    }
}