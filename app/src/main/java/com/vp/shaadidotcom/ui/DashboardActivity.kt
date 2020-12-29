package com.vp.shaadidotcom.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.vp.shaadidotcom.R
import com.vp.shaadidotcom.UserApp
import com.vp.shaadidotcom.data.local.entity.Results
import com.vp.shaadidotcom.data.local.entity.User
import com.vp.shaadidotcom.di.component.DaggerActivityComponent
import com.vp.shaadidotcom.di.module.ActivityModule
import com.vp.shaadidotcom.utils.EqualSpacingItemDecoration
import com.vp.shaadidotcom.utils.ResultDiffUtil
import com.vp.shaadidotcom.utils.Utils
import kotlinx.android.synthetic.main.activity_dashboard.*

import javax.inject.Inject


/*1. Handle Error Cases
2. Add DiffUtil
3. Add Paging
4. Constraint Layout*/

/**
 * Created by Vishwanath Patil on 08/10/20.
 */
class DashboardActivity : AppCompatActivity(), UserAdapter.IClickListener {

    @Inject
    lateinit var viewModel: DashboardViewModel

    lateinit var adapter: UserAdapter

     val list: MutableList<User>  = mutableListOf()

     val result: MutableList<Results>  = mutableListOf()

    companion object {
        const val TAG = "DashboardActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        setUpDependency()
        setUpListeners()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)





        rvUserList.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(result,this)//UserAdapter(this@DashboardActivity, list, this)//UserAdapter(list,this,false)//
        rvUserList.adapter = adapter
        rvUserList.addItemDecoration(EqualSpacingItemDecoration(Utils.convertPixelsToDp(46f,this).toInt(),EqualSpacingItemDecoration.VERTICAL))



    }

    private fun setUpListeners() {
        viewModel.listData.observe(this, Observer {
            adapter.notifyForDataChange(it.toMutableList())
        })

        viewModel.resultData.observe(this, Observer {
            Log.v("$TAG size",it.size.toString())
            adapter.notifyForResultsChange(it)

        })

        viewModel.addressData.observe(this, Observer {
            for(i in 1..it.size-1){
                Log.v(TAG,"${it[i].city} ${it[i].pincode}  ${it[i].userId} " )
            }
        })

        viewModel.progressData.observe(this, object : Observer<Boolean>{
            override fun onChanged(t: Boolean) {
                if(t){
                    progressBar.visibility = View.VISIBLE
                }else{
                    progressBar.visibility = View.GONE
                }
            }

        })


    }

    private fun setUpDependency() {
        DaggerActivityComponent
                .builder()
                .applicationComponent((application as UserApp).applicationComponent)
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
    }

    override fun onStart() {
        super.onStart()
       // viewModel.getUsers()
       // viewModel.getAddress()
    }

    override fun passData(position: Int, result: Results?,user:User?, view: View, status: String) {
      //  Log.v(TAG , position.toString())

        result?.let { viewModel.updateResult(it) }

//        if (status == "delete") {
//            user?.let { viewModel.deleteData(it) }
//        } else if (status == "edit") {
//            user?.let { viewModel.updateUser(it) }
//        }

    }


}