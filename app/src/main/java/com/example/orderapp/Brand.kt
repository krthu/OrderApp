package com.example.orderapp

import com.google.firebase.firestore.DocumentId

data class Brand (@DocumentId var brandId: String? = null, var name: String? = null) {
}