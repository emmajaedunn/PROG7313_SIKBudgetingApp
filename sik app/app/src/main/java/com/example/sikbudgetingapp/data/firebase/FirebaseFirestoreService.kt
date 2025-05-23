package com.example.sikbudgetingapp.data.firebase

import com.example.sikbudget.data.entities.Budget
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

object FirebaseFirestoreService {
    private val db = FirebaseFirestore.getInstance()

    fun saveBudget(budget: Budget) {
        db.collection("budgets").document(budget.id).set(budget)
    }

    fun getBudgets(userId: String, onResult: (List<Budget>) -> Unit) {
        db.collection("budgets").whereEqualTo("userId", userId).get()
            .addOnSuccessListener { snapshot ->
                val budgets = snapshot.documents.mapNotNull { it.toObject<Budget>() }
                onResult(budgets)
            }
    }

    fun saveAchievement(userId: String, badge: String) {
        db.collection("achievements").document(userId).update(badge, true)
    }

    fun getAchievements(userId: String, onResult: (Map<String, Boolean>) -> Unit) {
        db.collection("achievements").document(userId).get()
            .addOnSuccessListener { doc ->
                val data = doc.data ?: emptyMap()
                onResult(data.mapValues { it.value as Boolean })
            }
    }
}