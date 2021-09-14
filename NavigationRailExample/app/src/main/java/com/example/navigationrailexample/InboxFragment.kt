package com.example.navigationrailexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.navigationrailexample.databinding.FragmentInboxBinding

class InboxFragment : Fragment() {

    lateinit var inboxBinding: FragmentInboxBinding
    lateinit var mailAdapter: MailAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inboxBinding = FragmentInboxBinding.inflate(layoutInflater, container, false)
        return inboxBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mailList = Mail().getInboxMailList()
        mailAdapter = MailAdapter()
        inboxBinding.recyclerviewInbox.adapter = mailAdapter
        mailAdapter.addMailList(mailList)

    }



}