package ru.sikuda.mobile.start44_listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.R.layout
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import ru.sikuda.mobile.start44_listview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){
    val LOG_TAG = "myLogs"

    lateinit var binding: ActivityMainBinding
    //lateinit var lvMain: ListView
//    var names = arrayOf(
//        "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
//        "Костя", "Игорь", "Анна", "Денис", "Андрей"
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //lesson 42 - new binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val viewAll = binding.root
        setContentView(viewAll)

        //lesson 43
        val adapter = ArrayAdapter.createFromResource(this, R.array.names, layout.simple_list_item_1)
        binding.lvMain.choiceMode = ListView.CHOICE_MODE_SINGLE //ListView.CHOICE_MODE_MULTIPLE
        binding.lvMain.adapter = adapter

        //OnItemSelectedListener is used for Spinners not for ListView
        binding.lvMain.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                showToast("itemClick: position = " + position + ", id = "+id)
            }

        //lesson 44
        binding.lvMain.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?,
                position: Int, id: Long
            ) {
                showToast("itemSelect: position = " + position + ", id = "+id)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                showToast("itemSelect: nothing")
                //Log.d(LOG_TAG, "itemSelect: nothing")
            }

        }

        binding.lvMain.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                Log.d(LOG_TAG, "scrollState = $scrollState")
            }

            override fun  onScroll(
                view: AbsListView?, firstVisibleItem: Int,
                visibleItemCount: Int, totalItemCount: Int
            ) {
                Log.d(
                    LOG_TAG, "scroll: firstVisibleItem = " + firstVisibleItem
                            + ", visibleItemCount" + visibleItemCount
                            + ", totalItemCount" + totalItemCount
                )
            }
        })

        binding.btnChecked.setOnClickListener{
            val sbArray = binding.lvMain.checkedItemPositions
            var str = ""
            for (i in 0 until sbArray.size()) {
                val key = sbArray.keyAt(i)
                if (sbArray[key]) {
                    str += key.toString()+","
                    Log.d(LOG_TAG, key.toString())
                }
             }
            showToast("Selected:"+str)
        }
    }

    fun showToast(str: String){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}


