package org.hexworks.zircon.internal.component

import org.hexworks.zircon.api.behavior.Drawable
import org.hexworks.zircon.api.component.Component
import org.hexworks.zircon.api.data.Position
import org.hexworks.zircon.api.util.Maybe
import org.hexworks.zircon.internal.behavior.Focusable

/**
 * A [InternalComponent] is a GUI element which is used either to display information to the user
 * or to enable the user to interact with the program.
 * Components are basically a tree structure of GUI elements nested in each other.
 * The component hierarchy **always** has a [Container] as its root. A child [InternalComponent]
 * is **always** bounded by its parent. Containers are branches in this tree while components
 * are leaves. So for example a panel which is intended to be able to hold other components
 * like a label or a check box is a [Container] while a label which is only intended to
 * display information is a [InternalComponent].
 */
interface InternalComponent : Component, Drawable, Focusable {

    /**
     * Returns the innermost [InternalComponent] for a given [Position].
     * This means that if you call this method on a [Container] and it
     * contains a [InternalComponent] which intersects with `position` the
     * component will be returned instead of the container itself.
     * If no [InternalComponent] intersects with the given `position` an
     * empty [Maybe] is returned.
     */
    fun fetchComponentByPosition(position: Position): Maybe<out InternalComponent>

    /**
     * Tells whether this [Component] is attached to a [org.hexworks.zircon.api.component.ComponentContainer] or not.
     */
    fun isAttached(): Boolean

    /**
     * Signals this [Component] that it has been attached to a [org.hexworks.zircon.api.component.ComponentContainer].
     */
    fun signalAttached()
}
