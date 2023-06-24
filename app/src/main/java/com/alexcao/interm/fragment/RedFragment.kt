package com.alexcao.interm.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import kotlin.math.log

interface IMessage {
    fun onSend(message: String)
}

class RedFragment : Fragment() {
    private lateinit var iMessage: IMessage
    private lateinit var button: Button
    private lateinit var editText: EditText
    override fun onAttach(context: Context) {
        super.onAttach(context)
        iMessage = context as IMessage
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_red, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.btn)
        editText = view.findViewById(R.id.et)

        button.setOnClickListener() {
            iMessage.onSend(editText.text.toString())
        }
    }
}