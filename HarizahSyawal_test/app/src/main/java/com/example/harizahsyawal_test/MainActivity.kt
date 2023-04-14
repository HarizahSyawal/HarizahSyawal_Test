package com.example.harizahsyawal_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import org.json.JSONObject
import java.io.InputStream

class MainActivity : AppCompatActivity() {

        private lateinit var myAppbar: Toolbar
        private lateinit var listView: ListView
        private lateinit var adapter: UserAdapter
        private lateinit var users: ArrayList<User>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.lv_user)
        adapter = UserAdapter(this)
        users = parseJSON()

        listView.adapter = adapter
        adapter.users = users

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
            val toast = Toast.makeText(this, users[i].firstName, Toast.LENGTH_SHORT)
            toast.show()
        }

        myAppbar = findViewById(R.id.app_bar)
        myAppbar.title = "Contact"
        setSupportActionBar(myAppbar)
    }

    private fun parseJSON(): ArrayList<User> {
        val users = arrayListOf<User>()
        var json: String? = null

        try {
            val input: InputStream = assets.open("data.json")
            json = input.bufferedReader().use { it.readText() }

            val jsonObject = JSONObject(json)

            val jsonArray = jsonObject.getJSONArray("users")

            for (i in 0 until jsonArray.length()) {
                val jsonObj = jsonArray.getJSONObject(i)

                val firstname: String = jsonObj.getString("firstName")
                val lastname: String = jsonObj.getString("lastName")
                val email: String = jsonObj.getString("email")
                val dob: Int = jsonObj.getInt("dob")

                val user = User(firstname, lastname, email, dob)

                users.add(user)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return users
    }


}