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
        "Napoleon fish use their foreheads like a rhino horn", "Mahi Mahi means \"strong strong\"", "pufferfish make little underwater crop circles",
        "Vampire squids, despite their name, are filter feeders", "Whale sharks are filter feeders", "squids have a small bone in their head",
        "Coelacanth are \"living fossils\"", "cepholopods include squids, octopi, and cuttlefish", "siphonophores are colonies made of zooids",
        "octopus ink pasta technically tastes better than squid ink", "stepping on a stone fish can poison you (ouch)", "The mimic octopus can copy corals and even rayfish",
        "some fish can change their sexual orientation at will", "suckerfish can stick onto boats", "There was an experiment giving dolphins LSD",
        "The Dorado is named after its golden color", "moray eels have 2 sets of jaws", "sunfish are easily pass out, even in aquariums",
        "hammerheads travel in groups", "humbolt squids hunt in packs, earning the name \"red devil\"", "male angler fish are smaller than their female counterparts",
        "the moon jelly's sting is very weak", "sea angels open their heads to eat (scary!)", "The phatom jelly has only been spotted only <196 times",
        "Some Sturgeons live up to 100 years", "A Greenland shark live more than 500 years", "Thresher sharks use their tails like whips",
        "Not a fact, but Abzû is a great game about the ocean", "Salmon change their color in the ocean", "Brine pools are very salty pools inside the ocean",
        "A Mantis Shrimp's arms work opposite to an actual mantis's", "lobsters can change color based on diet", "Pistol shrimps can make loud and strong snaps",
        "Clownfish can stay with anemones without stinging", "Some jellyfish use light to communicate", "Deep sea creatures tend to be bigger",
        "Remora fish clean sharks' mouths", "Stingrays are eaten by sharks, despite their evolutionary relations", "the spotted jelly always has 8 legs",
        "blue ringed octopus venom can kill in minutes", "immortal jellyfish can revert to previous lifecyle phases"
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