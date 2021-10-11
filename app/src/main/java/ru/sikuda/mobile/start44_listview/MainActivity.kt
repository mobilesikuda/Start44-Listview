package ru.sikuda.mobile.start44_listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter

import android.R.layout
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val LOG_TAG = "myLogs"
    var lvMain: ListView? = null
    var names = arrayOf(
        "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
        "Костя", "Игорь", "Анна", "Денис", "Андрей"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      //First step
//        val lvMain: ListView = findViewById<View>(R.id.lvMain) as ListView
//
//        // создаем адаптер
//        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
//            this,
//            R.layout.simple_list_item_1, names
//        )
//
//        // присваиваем адаптер списку
//        lvMain.setAdapter(adapter)
        val adapter = ArrayAdapter.createFromResource(
            this, R.array.names,
            layout.simple_list_item_single_choice
        )
        lvMain = findViewById<View>(R.id.lvMain) as ListView?
        lvMain?.run {
            //choiceMode = ListView.CHOICE_MODE_SINGLE
            choiceMode = ListView.CHOICE_MODE_MULTIPLE
            setAdapter(adapter)
        }

        val btnChecked: Button = findViewById<View>(R.id.btnChecked) as Button
        btnChecked.setOnClickListener(this)

        names = resources.getStringArray(R.array.names)
    }

    override fun onClick(v: View?) {
        //Log.d(LOG_TAG, "checked: " + names[lvMain?.getCheckedItemPosition()!!]);
        //Log.d(LOG_TAG, "checked: " + lvMain?.getCheckedItemPosition())
        Log.d(LOG_TAG, "checked: ")
        val sbArray = lvMain!!.checkedItemPositions
        for (i in 0 until sbArray.size()) {
            val key = sbArray.keyAt(i)
            if (sbArray[key]) {
                Log.d(LOG_TAG, names[key])
            }
        }
    }
}


