package com.quyunshuo.module.home.bean

data class HomePageBean(
    val tag_options: List<TagOptionsBean>,
) {
    /**
     * 标签栏数据
     *
     * @property id  Int id
     * @property title String 标签栏题目
     * 示例:
     * ```
     * {
     *     "id": 29,
     *     "title": "京东",
     * }
     */
    data class TagOptionsBean(
        val platformId: Int,
        val name: String
    )
}
