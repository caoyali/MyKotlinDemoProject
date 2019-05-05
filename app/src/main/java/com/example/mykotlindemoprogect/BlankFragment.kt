package com.example.mykotlindemoprogect

import android.os.Bundle
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        this!!.activity?.let {
            Navigation.findNavController(mContentRoot).navigate(R.id.action_blankFragment_to_placeholder3)
        };
        super.onActivityCreated(savedInstanceState)
    }
}
