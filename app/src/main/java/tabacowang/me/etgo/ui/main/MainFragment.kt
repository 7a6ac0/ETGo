package tabacowang.me.etgo.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import tabacowang.me.etgo.databinding.MainFragmentBinding
import tabacowang.me.etgo.ui.page.TextFragment
import tabacowang.me.etgo.ui.page.YoutubeFragment
import tabacowang.me.etgo.util.Resource
import tabacowang.me.etgo.util.showSnackBar
import java.util.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding

    private lateinit var pagerAdapter: PagerAdapter

    private val viewModel: MainViewModel by viewModel()

    private var searchTimer: Timer? = null
    private val searchWacher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            searchTimer?.cancel()
        }

        override fun afterTextChanged(s: Editable?) {
            searchTimer = Timer()
            searchTimer?.schedule(object : TimerTask() {
                override fun run() {
                    viewModel.getData(binding.search.text.toString())
                }
            }, 600)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)

        pagerAdapter = PagerAdapter(this)
        pagerAdapter.fragments = listOf(TextFragment.newInstance(emptyList()), YoutubeFragment.newInstance(emptyList()))
        binding.viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "文字"
                else -> tab.text = "Youtube"
            }
        }.attach()

        binding.search.addTextChangedListener(searchWacher)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    val data = it.data.items ?: emptyList()
                    pagerAdapter.fragments = listOf(TextFragment.newInstance(data), YoutubeFragment.newInstance(data))
                    binding.viewPager.adapter = pagerAdapter
                    binding.progressBar.visibility = View.INVISIBLE
                    view.showSnackBar("讀取成功", Snackbar.LENGTH_LONG)
                }
                is Resource.Error -> {
                    pagerAdapter.fragments = listOf(TextFragment.newInstance(emptyList()), YoutubeFragment.newInstance(emptyList()))
                    binding.viewPager.adapter = pagerAdapter
                    binding.progressBar.visibility = View.INVISIBLE
                    view.showSnackBar("讀取失敗", Snackbar.LENGTH_LONG)
                }
            }
        }
    }
}