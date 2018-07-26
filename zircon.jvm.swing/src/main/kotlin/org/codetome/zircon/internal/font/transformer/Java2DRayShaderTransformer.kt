package org.codetome.zircon.internal.font.transformer

import com.jhlabs.image.RaysFilter
import org.codetome.zircon.api.data.Tile
import org.codetome.zircon.api.font.FontTextureRegion
import org.codetome.zircon.api.modifier.RayShade
import org.codetome.zircon.internal.font.FontRegionTransformer
import org.codetome.zircon.internal.font.impl.Java2DFontTextureRegion
import java.awt.image.BufferedImage

class Java2DRayShaderTransformer : FontRegionTransformer<BufferedImage> {

    override fun transform(region: FontTextureRegion<BufferedImage>, tile: Tile): FontTextureRegion<BufferedImage> {
        val rayShade: RayShade = tile.getModifiers().first{ it is RayShade } as RayShade
        return region.also {
            it.getBackend().let { backend ->
                backend.graphics.apply {
                    val filter = RaysFilter()
                    filter.opacity = rayShade.opacity
                    filter.threshold = rayShade.threshold
                    filter.strength = rayShade.strength
                    filter.raysOnly = rayShade.raysOnly
                    return Java2DFontTextureRegion(
                            cacheKey = tile.generateCacheKey(),
                            backend = filter.filter(region.getBackend(), null))
                }
            }
        }
    }
}
