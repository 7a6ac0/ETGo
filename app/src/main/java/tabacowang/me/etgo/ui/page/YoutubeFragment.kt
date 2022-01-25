package tabacowang.me.etgo.ui.page

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import tabacowang.me.etgo.api.model.Items
import tabacowang.me.etgo.databinding.YoutubeFragmentBinding

class YoutubeFragment : Fragment(), YoutubeAdapter.YoutubeItemListener {

    private lateinit var binding: YoutubeFragmentBinding

    private lateinit var youtubeAdapter: YoutubeAdapter

    private lateinit var itemList: List<Items>

    companion object {
        private val ARGUMENT_ITEM_LIST_STRING = "ITEM_LIST_STRING"

        fun newInstance(itemList: List<Items>): YoutubeFragment {
            val dataString = Gson().toJson(itemList)
            return YoutubeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARGUMENT_ITEM_LIST_STRING, dataString)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataString = requireArguments().getString(ARGUMENT_ITEM_LIST_STRING)
        itemList = Gson().fromJson(dataString, object : TypeToken<List<Items>>() {}.type)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = YoutubeFragmentBinding.inflate(inflater, container, false)

        youtubeAdapter = YoutubeAdapter(this)

        binding.recycleView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = youtubeAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        youtubeAdapter.youtubeItems = itemList
    }

    override fun onItemClicked(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)

        val chooser = Intent.createChooser(intent, "選擇瀏覽器")
        startActivity(chooser)
    }
}