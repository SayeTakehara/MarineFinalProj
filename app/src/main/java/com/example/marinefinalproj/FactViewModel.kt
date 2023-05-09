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
    private var _allPreviousFacts: MutableList<Fact> = mutableListOf()
    val allPreviousFacts: List<Fact>
        get() = _allPreviousFacts
    private var _lastThreeFacts: MutableList<Fact> = mutableListOf()
    val lastThreeFacts: List<Fact>
        get() = _lastThreeFacts

    fun assignLastThree(){
        _lastThreeFacts = mutableListOf()
        if(_allPreviousFacts.size >= 3){
            var length = _allPreviousFacts.size - 1
            while(_lastThreeFacts.size <= 3){
                _lastThreeFacts.add(_allPreviousFacts[length])
                length--
            }
        }
    }

    fun alertDialog(db: DatabaseReference): String{
        val randomInt = (Math.random() * 10).toInt()
        val factChosen = allFactsStrings[randomInt]
        db.child("Fact").push().setValue(Fact(factChosen, false))

        db.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allDBEntries = snapshot.children
                for (allFactEntries in allDBEntries) {
                    for (singleFactEntry in allFactEntries.children) {
                        val factID =
                            singleFactEntry.child("factText").getValue().toString()
                        val seenOrNot =
                            singleFactEntry.child("seenBefore").getValue().toString().toBoolean()
                        Log.i("MainActivity", "db worked")
                        _allPreviousFacts.add(Fact(factID, seenOrNot))
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        return factChosen
    }
}