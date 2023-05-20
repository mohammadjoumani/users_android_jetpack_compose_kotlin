package com.mmj.madarsofttask.presentation.resources.theme

import android.content.res.Configuration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object AppMargin {
    val dim2 = 2.dp
    val dim3 = 3.dp
    val dim4 = 4.dp
    val dim8 = 8.dp
    val dim6 = 6.dp
    val dim12 = 12.dp
    val dim14 = 14.dp
    val dim16 = 16.dp
    val dim18 = 18.dp
    val dim20 = 20.dp
    val dim24 = 24.dp
    val dim28 = 28.dp
    val dim50 = 50.dp
    val dim80 = 80.dp
}

object AppText {
    val dim4 = 4.sp
    val dim8 = 8.sp
    val dim12 = 12.sp
    val dim10 = 10.sp
    val dim11 = 11.sp
    val dim13 = 13.sp
    val dim14 = 14.sp
    val dim15 = 15.sp
    val dim16 = 16.sp
    val dim18 = 18.sp
    val dim20 = 20.sp
    val dim24 = 24.sp
    val dim28 = 28.sp
}

object AppCornerShape {
    val corner4 = 4.dp
    val corner8 = 8.dp
    val corner16 = 16.dp
    val corner25 = 25.dp
}

object AppSize {
    val size2 = 2.dp
    val size4 = 4.dp
    val size8 = 8.dp
    val size10 = 10.dp
    val size15 = 15.dp
    val size20 = 20.dp
    val size24 = 24.dp
    val size25 = 25.dp
    val size30 = 30.dp
    val size35 = 35.dp
    val size40 = 40.dp
    val size48 = 48.dp
    val size50 = 50.dp
    val size75 = 75.dp
    val size100 = 100.dp
    val size125 = 125.dp
    val size140 = 140.dp
    val size150 = 150.dp
    val size175 = 175.dp
    val size200 = 200.dp
    val size250 = 250.dp
    val size300 = 300.dp
}

object AppElevation {
    val elevation2 = 2.dp
    val elevation4 = 4.dp
    val elevation8 = 8.dp
}

fun getWidth(configuration: Configuration): Float {
    return configuration.screenWidthDp.toFloat()
}

fun getHeight(configuration: Configuration): Float {
    return configuration.screenHeightDp.toFloat()
}