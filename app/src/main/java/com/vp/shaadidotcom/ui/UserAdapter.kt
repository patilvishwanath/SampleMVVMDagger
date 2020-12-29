package com.vp.shaadidotcom.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vp.shaadidotcom.R
import com.vp.shaadidotcom.data.local.entity.Results
import com.vp.shaadidotcom.data.local.entity.User
import com.vp.shaadidotcom.utils.ResultDiffUtil
import kotlinx.android.synthetic.main.item_list_user.view.*

class UserAdapter(val resultList : MutableList<Results>, val mClickListener: IClickListener) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    var mContext: Context = mClickListener as Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false))
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = resultList[position]
        holder.setData(result)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            Log.v("Adapter", "payloads is empty")
            onBindViewHolder(holder, position)
        } else {
            val result = payloads[0] as Bundle

            Log.v("Adapter", "$position ${payloads.toString()} ${result.toString()}")
        }
    }


    fun notifyForDataChange(it: MutableList<User>?) {
//        if (it != null) {
//            userList.addAll(it)
//            notifyDataSetChanged()
//        }

    }

    fun notifyForResultsChange(it: MutableList<Results>?) {
        if (it != null) {
//            Log.v("Adapter", "oldList size ${resultList.size.toString()}")
//            Log.v("Adapter", "newList Size ${it.size.toString()}")
            val diffCallBack = ResultDiffUtil(resultList, it)
            val diffResult = DiffUtil.calculateDiff(diffCallBack,false)
            diffResult.dispatchUpdatesTo(this)

                resultList.clear()
                resultList.addAll(it)





         //   Log.v("Adapter", "updated oldList Size ${resultList.size.toString()}")

        }

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val context: Context? = itemView.context

        init {
            itemView.button.setOnClickListener(this)
            itemView.buttonEdit.setOnClickListener(this)

        }


        fun setData(user: Results) {
            itemView.textView.text = "${user.name.first} ${user.name.last}"
            itemView.tvEmail.text = user.email
            itemView.tvPhone.text = user.cell

            handleUserStatus(user)

        }

        private fun handleUserStatus(user: Results) {

            when (user.status) {
                "0" -> {
                    itemView.btnLayout.visibility = View.VISIBLE
                    itemView.tvMessage.visibility = View.GONE
                }
                "1" -> {
                    itemView.btnLayout.visibility = View.GONE
                    itemView.tvMessage.visibility = View.VISIBLE
                    itemView.tvMessage.text = mContext?.getString(R.string.request_accepted)
                }
                "2" -> {
                    itemView.btnLayout.visibility = View.GONE
                    itemView.tvMessage.visibility = View.VISIBLE
                    itemView.tvMessage.text = mContext?.getString(R.string.request_declined)

                }
            }

        }

        fun setDataUser(user: User) {
            itemView.textView.text = "${user.srId} ${user.name.first} ${user.name.last} "
            itemView.button.isEnabled = user.status == 1
        }

        override fun onClick(view: View?) {

            if (view?.id == R.id.button) {
                val result = resultList[adapterPosition].apply { this.status = "1" }

               Log.v("Adapter Single Result", result.toString())
                println()
                for(i in resultList){
                    Log.v("Adapter for Loop", i.toString())
                }
                view.let { mClickListener.passData(adapterPosition, result, null, view, "edit") }
            }

            if (view?.id == R.id.buttonEdit) {
                val result = resultList[adapterPosition].apply { this.status = "2" }
                view.let { mClickListener.passData(adapterPosition, result, null, view, "edit") }
            }
        }

    }

    interface IClickListener {
        fun passData(position: Int, result: Results?, user: User?, view: View, status: String)
    }


}
