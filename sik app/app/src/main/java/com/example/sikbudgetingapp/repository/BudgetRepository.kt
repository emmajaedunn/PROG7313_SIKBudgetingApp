package com.example.sikbudgetingapp.repository

import com.example.sikbudget.data.dao.BudgetDao
import com.example.sikbudget.data.entities.Budget
import com.example.sikbudget.data.firebase.FirebaseAuthService
import com.example.sikbudget.data.firebase.FirebaseFirestoreService
import kotlinx.coroutines.flow.Flow

class BudgetRepository(
    private val budgetDao: BudgetDao,
    private val authService: FirebaseAuthService,
    private val firestoreService: FirebaseFirestoreService
) {
    val currentUserId: String?
        get() = authService.currentUser?.uid

    suspend fun addBudget(budget: Budget) {
        budgetDao.insertBudget(budget)
        firestoreService.addBudget(budget)
    }

    fun getBudgets(): Flow<List<Budget>> {
        return budgetDao.getAllBudgets()
    }

    suspend fun syncBudgets() {
        val budgets = firestoreService.getBudgets()
        budgets.forEach { budgetDao.insertBudget(it) }
    }

    suspend fun getAchievements(): List<String> {
        return firestoreService.getAchievements(currentUserId ?: "")
    }

    suspend fun updateAchievements(budgetCount: Int) {
        firestoreService.updateAchievements(currentUserId ?: "", budgetCount)
    }
}