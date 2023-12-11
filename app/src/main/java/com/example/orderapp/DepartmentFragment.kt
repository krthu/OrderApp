package com.example.orderapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DepartmentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DepartmentFragment() : Fragment(){
    val departments = mutableListOf<Department>()
    lateinit var recyclerView: RecyclerView
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deparment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.departmentRecycleView)

        val adapter = DepartmentAdapter(requireContext(), departments, this)
        recyclerView.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val newButton = view.findViewById<Button>(R.id.newButton)
        newButton.setOnClickListener{
            val newDepartmentFragment = NewDepartmentFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(R.id.fragmentContainer, newDepartmentFragment)
                ?.commit()

        }


        val docRef = db.collection("departments")
        docRef.addSnapshotListener{snapshot, e ->
            if (snapshot != null){
                departments.clear()
                for (document in snapshot.documents){
                    val department = document.toObject<Department>()
                    if (department != null){
                        departments.add(department)

                    }

                }
            }
            adapter.notifyDataSetChanged()
        }
    }

    fun onItemClick(documentId: String?) {
        val bundle = Bundle()
        bundle.putString("id", documentId)
        if (documentId != null) {
            val brandFragment = BrandFragment.newInstance(documentId)
            val supportFragmentManager = activity?.supportFragmentManager
            supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, brandFragment)
                ?.commit()

        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment deparmentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DepartmentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}