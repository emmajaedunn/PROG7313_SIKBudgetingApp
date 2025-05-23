package com.example.sikbudgetingapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sikbudget.data.entities.Budget
import com.example.sikbudget.repository.BudgetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BudgetViewModel(private val repository: BudgetRepository) : ViewModel() {

    val budgets: Flow<List<Budget>> = repository.getBudgets()

    fun addBudget(budget: Budget) {
        viewModelScope.launch {
            repository.addBudget(budget)
            val count = repository.getBudgets().collect { it.size }
            repository.updateAchievements(count)
        }
    }

    fun syncBudgets() {
        viewModelScope.launch {
            repository.syncBudgets()
        }
    }
}