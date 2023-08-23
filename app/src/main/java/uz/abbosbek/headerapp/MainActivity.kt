package uz.abbosbek.headerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import uz.abbosbek.headerapp.databinding.ActivityMainBinding
import uz.abbosbek.headerapp.repository.Repository

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
//        viewModel.getPost()

        binding.button.setOnClickListener {
            val myNumber = binding.numberEditText.text.toString()
            viewModel.getCustomPost(Integer.parseInt(myNumber), "id", "desc")
            viewModel.myCustomPosts.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    binding.textView.text = response.body().toString()
                    response.body()?.forEach {
                        Log.d("Response", it.userId.toString())
                        Log.d("Response", it.id.toString())
                        Log.d("Response", it.title)
                        Log.d("Response", it.body)
                        Log.d("Response", "----____-----_____-----")
                    }
                } else {
                    binding.textView.text = response.code().toString()
                }
            })
        }
    }
}