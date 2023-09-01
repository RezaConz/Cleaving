package com.example.cleaving.Recycle

import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.cleaving.R
import com.example.cleaving.databinding.ActivityRegistrasiBinding
import com.example.cleaving.databinding.BottomSheetPickupBinding
import com.example.cleaving.databinding.FragmentRecycleBinding
import com.example.cleaving.databinding.LayoutSampahBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RecycleFragment : Fragment() {

    private lateinit var _binding: FragmentRecycleBinding
    private val binding get() = _binding!!
    private lateinit var binding2: LayoutSampahBinding
    private lateinit var binding3: ActivityRegistrasiBinding
    private lateinit var binding4:BottomSheetPickupBinding

    private lateinit var database: DatabaseReference

    private lateinit var dialog: BottomSheetDialog

    private var totalItems:Int = 0
    private var totalPoin:Int = 0
    private var poinKardus:Int = 0
    private var poinMinyak:Int = 0
    private var poinBotol:Int = 0
    private var poinKaleng:Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecycleBinding.inflate(inflater,container,false)
        binding2 = LayoutSampahBinding.inflate(inflater,container,false)
        binding3 = ActivityRegistrasiBinding.inflate(inflater,container,false)
        binding4 = BottomSheetPickupBinding.inflate(inflater,container,false)
        database = FirebaseDatabase.getInstance().getReference("Users")

        binding.tvTotalPoins.setText("0 Poin")

        setDataKardus()
        setDataBotol()
        setDataMinyak()
        setDataKaleng()

        binding.btnPickUp.setOnClickListener {
            showBottomSheet()
//            database.child(binding3.username.text.toString()).child("poin").setValue(totalPoin)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//            binding4.saveandcontinue.setOnClickListener { this }
    }

    private fun showBottomSheet(){
        dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        dialog.setContentView(R.layout.bottom_sheet_pickup)

        val builder = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.bottom_sheet_pickup,null)
        if (dialog.window != null){
            dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        }
        dialog.show()

        dialog.findViewById<Button>(R.id.saveandcontinue)?.setOnClickListener {
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                dialog.dismiss()
                replace(
                    R.id.frame_layout,
                    SummaryOrderFragment(),
                    SummaryOrderFragment::class.java.simpleName
                )
                addToBackStack(null)
                commit()
            }
        }
    }
    private fun setDataKardus(){
        var itemCount1: Int = 0

        binding.layoutsampah.imageAdd1.setOnClickListener{
            itemCount1 = itemCount1 + 1
            binding.layoutsampah.tvTotalKardus.setText(""+itemCount1)
            poinKardus = 15 * itemCount1
            setTotalPoin()
        }

        binding.layoutsampah.imageMinus1.setOnClickListener{
            if (itemCount1 > 0) {
                itemCount1 = itemCount1 - 1
                binding.layoutsampah.tvTotalKardus.setText(""+itemCount1)
            }
            poinKardus = 15 * itemCount1
            setTotalPoin()
        }
    }
    private fun setDataBotol(){
        var itemCount1: Int = 0

        binding.layoutsampah.imageAdd2.setOnClickListener{
            itemCount1 = itemCount1 + 1
            binding.layoutsampah.tvTotalBotol.setText(""+itemCount1)
            poinBotol = 5 * itemCount1
            setTotalPoin()
        }

        binding.layoutsampah.imageMinus2.setOnClickListener{
            if (itemCount1 > 0) {
                itemCount1 = itemCount1 - 1
                binding.layoutsampah.tvTotalBotol.setText(""+itemCount1)
            }
            poinBotol = 5 * itemCount1
            setTotalPoin()
        }
    }
    private fun setDataMinyak(){
        var itemCount1: Int = 0

        binding.layoutsampah.imageAdd3.setOnClickListener{
            itemCount1 = itemCount1 + 1
            binding.layoutsampah.tvTotalMinyak.setText(""+itemCount1)
            poinMinyak = 15 * itemCount1
            setTotalPoin()
        }

        binding.layoutsampah.imageMinus3.setOnClickListener{
            if (itemCount1 > 0) {
                itemCount1 = itemCount1 - 1
                binding.layoutsampah.tvTotalMinyak.setText(""+itemCount1)
            }
            poinMinyak= 15 * itemCount1
            setTotalPoin()
        }
    }
    private fun setDataKaleng(){
        var itemCount1: Int = 0

        binding.layoutsampah.imageAdd4.setOnClickListener{
            itemCount1 = itemCount1 + 1
            binding.layoutsampah.tvTotalKaleng.setText(""+itemCount1)
            poinKaleng = 3 * itemCount1
            setTotalPoin()
        }

        binding.layoutsampah.imageMinus4.setOnClickListener{
            if (itemCount1 > 0) {
                itemCount1 = itemCount1 - 1
                binding.layoutsampah.tvTotalKaleng.setText(""+itemCount1)
            }
            poinKaleng = 3 * itemCount1
            setTotalPoin()
        }
    }
    private fun setTotalPoin() {
        totalPoin = poinKardus + poinBotol + poinMinyak + poinKaleng
        binding.tvTotalPoins.setText(totalPoin.toString() + " Poins")

    }
}