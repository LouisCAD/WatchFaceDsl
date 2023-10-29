package louis2.wear.wff.group.configuration

import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visit
import louis2.wear.wff.*

/**
 * Allows for conditionally showing a set of text and graphical elements based on the user's current selection from a list on a watch face options screen. This element references a user configuration ListConfiguration element that's defined elsewhere in the watch face file.
 *
 * Introduced in Wear OS 4.
 *
 * [AndroidX doc](https://developer.android.com/training/wearables/wff/group/configuration/list-configuration)
 */
@WffTagMarker
inline fun SupportsListConfiguration.listConfiguration(
    id: String,
    crossinline block: LISTCONFIGURATION.() -> Unit
): Unit = LISTCONFIGURATION(
    initialAttributes = attributesMapOf("id", id),
    parentContainer = this,
    consumer = consumer
).visit(block)

@WffTagMarker
inline fun LISTCONFIGURATION.listOption(
    id: Int,
    crossinline block: LISTCONFIGURATION.LISTOPTION.() -> Unit
): Unit = LISTCONFIGURATION.LISTOPTION(
    initialAttributes = attributesMapOf("id", id.toString()),
    parentContainer = this,
    consumer = consumer
).visit(block)

class LISTCONFIGURATION(
    initialAttributes: Map<String, String> = emptyMap(),
    private val parentContainer: Container,
    override val consumer: TagConsumer<*>
) : XMLTag(
    tagName = "ListConfiguration",
    consumer = consumer,
    initialAttributes = initialAttributes,
    namespace = null,
    inlineTag = false,
    emptyTag = false
), Container {
    override val width: Int get() = parentContainer.width
    override val height: Int get() = parentContainer.height

    class LISTOPTION(
        initialAttributes: Map<String, String> = emptyMap(),
        private val parentContainer: Container,
        override val consumer: TagConsumer<*>
    ) : XMLTag(
        tagName = "ListOption",
        consumer = consumer,
        initialAttributes = initialAttributes,
        namespace = null,
        inlineTag = false,
        emptyTag = false
    ), SupportsPart, SupportsGroup, SupportsConditions, SupportsClock {
        override val width: Int get() = parentContainer.width
        override val height: Int get() = parentContainer.height
    }
}
