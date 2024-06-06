package com.example.markzapptask.aj.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.markzapptask.R
import com.example.markzapptask.databinding.FragmentBlankBinding
import com.example.markzapptask.aj.room.AppDatabase
import com.example.markzapptask.aj.room.MainViewModel
import com.example.markzapptask.aj.room.MainViewModelFactory
import com.example.markzapptask.aj.adapters.UserAdapter
import com.example.markzapptask.aj.room.ProductRepository
import com.example.markzapptask.aj.adapters.ImagePagerAdapter
import com.example.markzapptask.aj.adapters.StatusAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private val images = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4
    )
    private var _binding: FragmentBlankBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var adapter2: StatusAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userDao = AppDatabase.getDatabase(requireContext()).productDao()
        val repository = ProductRepository(userDao)
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        binding.marqueeTextView.isSelected = true

        adapter = UserAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerView.adapter = adapter
        viewModel.products.observe(viewLifecycleOwner) { users ->
            users?.let { adapter.setProducts(it) }
        }


        adapter2 = StatusAdapter()
        binding.rvStatus.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.rvStatus.adapter = adapter2
        viewModel.products.observe(viewLifecycleOwner) { users ->
            users?.let { adapter2.setUsers(it) }
        }

        viewModel.refreshProducts()


        binding.viewPager.adapter = ImagePagerAdapter(images)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Tab configuration if needed
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}