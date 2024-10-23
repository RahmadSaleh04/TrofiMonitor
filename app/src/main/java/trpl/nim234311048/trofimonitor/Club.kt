data class Club(
    val name: String,
    val premierLeague: Int,
    val faCup: Int,
    val eflCup: Int,
    val championsLeague: Int,
    val europaLeague: Int
) {
    // Custom getter untuk menghitung total trofi
    val totalTrophies: Int
        get() = premierLeague + faCup + eflCup + championsLeague + europaLeague
}