package com.mtech.travces.view.fragments.dashboard


import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mtech.travces.data.remote.travces.model.data.GetChildrenData
import com.mtech.travces.utils.SwipeToDeleteCallback
import com.mtech.travces.view.activities.GlobalNavigationActivity
import com.mtech.travces.view.activities.base.BaseActivity
import com.mtech.travces.view.adapters.ChildrenAdapter
import com.mtech.travces.view.fragments.base.BaseFragment
import com.mtech.travces.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_view_children.*


class ViewChildrenFragment : BaseFragment(), ChildrenAdapter.Callback {
    override fun getLayoutId(): Int = com.mtech.travces.R.layout.fragment_view_children
    var lastScrollPosition = -1
    lateinit var childrenAdapter: ChildrenAdapter
    var childrenList = ArrayList<GetChildrenData>()
    lateinit var userViewModel: UserViewModel
    // Initializing an empty ArrayList to be filled with animals

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachViewModel()
        userViewModel.getChildrenList(appPreferences.getUser().user.id.toString())
        initNotesAdapter()
    }


    private fun attachViewModel() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        with(userViewModel) {
            snackbarMessage.observe(viewLifecycleOwner, Observer {
                val msg = it?.getContentIfNotHandled()
                if (!msg.isNullOrEmpty()) showToast(msg)
            })
            progressBar.observe(viewLifecycleOwner, Observer {
                val show = it?.getContentIfNotHandled()
                if (show != null)
                    showProgressDialog(show)
            })
            getChildrenListResponse.observe(viewLifecycleOwner, Observer {

                val show = it?.getContentIfNotHandled()
                if (show?.Data != null) {
                    childrenList.clear()
                    for (temp in show.Data.iterator()) {
                        childrenList.add(temp)
                    }
                }
                childrenAdapter.notifyDataSetChanged()
            })
        }
    }

    private fun initNotesAdapter() {
        childrenAdapter = ChildrenAdapter(context as BaseActivity, childrenList, this)
        rvChild.layoutManager = LinearLayoutManager(context)
        rvChild.adapter = childrenAdapter
    }

    override fun onItemClicked(pos: Int) {
        val args = Bundle()
        args.putSerializable(AddChildFragment.Companion.KEY_CHILD, childrenList[pos])
        args.putBoolean("layout", true)
        (activity as GlobalNavigationActivity).navController.navigate(
            com.mtech.travces.R.id.action_viewChildrenFragment_to_addChildFragment,
            args
        )
    }

    override fun onDeleteClicked(pos: Int) {

    }

    override fun oncvItemClicked(pos: Int) {

    }

//    private fun enableSwipeToDeleteAndUndo() {
//        val swipeToDeleteCallback = object : SwipeToDeleteCallback(this) {
//            override fun onSwiped(@NonNull viewHolder: RecyclerView.ViewHolder, i: Int) {
//
//
//                val position = viewHolder.adapterPosition
////                val item = childrenAdapter.getData().get(position)
//
//                mAdapter.removeItem(position)
//
//
//                val snackbar = Snackbar
//                    .make(
//                        coordinatorLayout,
//                        "Item was removed from the list.",
//                        Snackbar.LENGTH_LONG
//                    )
//                snackbar.setAction("UNDO") {
//                    childrenAdapter.restoreItem(item, position)
//                    rvChild.scrollToPosition(position)
//                }
//
//                snackbar.setActionTextColor(Color.YELLOW)
//                snackbar.show()
//
//            }
//        }
//
//        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
//        itemTouchhelper.attachToRecyclerView(rvChild)
//    }
}
