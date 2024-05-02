package com.example.searchimage

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.searchimage.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment(SearchFragment())
        initListener()

    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.layout_fragment, fragment)
            setReorderingAllowed(true)
            addToBackStack("")
        }
    }

    private fun initListener() {
        binding.layoutTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab?) {
                when (p0?.position) {
                    0 -> setFragment(SearchFragment())
                    1 -> setFragment(MyImageFragment())
                }
            }
            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }
        })
    }

}