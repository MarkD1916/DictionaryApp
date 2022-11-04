package com.example.dictionaryapp


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dictionaryapp.databinding.ActivityMainBinding
import com.example.dictionaryapp.presentation.search_word.WordSearchFragment


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val fragment = WordSearchFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container_main_activity, fragment)
            .commit()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

//    inner class ListenerWithScaleAnimation : View.OnClickListener {
//        override fun onClick(v: View) {
//            when (v.id) {
//                R.id.button -> {
//                    (v as Button).onClickWithScaleAnimate {
//                        Toast.makeText(this@MainActivity, "text", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
//    }
}

