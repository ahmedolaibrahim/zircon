package org.hexworks.zircon.internal.component.impl.wrapping

import org.hexworks.zircon.api.data.Position
import org.hexworks.zircon.api.data.Size
import org.hexworks.zircon.api.graphics.Symbols
import org.hexworks.zircon.api.builder.data.TileBuilder
import org.hexworks.zircon.api.color.TileColor
import org.hexworks.zircon.api.graphics.StyleSet
import org.hexworks.zircon.api.graphics.TileGraphic
import org.hexworks.zircon.api.shape.LineFactory
import org.hexworks.zircon.internal.component.WrappingStrategy

class ShadowWrappingStrategy(
        private val shadowChar: Char = DEFAULT_SHADOW_CHAR) : WrappingStrategy {

    override fun getOccupiedSize() = Size.create(1, 1)

    override fun getOffset() = Position.topLeftCorner()

    override fun apply(tileGraphic: TileGraphic, size: Size, offset: Position, style: StyleSet) {
        val tc = TileBuilder.newBuilder()
                .backgroundColor(TileColor.transparent())
                .foregroundColor(TileColor.create(100, 100, 100))
                .character(shadowChar)
                .build()
        LineFactory.buildLine(
                fromPoint = Position.create(1, 0),
                toPoint = Position.create(size.xLength - 1, 0))
                .toTileGraphics(tc, tileGraphic.tileset())
                .drawOnto(tileGraphic, Position.create(1, size.yLength - 1))
        LineFactory.buildLine(
                fromPoint = Position.create(0, 1),
                toPoint = Position.create(0, size.yLength - 1))
                .toTileGraphics(tc, tileGraphic.tileset())
                .drawOnto(tileGraphic, Position.create(size.xLength - 1, 1))
    }

    override fun isThemeNeutral() = true

    companion object {
        val DEFAULT_SHADOW_CHAR = Symbols.BLOCK_SPARSE
    }
}
