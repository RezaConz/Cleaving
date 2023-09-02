package com.example.cleaving.Recycle

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.cleaving.LoginRegistrasi.Barang
import com.example.cleaving.MyAdapter
import com.example.cleaving.R
import com.example.cleaving.SuccessFragment
import com.example.cleaving.databinding.FragmentSummaryOrderBinding
import com.example.cleaving.databinding.LayoutSampahBinding

class SummaryOrderFragment : Fragment() {

    private lateinit var binding: FragmentSummaryOrderBinding
    private lateinit var binding2: LayoutSampahBinding

    private lateinit var adapter: MyAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Barang>

    lateinit var image:Array<Int>
    lateinit var name:Array<String>
    lateinit var poin:Array<String>
    lateinit var jumlah:Array<String>

    private fun dataInizialize(){
        newArrayList = arrayListOf<Barang>()
        val args = this.arguments
        image = arrayOf(
            R.drawable.kardus,
            R.drawable.botol_plastik,
            R.drawable.minyak_jelantah,
            R.drawable.kaleng
        )
        name = arrayOf(
            "Karton & Kertas",
            "Botol Plastik",
            "Minyak Jelantah",
            "Kaleng Minuman"
        )
        poin = arrayOf(
            "15 Point",
            "5 Points",
            "15 points",
            "3 Points"
        )
        jumlah = arrayOf(
            args?.get("kardusjumlah").toString()
        )

        for (i in image.indices){
            val Barang = Barang(image[i],name[i],poin[i],jumlah[0])
            newArrayList.add(Barang)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentSummaryOrderBinding.inflate(inflater,container,false)
        binding2 = LayoutSampahBinding.inflate(inflater,container,false)

//        dataInizialize()
//        val LayoutManager = LinearLayoutManager(context)
//        recyclerView = binding.recycleView
//        recyclerView.layoutManager = LayoutManager
//        recyclerView.setHasFixedSize(true)
//        adapter = MyAdapter(newArrayList)
//        recyclerView.adapter = adapter

        binding.buttonPickup.setOnClickListener {
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(
                    R.id.frame_layout,
                    SuccessFragment(),
                    SuccessFragment::class.java.simpleName
                )
                addToBackStack(null)
                commit()
            }
        }

//        val args = this.arguments
//        val test:String =  args?.get("kardusjumlah").toString()
//        if (test == null||test == "0"){
//        }else{
//            binding.nama1.text = args?.get("kardus").toString()
//            binding.poin1.text = args?.get("karduspoin").toString()
//            binding.jumlah1.text = test
//            binding.gambar1.setImageResource(R.drawable.kardus)
//        }

        return binding.root
    }
}