package inc.fabudi.foodies.data

data class Tags(
    val tags: List<Tag>
)

data class Tag(
    val id: Int,
    val name: String
)