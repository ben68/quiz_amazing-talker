package com.amazingtalker.assessment.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.core.animation.addListener
import androidx.viewpager2.widget.ViewPager2
import com.amazingtalker.assessment.R
import com.amazingtalker.assessment.data.bean.Day
import com.amazingtalker.assessment.databinding.ActivityCalendarBinding
import com.amazingtalker.assessment.ui.base.BaseActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.hi.dhl.binding.viewbind
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CalendarActivity : BaseActivity<ActivityCalendarBinding, CalendarViewModel>() {

    override val mBinding: ActivityCalendarBinding by viewbind()
    override val mModel: CalendarViewModel by viewModel()

    private lateinit var weekAdapter: WeekAdapter
    private lateinit var dayListAdapter: DayListAdapter
    private var dayName = listOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        observeStates()
        getData()
    }

    private fun initView() {
        weekAdapter = WeekAdapter(mModel)
        dayListAdapter = DayListAdapter(this)
        with(mBinding) {
            with(weekSelection) {
                adapter = weekAdapter
                registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        mModel.getDayListDataByWeek(position)
                    }
                })
            }

            with(periodSelection) {
                offscreenPageLimit = 4
                adapter = dayListAdapter
            }

            with(daySelection) {
                TabLayoutMediator(this, periodSelection) { tab, position ->
                    tab.text = dayName[position]
                }.attach()
            }

            with(scrollView) {
                watchScrolling()
            }
        }
    }

    private fun observeStates() {
        val lifecycleOwner = this
        with(mModel) {
            timeZoneState.observe(lifecycleOwner, {
                mBinding.txtTimeZone.text = getString(R.string.time_with_zone, it)
            })

            shouldShowPreviousWeek.observe(lifecycleOwner, { show ->
                if (show) {
                    mBinding.weekSelection.currentItem--
                    mModel.showPreviousWeekIsDone()
                }
            })

            shouldShowNextWeek.observe(lifecycleOwner, { show ->
                if (show) {
                    mBinding.weekSelection.currentItem++
                    mModel.showNextWeekIsDone()
                }
            })

            weekState.observe(lifecycleOwner, {
                if (it.size == 1) getDayListDataByWeek()
                mBinding.weekSelection.post {
                    weekAdapter.setData(it)
                }
            })

            dayListState.observe(lifecycleOwner, { it ->
                mutableListOf<String>().apply {
                    for (day in it) {
                        add(getTabNameByDay(day))
                    }
                }.toList().also { tabNames ->
                    dayName = tabNames
                }
                dayListAdapter.setData(it)
            })
        }
    }

    private fun getData() {
        with(mModel) {
            getWeekData()
            getTimeZone()
        }
    }

    private fun getTabNameByDay(state: Day) =
        "${getDayName(state.dayOfWeek)}, ${getMonthName(state.month)} ${state.date}"

    private fun getDayName(dayOfWeek: Int) =
        when(dayOfWeek) {
            Calendar.SUNDAY -> getString(R.string.sunday_ab)
            Calendar.MONDAY -> getString(R.string.monday_ab)
            Calendar.TUESDAY -> getString(R.string.tuesday_ab)
            Calendar.WEDNESDAY -> getString(R.string.wednesday_ab)
            Calendar.THURSDAY -> getString(R.string.thursday_ab)
            Calendar.FRIDAY -> getString(R.string.friday_ab)
            Calendar.SATURDAY -> getString(R.string.saturday_ab)
            else -> ""
        }

    private fun getMonthName(month: Int) =
        when(month) {
            Calendar.JANUARY -> getString(R.string.january_ab)
            Calendar.FEBRUARY -> getString(R.string.february_ab)
            Calendar.MARCH -> getString(R.string.march_ab)
            Calendar.APRIL -> getString(R.string.april_ab)
            Calendar.MAY -> getString(R.string.may_ab)
            Calendar.JUNE -> getString(R.string.june_ab)
            Calendar.JULY -> getString(R.string.july_ab)
            Calendar.AUGUST -> getString(R.string.august_ab)
            Calendar.SEPTEMBER -> getString(R.string.september_ab)
            Calendar.OCTOBER -> getString(R.string.october_ab)
            Calendar.NOVEMBER -> getString(R.string.november_ab)
            Calendar.DECEMBER -> getString(R.string.december_ab)
            else -> ""
        }

    private fun watchScrolling() {
        with(mBinding.scrollView) {
            setOnScrollChangeListener{ v, scrollX, scrollY, oldScrollX, oldScrollY ->
                val scrollSpeed = scrollY - oldScrollY
                if (scrollSpeed > 8) {
                    setOnScrollChangeListener(null)
                    hideHeader()
                }
                else if(scrollSpeed < -8) {
                    setOnScrollChangeListener(null)
                    showHeader()
                }
            }
        }
    }

    private fun showHeader() {
        with(mBinding) {
            ObjectAnimator.ofFloat(txtTimeZone, "alpha", 1F).apply {
                duration = 500
                start()
            }

            ObjectAnimator.ofFloat(daySelection, "translationY", 0F).apply {
                duration = 500
                start()
            }

            ObjectAnimator.ofFloat(scrollView, "translationY", 0F).apply {
                duration = 500
                start()
            }

            with(weekSelection) {
                ObjectAnimator.ofFloat(this, "scaleX", 1F).apply {
                    duration = 500
                    start()
                }

                ObjectAnimator.ofFloat(this, "scaleY", 1F).apply {
                    duration = 500
                    start()
                }

                ObjectAnimator.ofFloat(this, "alpha", 1F).apply {
                    duration = 500
                    addListener(onEnd = {
                        post { watchScrolling() }
                    })
                    start()
                }
            }
        }
    }

    private fun hideHeader() {
        with(mBinding) {
            val yPos = weekSelection.height.toFloat()

            ObjectAnimator.ofFloat(txtTimeZone, "alpha", 0F).apply {
                duration = 200
                start()
            }

            ObjectAnimator.ofFloat(daySelection, "translationY", -yPos).apply {
                duration = 500
                start()
            }

            ObjectAnimator.ofFloat(scrollView, "translationY", -yPos).apply {
                duration = 500
                start()
            }

            with(weekSelection) {
                ObjectAnimator.ofFloat(this, "scaleX", 0F).apply {
                    duration = 500
                    start()
                }

                ObjectAnimator.ofFloat(this, "scaleY", 0F).apply {
                    duration = 500
                    start()
                }

                ObjectAnimator.ofFloat(this, "alpha", 0F).apply {
                    duration = 500
                    addListener(onEnd = {
                        post { watchScrolling() }
                    })
                    start()
                }
            }
        }
    }
}