package com.jorfald.todokristiania.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jorfald.todokristiania.R

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var todoRecyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton

    private lateinit var toDoAdapter: ToDoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        todoRecyclerView = view.findViewById(R.id.todo_recycler)
        fab = view.findViewById(R.id.todo_fab)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toDoAdapter = ToDoAdapter(
                { itemToDelete ->
                    //TODO: Delete item in database
                },
                { changedItem ->
                    //TODO: Change item in database
                }
        )
        todoRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = toDoAdapter
        }

        fab.setOnClickListener {
            //TODO: Add new item in database
        }


    }
}