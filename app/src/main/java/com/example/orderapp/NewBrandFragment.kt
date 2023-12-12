package com.example.orderapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [NewBrandFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewBrandFragment : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var newBrandNameEditText: EditText
    lateinit var departmentId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            departmentId = it.getString(ARG_PARAM1).toString()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_brand, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newBrandNameEditText = view.findViewById(R.id.newBrandEditText)
        val saveButton = view.findViewById<Button>(R.id.saveBrandButton)
        saveButton.setOnClickListener{
            saveBrand()
        }



    }

    private fun saveBrand() {
        val db = Firebase.firestore
        val brandName = newBrandNameEditText.text.toString().lowercase()

        if (brandName.isNotEmpty()){
            val query = db.collection("departments").document(departmentId).collection("brands").whereEqualTo("name", brandName)
            query.get().addOnSuccessListener { snaphot ->
                if (snaphot.isEmpty){
                    db.collection("departments").document(departmentId).collection("brands").add(Brand(name = brandName))
                    val fragmentManager = requireActivity().supportFragmentManager
                    fragmentManager.popBackStack()
                }
                else{
                    Toast.makeText(requireContext(), "Name already exists", Toast.LENGTH_SHORT).show()
                }
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
         * @return A new instance of fragment NewBrandFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            NewBrandFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)

                }
            }
    }
}