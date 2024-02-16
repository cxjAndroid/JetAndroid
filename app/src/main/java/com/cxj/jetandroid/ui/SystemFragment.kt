/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cxj.jetandroid.R
import com.cxj.jetandroid.base.BaseComposeFragment
import com.cxj.jetandroid.model.SystemInfo
import com.cxj.jetandroid.model.SystemSecondList
import com.cxj.jetandroid.viewmodel.ComposeViewModel

class SystemFragment : BaseComposeFragment<ComposeViewModel>() {

    @Composable
    @Preview(showBackground = true)
    override fun SetComposeView() {
        ShowSystemPage(viewModel)
    }

    override fun initData() {
    }

    @Composable
    fun ShowSystemPage(viewModel: ComposeViewModel) {
        val systemData by viewModel.systemLiveData.observeAsState()
        LaunchedEffect(true) {
            viewModel.getSystemInfoList()
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F2F4)),
            contentPadding = PaddingValues(top = 15.dp, bottom = 15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            systemData?.let {
                item {
                    Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.Center) {
                        Text(
                            text = "This is a compose Page",
                            fontSize = 25.sp,
                            color = Color.DarkGray
                        )
                    }
                }
                items(it, key = { item -> item?.id ?: 0 }) { systemInfo ->
                    SystemItem(Modifier, systemInfo) {
                    }
                }
            }

        }


    }

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    fun SystemItem(
        modifier: Modifier = Modifier,
        data: SystemInfo?,
        itemClick: (SystemInfo?) -> Unit
    ) {
        Card(
            modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .clickable {
                    itemClick(data)
                },
            shape = RoundedCornerShape(6.dp)
        ) {
            Column(
                modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(top = 5.dp, bottom = 5.dp, start = 10.dp)

            ) {
                Text(
                    modifier = modifier.wrapContentSize(),
                    text = data?.name ?: "",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.sp_16).value.sp
                )
                FlowRow(modifier = Modifier.padding(top = 10.dp)) {
                    data?.children?.forEach {
                        Text(
                            text = it.name ?: "",
                            fontSize = 12.sp, color = Color.DarkGray,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun SystemItemPreview() {
        val systemParent =
            SystemInfo(
                1, 1, "Android开发", mutableListOf(
                    SystemSecondList(1, "Android Studio 相关", 1),
                    SystemSecondList(1, "Android Studio 相关", 1)
                ), 1
            )
        SystemItem(data = systemParent) {}
    }

}