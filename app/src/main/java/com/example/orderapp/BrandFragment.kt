package com.example.orderapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.flowOf
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [BrandFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BrandFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var documentId: String
    private var brands = mutableListOf<Brand>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            documentId = it.getString(ARG_PARAM1).toString()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_brand, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.brandRecycleView)
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(divider)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = BrandAdapter(requireContext(), brands, this)
        recyclerView.adapter = adapter




        val db = Firebase.firestore
        if (documentId != null){

            val docRef = db.collection("departments").document(documentId).collection("brands")
            docRef.addSnapshotListener { snapshot, e ->

                if (snapshot != null) {
                    brands.clear()
                    for (document in snapshot.documents){

                        val brand = document.toObject<Brand>()

                        if (brand != null){
                            brands.add(brand)
                        }
                    }
                }
                adapter.notifyDataSetChanged()
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BrandFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            BrandFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}