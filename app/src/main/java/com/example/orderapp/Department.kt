package com.example.orderapp

import com.google.firebase.firestore.DocumentId

data class Department(@DocumentId var documentId: String? = null, val name: String? = null) {
}