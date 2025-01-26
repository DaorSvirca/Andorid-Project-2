package com.example.android_project_2

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.cacttus.navigationdrawergr_3.R
import com.cacttus.navigationdrawergr_3.databinding.ActivityMainBinding
import com.example.android_project_2.model.MusicResponse
import com.example.android_project_2.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_music_list,
                R.id.navigation_favorites,
                R.id.navigation_search,
                R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        fetchRecipes()
    }

    private fun fetchRecipes() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response: Response<MusicResponse> =
                    RetrofitInstance.api.getMusics(limit = 10).execute()

                if (response.isSuccessful) {
                    val musics = response.body()?.musics ?: emptyList()
                    // Call the updateRecipes method on the singleton
                    MusicRepository.getInstance().updateMusics(musics)
                    Log.d("API", "Fetched ${musics.size} musics")
                } else {
                    Log.e("API", "API error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Fetch failed: ${e.message}")
            }
        }
    }
}