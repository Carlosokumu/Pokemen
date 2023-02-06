package com.carlos.pokemen.utils

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.material.Card


fun LazyGridState.isScrolledToEnd() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1


