package com.antgul.antgul_android.ui.community.post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.antgul.antgul_android.R
import com.antgul.antgul_android.base.ApplicationClass
import com.antgul.antgul_android.base.BaseFragment
import com.antgul.antgul_android.databinding.FragmentPostBinding
import com.antgul.antgul_android.model.Post
import com.antgul.antgul_android.model.PostCase
import com.antgul.antgul_android.ui.community.recyclerView.CommunityAdapter
import com.antgul.antgul_android.ui.start.login.LoginFragment
import com.antgul.antgul_android.util.RecyclerDecorationHeight
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import java.util.*

class PostFragment : BaseFragment<FragmentPostBinding?>() {
    private var mAdapter: CommunityAdapter? = null
    private var postList: ArrayList<Post>? = null
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPostBinding {
        return FragmentPostBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        postList = ArrayList()
        mAdapter = CommunityAdapter(postList, PostCase.STOCK_INFO)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(layoutInflater.context)
        binding!!.postRecycler.layoutManager = layoutManager
        binding!!.postRecycler.addItemDecoration(RecyclerDecorationHeight(3))
        binding!!.postRecycler.adapter = mAdapter
        posts
        mAdapter!!.notifyDataSetChanged()
        swipeRefresh()
    }

    override fun initClickListener() {
        // TODO 뒤로가기 했을 때 커뮤니티 프래그먼트가 제대로 안띄워짐
        mAdapter!!.setOnItemClickListener { v, pos ->
            binding!!.postWriteButton.visibility = View.GONE
            val bundle = Bundle()
            bundle.putString("docId", postList!![pos].documentId)
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val postDetailFragment = PostDetailFragment()
            transaction.setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.fade_out
            )
            transaction.add(R.id.activity_main_container, postDetailFragment)
            transaction.addToBackStack(null).commit()
            postDetailFragment.arguments = bundle
        }
        binding!!.postWriteButton.setOnClickListener {
            if (currentUser != null) {
                mainActivity.addFragment(R.id.activity_main_container, PostWriteFragment())
            } else {
                //TODO "로그인하시겠습니까?" 라고 출력하는 커스텀 다이얼로그 제작 필요
                showToast("로그인이 필요한 기능입니다.")
                mainActivity.addFragment(R.id.activity_main_container, LoginFragment())
            }
        }
    }

    //                .orderBy("createAt", Query.Direction.DESCENDING)
    private val posts: Unit
        private get() {
            Log.i(TAG, "getPost")
            progressDialog.showProgress()
            db.collection(ApplicationClass.POSTS_COLLECTION)
                .whereEqualTo(
                    "category",
                    1
                ) //                .orderBy("createAt", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener { task: Task<QuerySnapshot> ->
                    Log.i(TAG, "onComplete")
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            Log.d(TAG, document.id + " => " + document.data)
                            val post = document.toObject(Post::class.java)
                            post.documentId = document.id
                            postList!!.add(post)
                        }
                        mAdapter!!.notifyDataSetChanged()
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.exception)
                    }
                    progressDialog.hideProgress()
                }
        }

    private fun swipeRefresh() {
        binding!!.postRefreshLayout.setOnRefreshListener {
            postList!!.clear()
            posts
            showToast("새로고침 완료")
            binding!!.postRefreshLayout.isRefreshing = false
        }
    }
}