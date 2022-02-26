package com.example.companymarket
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.TextView
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.companymarket.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val user = Firebase.auth.currentUser

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var uid = user?.uid
        var email = user?.email
        Log.d("userdata", uid.toString())// user uid가져오기


     binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        binding.appBarMain.fab.setOnClickListener { view ->
            var intent = Intent(this, NewProductActivity::class.java)
            startActivity(intent)

//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        var header =  navView.getHeaderView(0)
        var header_userUid : TextView = header.findViewById(R.id.header_userUid)
        var header_userEmail : TextView = header.findViewById(R.id.header_userEmail)
        header_userUid.text = uid.toString()
        header_userEmail.text = email


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_main,
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)



//        @Database(entities = [User::class], version = 1)
//        abstract class AppDatabase : RoomDatabase() {
//            abstract fun userDao(): UserDao
//        }
//
//        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
//
//        var user1 = User(0, "Hong", "Gildong")
//        var user2 = User(1, "Kim", "Gildong")
//
//        db.userDao().insertAll(user1);
//        db.userDao().insertAll(user2);
//
//        val userset = db.userDao().getAll()
//        for (u in userset) {
//            Log.d("user", u.toString())
//        }
//        db.userDao().getAll()

    }







    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}