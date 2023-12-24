package com.example.test_suitmedia.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.test_suitmedia.data.response.DataItem
import com.example.test_suitmedia.data.retrofit.ApiService

class UserPagingSource (private  val apiService: ApiService) : PagingSource<Int, DataItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getUser(page, params.loadSize)

            LoadResult.Page(
                data = responseData.data,
                prevKey = if (page == INITIAL_PAGE_INDEX) null else page - 1,
                nextKey = if (responseData.data.isNullOrEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            Log.e(TAG, "Error paging: ${exception.localizedMessage}")
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        const val INITIAL_PAGE_INDEX = 1
        const val TAG = "UserPagingSource"
    }
}