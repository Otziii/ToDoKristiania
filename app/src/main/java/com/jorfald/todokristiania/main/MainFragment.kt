package com.jorfald.todokristiania.main

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
import com.jorfald.todokristiania.database.AppDatabase
import com.jorfald.todokristiania.database.dao.ToDoDAO
import com.jorfald.todokristiania.managers.DialogManager

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var todoRecyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var filterFab: FloatingActionButton

    private lateinit var toDoAdapter: ToDoAdapter

    private lateinit var toDoDAO: ToDoDAO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val view = inflater.inflate(R.layout.main_fragment, container, false)

        todoRecyclerView = view.findViewById(R.id.todo_recycler)
        fab = view.findViewById(R.id.todo_fab)
        filterFab = view.findViewById(R.id.filter_fab)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindObservers()
        bindButtons()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toDoDAO = AppDatabase.getDatabase(requireContext()).toDoDAO()

        updateList()
    }

    private fun bindObservers() {
        viewModel.allToDoItems.observe(viewLifecycleOwner, { newList ->
            activity?.runOnUiThread {
                toDoAdapter.setData(newList)
            }
        })
    }

    private fun bindButtons() {
        toDoAdapter = ToDoAdapter(
            { itemToDelete ->
                viewModel.deleteItem(toDoDAO, itemToDelete)
            },
            { changedItem ->
                viewModel.changeItem(toDoDAO, changedItem)
            }
        )
        todoRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = toDoAdapter
        }

        fab.setOnClickListener {
            DialogManager.showInputDialog(
                requireContext(),
                "Hva skal du gjøre?"
            ) { toDoText ->
                viewModel.saveToDoItem(toDoDAO, toDoText)
            }
        }

        filterFab.setOnClickListener {
            DialogManager.showInputDialog(
                requireContext(),
                "Skriv inn filtrering"
            ) { toDoText ->
                if (toDoText.isNotEmpty()) {
                    viewModel.getListWithStartingChar(toDoDAO, toDoText)
                } else {
                    updateList()
                }
            }
        }
    }

    private fun updateList() {
        viewModel.fetchList(toDoDAO)
    }
}