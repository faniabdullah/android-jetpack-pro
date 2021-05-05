package com.bangkit.faniabdullah_jetpack.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CatalogResponse<T>(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<T>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)

