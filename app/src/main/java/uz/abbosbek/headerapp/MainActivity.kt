package uz.abbosbek.headerapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.abbosbek.headerapp.adapters.RvAdapter
import uz.abbosbek.headerapp.databinding.ActivityMainBinding
import uz.abbosbek.headerapp.databinding.BottomShitDialogBinding
import uz.abbosbek.headerapp.models.Post
import uz.abbosbek.headerapp.repository.Repository

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val myAdapter by lazy { RvAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRecyclerView()
        btnClick(Post())

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        //todo: bu yerda userId ni qo'lda kiritasiz (hozirgi userId <2> ga teng)
        viewModel.getCustomPost(2, "id", "desc")
        viewModel.myCustomPosts.observe(this) {
            if (it.isSuccessful) {
                myAdapter.setData(it.body() as ArrayList<Post>)
            } else {
                Toast.makeText(this, it.code(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rv.adapter = myAdapter
        binding.rv.layoutManager = LinearLayoutManager(this)
    }

    private fun btnClick(post: Post) {
        binding.apply {
            btnAdd.setOnClickListener {
                val dialog = BottomSheetDialog(this@MainActivity)
                val bottomDialog = BottomShitDialogBinding.inflate(layoutInflater)
                dialog.setContentView(bottomDialog.root)

                bottomDialog.apply {
                }
                dialog.create()
                dialog.show()
            }
        }
    }
}