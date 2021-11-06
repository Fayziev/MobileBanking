package uz.gita.mobilebanking.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.retrofit.response.CardData
import uz.gita.mobilebanking.databinding.ItemCardBinding
import uz.gita.mobilebanking.utils.scope

class CardAdapter : androidx.recyclerview.widget.ListAdapter<CardData, CardAdapter.CardViewHolder>(DiffItem) {

    private var listener: ((Int) -> Unit)? = null

    object DiffItem : DiffUtil.ItemCallback<CardData>() {
        override fun areItemsTheSame(oldItem: CardData, newItem: CardData): Boolean {
            return oldItem.pan == newItem.pan
        }

        override fun areContentsTheSame(oldItem: CardData, newItem: CardData): Boolean {
            return oldItem.balance == newItem.balance ||
                    oldItem.cardName == newItem.cardName ||
                    oldItem.exp == newItem.exp ||
                    oldItem.owner == newItem.owner ||
                    oldItem.status == newItem.status
        }
    }

    inner class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding by viewBinding(ItemCardBinding::bind)

        fun bind() = binding.scope {
            cardName.text = getItem(absoluteAdapterPosition).cardName
            exp.text = getItem(absoluteAdapterPosition).exp
            balance.text = getItem(absoluteAdapterPosition).balance.toString()
            pan.text = getItem(absoluteAdapterPosition).pan
            owner.text = getItem(absoluteAdapterPosition).owner
            status.text = getItem(absoluteAdapterPosition).status.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false))

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) = holder.bind()

    fun setListener(f: (Int) -> Unit) {
        listener = f
    }
}
