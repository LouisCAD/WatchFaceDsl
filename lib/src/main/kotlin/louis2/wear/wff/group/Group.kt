package louis2.wear.wff.group

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*

/**
 * A Group is a container for other elements. Child elements are rendered relative to the position, size, angle, and color of the group.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/group)
 */
@WffTagMarker
inline fun SceneOrGroup.group(
    id: String,
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    pivotX: Float = .5f,
    pivotY: Float = .5f,
    angle: Float = 0f,
    alpha: UByte = 0xFFu,
    scaleX: Float = 1f,
    scaleY: Float = 1f,
    renderMode: GROUP.RenderMode = GROUP.RenderMode.SOURCE,
    tintColor: UInt? = null,
    crossinline block: GROUP.() -> Unit
): Unit = GROUP(
    initialAttributes = attributesMapOf(
        "id", id,
        "x", x.toString(),
        "y", y.toString(),
        "width", width.toString(),
        "height", height.toString(),
        "pivotX", pivotX.takeUnless { angle == 0f }?.toString(),
        "pivotY", pivotY.takeUnless { angle == 0f }?.toString(),
        "angle", angle.takeUnless { it == 0f }?.toString(),
        "alpha", alpha.takeUnless { it == 0xFFu.toUByte() }?.toString(),
        "scaleX", scaleX.takeUnless { it == 1f }?.toString(),
        "scaleY", scaleY.takeUnless { it == 1f }?.toString(),
        "renderMode", renderMode.takeUnless { it == GROUP.RenderMode.SOURCE }?.name,
        "tintColor", tintColor?.let { "#${it.toString(radix = 16)}" },
    ),
    consumer = consumer
).visit(block)

class GROUP(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "Group",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), SceneOrGroup {
    enum class RenderMode {
        SOURCE, MASK, ALL
    }
}