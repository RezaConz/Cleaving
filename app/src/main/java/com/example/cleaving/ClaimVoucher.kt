package com.example.cleaving

import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.cleaving.Recycle.SummaryOrderFragment
import com.example.cleaving.databinding.FragmentClaimVoucherBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


class ClaimVoucher : Fragment(), View.OnClickListener{
    private lateinit var binding: FragmentClaimVoucherBinding
    private lateinit var dialog: BottomSheetDialog
    private lateinit var dialog2: BottomSheetDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClaimVoucherBinding.inflate(inflater, container, false)

        binding.UseVoucher.setOnClickListener {
            dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
            dialog.setContentView(R.layout.redeemed_points_activate)

            if (dialog.window != null){
                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            }

            dialog.show()
            dialog.findViewById<Button>(R.id.ButtonActivatePoints)?.setOnClickListener {
                dialog.dismiss()
                dialog2 = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
                dialog2.setContentView(R.layout.remaining_time_voucher)

                if (dialog2.window != null){
                    dialog2.window!!.setBackgroundDrawable(ColorDrawable(0))
                }

                dialog2.show()
                dialog2.findViewById<Button>(R.id.ButtonFinsihPoints)?.setOnClickListener {
                    val mFragmentManager = parentFragmentManager
                    mFragmentManager.beginTransaction().apply {
                        dialog2.dismiss()
                        replace(
                            R.id.frame_layout,
                            VoucherLastFragment(),
                            VoucherLastFragment::class.java.simpleName
                        )
                        addToBackStack(null)
                        commit()
                    }
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.BAckClaimVoucher.setOnClickListener(this)
//        binding.UseVoucher.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.BAckClaimVoucher) {
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(
                    R.id.frame_layout,
                    HomeFragment(),
                    HomeFragment::class.java.simpleName
                )
                addToBackStack(null)
                commit()
            }
        }
        if (v.id == R.id.UseVoucher) {
            val builder = AlertDialog.Builder(requireContext())
            val view = layoutInflater.inflate(R.layout.redeemed_points_activate, null)
            builder.setView(view)
            val dialog = builder.create()

            view.findViewById<Button>(R.id.ButtonNotNow).setOnClickListener {
                val mFragmentManager = parentFragmentManager
                mFragmentManager.beginTransaction().apply {
                    replace(
                        R.id.frame_layout,
                        ClaimVoucher(),
                        ClaimVoucher::class.java.simpleName
                    )
                    addToBackStack(null)
                    commit()
                }
                dialog.dismiss()
            }
            if (dialog.window != null) {
                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            }
            dialog.show()
        }
    }
}