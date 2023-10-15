package louis2.wear.wff

import kotlinx.html.HtmlTagMarker
import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit

/**
 * A container for user-defined bitmap fonts.
 *
 * Introduced in Wear OS 4.
 *
 * The BitmapFonts element must contain at least one BitmapFont inner element.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/bitmap-fonts)
 */
@HtmlTagMarker
inline fun SCENE.bitmapFonts(
    crossinline block: BITMAPFONTS.() -> Unit
) : Unit = BITMAPFONTS(
    initialAttributes = emptyMap(),
    consumer = consumer
).visit(block)

/**
 * Specifies a particular user-defined bitmap font.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/bitmap-fonts)
 */
@HtmlTagMarker
inline fun BITMAPFONTS.bitmapFont(
    name: String,
    crossinline block: BITMAPFONTS.BITMAPFONT.() -> Unit
) : Unit = BITMAPFONTS.BITMAPFONT(
    initialAttributes = attributesMapOf("name", name),
    consumer = consumer
).visit(block)

/**
 * Specifies a particular user-defined bitmap font.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/bitmap-fonts)
 */
@HtmlTagMarker
fun BITMAPFONTS.BITMAPFONT.character(
    name: String,
    resource: String,
    width: Int,
    height: Int,
) : Unit = BITMAPFONTS.BITMAPFONT(
    initialAttributes = attributesMapOf(
        "name",
        name,
        "resource",
        resource,
        "width",
        width.toString(),
        "height",
        height.toString(),
    ),
    consumer = consumer
).visit {}

class BITMAPFONTS(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "BitmapFonts",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
) {
    class BITMAPFONT(
        initialAttributes: Map<String, String>,
        override val consumer: TagConsumer<*>
    ) : XMLTag(
        tagName = "BitmapFont",
        consumer = consumer,
        initialAttributes = initialAttributes,
        namespace = null,
        inlineTag = false,
        emptyTag = false
    ) {
        class CHARACTER(
            initialAttributes: Map<String, String>,
            override val consumer: TagConsumer<*>
        ) : XMLTag(
            tagName = "BitmapCharacter",
            consumer = consumer,
            initialAttributes = initialAttributes,
            namespace = null,
            inlineTag = false,
            emptyTag = false
        )
    }
}
