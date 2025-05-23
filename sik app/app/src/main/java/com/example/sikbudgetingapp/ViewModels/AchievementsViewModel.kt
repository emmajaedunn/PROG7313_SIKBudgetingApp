package com.example.sikbudgetingapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sikbudget.repository.BudgetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AchievementsViewModel(private val repository: BudgetRepository) : ViewModel() {

    private val _achievements = MutableStateFlow<List<String>>(emptyList())
    val achievements: StateFlow<List<String>> = _achievements

    fun fetchAchievements() {
        viewModelScope.launch {
            val result = repository.getAchievements()
            _achievements.value = result
        }
    }
}