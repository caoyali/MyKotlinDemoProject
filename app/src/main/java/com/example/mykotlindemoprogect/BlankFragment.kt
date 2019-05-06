package com.example.mykotlindemoprogect

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_blank.*

class BlankFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    private var mHandler : Handler = Handler{
        false
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var bundle : Bundle = Bundle();
        bundle.putInt(UserFragment.UID_KEY, UserFragment.LOAD_USER_ID)
        mHandler.postDelayed(Runnable { kotlin.run {
            this!!.activity?.let {
                Navigation.findNavController(mContentRoot).navigate(R.id.action_blankFragment_to_placeholder3, bundle)
            }
        } }, 5000)
    }
}
