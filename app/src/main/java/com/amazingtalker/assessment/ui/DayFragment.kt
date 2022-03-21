package com.amazingtalker.assessment.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.amazingtalker.assessment.data.bean.Day
import com.amazingtalker.assessment.data.bean.Period
import com.amazingtalker.assessment.databinding.FragmentDayBinding
import com.amazingtalker.assessment.databinding.ViewPeriodBinding
import com.amazingtalker.assessment.domain.FormatDateUseCase
import com.amazingtalker.assessment.ui.base.BaseFragment
import com.hi.dhl.binding.viewbind
import org.koin.androidx.viewmodel.ext.android.viewModel

class DayFragment : BaseFragment<FragmentDayBinding, DayViewModel>() {

    override val mBinding: FragmentDayBinding by viewbind()
    override val mModel: DayViewModel by viewModel()

    companion object {
        private const val KEY_DATE = "key_date"
        fun newInstance(date: Day) =
            DayFragment().apply {
                arguments = bundleOf(KEY_DATE to FormatDateUseCase(date)())
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeStates()
        getData()
    }

    private fun initView() {
        with(mBinding) {
        }
    }

    private fun observeStates() {
        with(mModel) {
            periodListState.observe(viewLifecycleOwner, { periodListData ->
                for (period in periodListData) {
                    bindData(period)
                }
            })
        }
    }

    private fun bindData(period: Period) {
        mBinding.periodList.let { parent ->
            ViewPeriodBinding.inflate(layoutInflater, parent, false)
                .apply {
                    with(txtDate) {
                        isEnabled = period.isAvailable
                        text = formatDateForPeriod(period.startAt)
                    }
                    parent.addView(root)
                }
        }
    }

    private fun formatDateForPeriod(date: String): String {
        return FormatDateUseCase(date).toPeriod()
    }

    private fun getData() {
        requireArguments().getString(KEY_DATE)?.let {
            FormatDateUseCase(it)
        }?.let {
            mModel.getPeriodListData(it)
        }
    }
}