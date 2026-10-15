
package org.setu.placemarks

interface OnMarkListener {

    fun onMarkClick(mark: PlacedMark)

    fun onMarkLongClick(mark: PlacedMark): Boolean
}


