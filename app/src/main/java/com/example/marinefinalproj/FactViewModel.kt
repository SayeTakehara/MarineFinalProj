package com.example.marinefinalproj

import android.content.Context
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
    private var allFactsStrings: List<Int> = listOf(
        R.string.factOne,
        R.string.factTwo,
        R.string.factThree,
        R.string.factFour,
        R.string.factFive,
        R.string.factSix,
        R.string.factSeven,
        R.string.factEight,
        R.string.factNine,
        R.string.factTen,
    )
    private var _allPreviousFacts: MutableList<Fact> = mutableListOf()
    val allPreviousFacts: List<Fact>
        get() = _allPreviousFacts
    private var _lastThreeFacts: MutableList<Fact> = mutableListOf()
    val lastThreeFacts: List<Fact>
        get() = _lastThreeFacts

    fun addRandomFact(db: DatabaseReference): Int{
        val randomInt = (Math.random() * 10).toInt()
        val factChosen = allFactsStrings[randomInt]
        if (_allPreviousFacts.indexOf(Fact(factChosen, true)) > -1){
            db.child("Fact").push().setValue(Fact(factChosen, true))
        }
        return (factChosen)
    }
    fun assignFacts(db: DatabaseReference){
    }

    fun alertDialog(context: Context, db: DatabaseReference, fact: String): Boolean{
        var goBackorForward: Boolean = false
        MaterialAlertDialogBuilder(context)
            .setTitle(fact)
            .setMessage("play again?")
            .setPositiveButton("Yes") { dialog, which ->
                goBackorForward = true
            }
            .setNegativeButton("No"){ dialog, which ->
                goBackorForward = false
            }
            .show()
        db.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allDBEntries = snapshot.children
                Log.i("MainActivity", "db: ${allDBEntries}")
                for (allFactEntries in allDBEntries) {
                    for (singleFactEntry in allFactEntries.children) {
                        val factID =
                            singleFactEntry.child("factTextID").getValue().toString().toInt()
                        val seenOrNot =
                            singleFactEntry.child("seenBefore").getValue().toString()
                                .toBoolean()
                        Log.i("MainActivity", "db worked")
                        _allPreviousFacts.add(Fact(factID, seenOrNot))
                        if(_lastThreeFacts.size < 3){
                            _lastThreeFacts.add(Fact(factID, seenOrNot))
                        }
                    }
                    Log.i("MainActivity", "db cooked")
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        return goBackorForward
    }
}