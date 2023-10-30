package louis2.wear.wff.group.part.draw.gradient

import louis2.wear.wff.Color

@PublishedApi
internal fun FloatArray?.asGradientPositions(colors: List<Color>): String {
    if (this == null) return ""
    onEachIndexed { index, it ->
        require(it in 0f..1f) {
            "position at index $index is not within [0..1] ($it)"
        }
    }
    require(size == colors.size) { "colors and positions must have matching sizes" }
    return joinToString(separator = " ")
}

@PublishedApi
internal fun List<Color>.asGradientColors(): String {
    return joinToString(separator = " ") { it.xmlValue() }
}
