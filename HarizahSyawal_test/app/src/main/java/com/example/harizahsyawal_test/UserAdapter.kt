package com.example.harizahsyawal_test
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class UserAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var users = arrayListOf<User>()

    override fun getCount(): Int {
        return users.size
    }

    override fun getItem(p0: Int): Any {
        return users[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        var itemView = view
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false)
        }

        val viewHolder = ViewHolder(itemView as View)

        val user = getItem(position) as User
        viewHolder.bind(user)

        return itemView
    }

    private inner class ViewHolder(view: View?) {
        val firstName = view?.findViewById<TextView>(R.id.list_firstname)
        val lastName = view?.findViewById<TextView>(R.id.list_lastname)
        val email = view?.findViewById<TextView>(R.id.list_email)
        val dob = view?.findViewById<TextView>(R.id.list_dob)

        fun bind(user: User) {
            firstName?.text = user.firstName
            lastName?.text = user.lastName
            email?.text = user.email
            dob?.text = user.dob.toString()
        }
    }
}