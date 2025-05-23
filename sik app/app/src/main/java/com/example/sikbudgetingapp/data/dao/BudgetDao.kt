package com.example.sikbudgetingapp.data.dao
import androidx.room.*
import com.example.sikbudgetingapp.data.entities.Budget

@Dao
interface BudgetDao {
    @Query("SELECT * FROM budget")
    suspend fun getAllBudgets(): List<Budget>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBudget(budget: Budget)

    @Delete
    suspend fun deleteBudget(budget: Budget)
}