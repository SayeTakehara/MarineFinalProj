package com.example.marinefinalproj

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class FactViewModel: ViewModel() {
    private var allFacts: List<Fact> = listOf(
        Fact(R.string.factOne, false),
        Fact(R.string.factTwo, false),
        Fact(R.string.factThree, false),
        Fact(R.string.factFour, false),
        Fact(R.string.factFive, false),
        Fact(R.string.factSix, false),
        Fact(R.string.factSeven, false),
        Fact(R.string.factEight, false),
        Fact(R.string.factNine, false),
        Fact(R.string.factTen, false)
    )
    private var _lastThreeFacts: MutableList<Fact> = mutableListOf()
    val lastThreeFacts: List<Fact>
    get() = _lastThreeFacts

    fun addRandomFact(db: DatabaseReference){
        val randomInt = (Math.random() * 11).toInt()
        val factChosen = allFacts[randomInt]
        if(!factChosen.seenBefore) {
            db.child("Facts").push().setValue(factChosen)
        }
    }
    fun assignLastThree(db: DatabaseReference){
        db.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allDBEntries = snapshot.children

                for (allFactEntries in allDBEntries) {
                    if (_lastThreeFacts.size <= 3) {
                        for (singleFactEntry in allFactEntries.children) {
                            val factID =
                                singleFactEntry.child("factTextID").getValue().toString().toInt()
                            val seenOrNot =
                                singleFactEntry.child("seenBefore").getValue().toString()
                                    .toBoolean()
                            _lastThreeFacts.add(Fact(factID, seenOrNot))
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}