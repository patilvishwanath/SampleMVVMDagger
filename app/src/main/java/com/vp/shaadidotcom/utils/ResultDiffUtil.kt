package com.vp.shaadidotcom.utils

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.vp.shaadidotcom.data.local.entity.Results

/**
 * Created by Vishwanath Patil on 24/11/20.
 */
class ResultDiffUtil(private val oldList: List<Results>, private val newList : List<Results>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return true
    }


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        Log.v("Diff","$oldItemPosition ${oldList[oldItemPosition].status}====== ${newList[newItemPosition].status} $newItemPosition")
        return (oldList[oldItemPosition].status == newList[newItemPosition].status)
    }


    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldStatus = oldList[oldItemPosition].status
        val newStatus = newList[newItemPosition].status

        val diff = Bundle()
        if(newStatus != oldStatus) {
            Log.v("Diff", "not equal $newStatus")
            diff.putString("status", newStatus)
        }

        if(diff.size() == 0){
            Log.v("Diff", "diff.size() == 0")
            return null
        }

        return diff
    }

}