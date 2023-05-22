package com.example.marinefinalproj

import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.snapshots

class FactViewModel: ViewModel() {
    private var allFactsStrings: List<String> = listOf(
        "a",
        "b",
        "c",
        "d",
        "e",
        "f",
        "g",
        "h",
        "i",
        "j"
    )
    private var _allPreviousFacts: MutableList<String> = mutableListOf()
    val allPreviousFacts: List<String>
        get() = _allPreviousFacts
    var shownFacts: MutableList<String> = mutableListOf()
    private var _lastThreeFacts: MutableList<String> = mutableListOf()
    val lastThreeFacts: List<String>
        get() = _lastThreeFacts
    var _playedOrNot: Boolean = false

    fun assignLastThree(){
        _lastThreeFacts = mutableListOf()
        val length = _allPreviousFacts.size.toInt() - 1
        for(i in length downTo 0){
            val newFact = _allPreviousFacts[i]
            if(!_lastThreeFacts.contains(newFact))
            _lastThreeFacts.add(newFact)
        }
    }

    fun addAndAssignFacts(db: DatabaseReference, ): String{
        val randomInt = (Math.random() * 10).toInt()
        val factChosen = allFactsStrings[randomInt]
        val newFact = Fact(factChosen)
        db.child("Fact").push().setValue(newFact)

        db.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allDBEntries = snapshot.children
                for (allFactEntries in allDBEntries) {
                    for (singleFactEntry in allFactEntries.children) {
                        val factID =
                            singleFactEntry.child("factText").getValue().toString()
                        Log.i("MainActivity", "db worked")
                        if(!_allPreviousFacts.contains(factID)) {
                            shownFacts.add(factID)
                        }
                        _allPreviousFacts.add(factID)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        _playedOrNot = true
        return factChosen
    }
}