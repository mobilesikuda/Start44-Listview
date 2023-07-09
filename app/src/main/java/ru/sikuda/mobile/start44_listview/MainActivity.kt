package ru.sikuda.mobile.start44_listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.R.layout
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemLongClickListener
import ru.sikuda.mobile.start44_listview.databinding.ActivityMainBinding
import android.widget.ArrayAdapter

class MainActivity : AppCompatActivity(){

    val LOG_TAG = "logListView"

    lateinit var binding: ActivityMainBinding
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
//      //direct create adapter
//        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
//            this,
//            layout.simple_list_item_1,
//            names
//        )

        //create adapter from resource
        val adapter = ArrayAdapter.createFromResource(this, R.array.names, layout.simple_list_item_1)

        //bind to spinner
        binding.spinner.adapter = adapter
        binding.spinner.setPrompt("Chooce item:")
        binding.spinner.setSelection(1)
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?,
                position: Int, id: Long
            ) {
                showToast("spinerSelect: position = " + position + ", id = " + id)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                showToast("spinerSelect: nothing")
            }
        }

        //bind to list
        binding.lvMain.choiceMode =  ListView.CHOICE_MODE_MULTIPLE //ListView.CHOICE_MODE_SINGLE
        binding.lvMain.adapter = adapter

        //OnItemSelectedListener is used for Spinners not for ListView
        binding.lvMain.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                showToast("Click: position = " + position + ", id = "+id)
            }

        binding.lvMain.onItemLongClickListener =
            OnItemLongClickListener {  parent, view, position, id ->
               showToast("LongClick: position = " + position + ", id = "+id)
                return@OnItemLongClickListener true
            }

        //lesson 44
        //цитата: Т.е. обработчик фиксирует какой пункт выделен. Честно говоря, я не очень понимаю как можно использовать такое выделение.
        //Но обработчик для него есть и я решил про него рассказать. Пусть будет.
//        binding.lvMain.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?, view: View?,
//                position: Int, id: Long
//            ) {
//                showToast("itemSelect: position = " + position + ", id = "+id)
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                showToast("itemSelect: nothing")
//            }
//        }

        binding.lvMain.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                showToast("scrollState = $scrollState")
            }


            override fun  onScroll(
                view: AbsListView?, firstVisibleItem: Int,
                visibleItemCount: Int, totalItemCount: Int
            ) {
                //to many events - only Log
                Log.d(LOG_TAG,"scroll: firstVisibleItem = " + firstVisibleItem
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

        binding.btnClearChecked.setOnClickListener{
            binding.lvMain.checkedItemPositions.clear()
        }
    }

    //write to log and show Toast
    fun showToast(str: String){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
        Log.d(LOG_TAG,str)
    }
}


