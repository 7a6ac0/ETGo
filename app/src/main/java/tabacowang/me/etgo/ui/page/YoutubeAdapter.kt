package tabacowang.me.etgo.ui.page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tabacowang.me.etgo.R
import tabacowang.me.etgo.api.model.Items
import tabacowang.me.etgo.databinding.ItemTextBinding
import tabacowang.me.etgo.databinding.ItemYoutubeBinding

class YoutubeAdapter(
    private val listener: YoutubeItemListener
) : RecyclerView.Adapter<YoutubeAdapter.YoutubeViewHolder>() {

    private val list = AsyncListDiffer(this, YoutubeDiffCallback)
    var youtubeItems: List<Items> = emptyList()
        set(value) {
            field = value
            list.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
        return YoutubeViewHolder(
            ItemYoutubeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: YoutubeViewHolder, position: Int) {
        val itemData = list.currentList[position]
        val binding = holder.binding

        Glide
            .with(binding.image.context)
            .load(itemData?.pagemap?.cse_image?.get(0)?.src)
            .centerCrop()
            .into(binding.image)
        binding.title.text = itemData?.pagemap?.metatags?.get(0)?.title ?: ""

        if (itemData.isClicked) {
            binding.layout.setBackgroundResource(R.color.clicked)
        } else {
            binding.layout.setBackgroundResource(R.color.white)
        }

        holder.itemView.setOnClickListener {
            itemData.isClicked = true
            binding.layout.setBackgroundResource(R.color.clicked)
            listener.onItemClicked(itemData?.link ?: "")
        }
    }

    override fun getItemCount(): Int = youtubeItems.size

    inner class YoutubeViewHolder(val binding: ItemYoutubeBinding): RecyclerView.ViewHolder(binding.root)

    interface YoutubeItemListener {
        fun onItemClicked(url: String)
    }
}

object YoutubeDiffCallback : DiffUtil.ItemCallback<Items>() {

    override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
        return oldItem.cacheId == newItem.cacheId
    }

    override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
        return oldItem == newItem
    }
}