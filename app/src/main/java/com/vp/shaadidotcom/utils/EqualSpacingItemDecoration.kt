package com.vp.shaadidotcom.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EqualSpacingItemDecoration(val spacing: Int, val mode: Int) : RecyclerView.ItemDecoration() {

    companion object {

        val HORIZONTAL = 0
        val VERTICAL = 1
        val GRID = 2
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildViewHolder(view).adapterPosition
        val itemCount = state.itemCount
        val layoutManager = parent.layoutManager
        setSpacing(outRect,position,itemCount,layoutManager)
    }

    private fun setSpacing(outRect: Rect, position: Int, itemCount: Int, layoutManager: RecyclerView.LayoutManager?) {

        when(mode){
             0 -> {
                outRect.left = spacing
                 outRect.right = if (position == itemCount - 1) spacing else 0
                 outRect.top = spacing
                 outRect.bottom = spacing

            }
             1 -> {

                 outRect.left = spacing
                 outRect.right = spacing
                 outRect.top = spacing
                 outRect.bottom = if (position == itemCount - 1) spacing else 0

            }
             2 -> {

                 if(layoutManager is GridLayoutManager){
                     val gridLayout = layoutManager as GridLayoutManager
                     val columns = gridLayout.spanCount
                     val rows = itemCount/columns


                     outRect.left = spacing
                     outRect.right = if(position % columns == columns - 1 ) spacing else 0
                     outRect.top = spacing
                     outRect.bottom = if(position % columns == columns - 1 ) spacing else 0


                 }

            }
        }

    }
}
