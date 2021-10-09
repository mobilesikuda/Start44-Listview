package ru.sikuda.mobile.start44_listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter

import android.R.*
import android.view.View
import android.widget.ListView


class MainActivity : AppCompatActivity() {

    var names = arrayOf(
        "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
        "Костя", "Игорь", "Анна", "Денис", "Андрей"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lvMain: ListView = findViewById<View>(R.id.lvMain) as ListView

        // создаем адаптер

        // создаем адаптер
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            R.layout.simple_list_item_1, names
        )

        // присваиваем адаптер списку

        // присваиваем адаптер списку
        lvMain.setAdapter(adapter)
    }
}