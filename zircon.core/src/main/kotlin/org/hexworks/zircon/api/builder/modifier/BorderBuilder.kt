package org.hexworks.zircon.api.builder.modifier

import org.hexworks.zircon.api.builder.Builder
import org.hexworks.zircon.api.modifier.Border
import org.hexworks.zircon.api.modifier.BorderPosition
import org.hexworks.zircon.api.modifier.BorderType

/**
 * Builds [Border]s.
 * Defaults:
 * - a simple border
 * - on all sides (top, right, bottom, left)
 */
data class BorderBuilder(
        private var borderType: BorderType = BorderType.SOLID,
        private var borderPositions: Set<BorderPosition> = BorderPosition.values().toSet())
    : Builder<Border> {

    override fun build(): Border = Border(
            borderType = borderType,
            borderPositions = borderPositions)

    override fun createCopy() = copy(borderPositions = borderPositions.toSet())

    fun borderType(borderType: BorderType) = also {
        this.borderType = borderType
    }

    fun borderPositions(vararg borderPositions: BorderPosition) = also {
        borderPositions(borderPositions.toSet())
    }

    fun borderPositions(borderPositions: Set<BorderPosition>) = also {
        this.borderPositions = borderPositions
    }

    companion object {
        fun newBuilder() = BorderBuilder()
    }
}
