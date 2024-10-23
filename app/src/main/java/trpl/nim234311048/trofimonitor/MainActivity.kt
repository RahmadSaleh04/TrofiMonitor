package trpl.nim234311048.trofimonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val studentName = "Rahmad Riskiawan H. Saleh"
        val studentId = "234311048"

        val initialClubs = listOf(
            Club("Liverpool", 19, 8, 10, 6, 3),
            Club("Manchester United", 20, 12, 6, 3, 1),
            Club("Arsenal", 13, 14, 2, null, null),
            Club("Manchester City", 9, 7, 8, 1, null),
            Club("Chelsea", 6, 8, 5, 2, 2),
            Club("Tottenham Hotspur", 2, 8, 4, null, null),
        )

        setContent {
            var clubs by remember { mutableStateOf(initialClubs) }
            var showAddClubForm by remember { mutableStateOf(false) } // State untuk menampilkan form

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StudentInfoScreen(studentName, studentId)

                if (showAddClubForm) {
                    // Menampilkan form untuk menambah klub baru
                    AddClubScreen(onClubAdded = { newClub ->
                        clubs = clubs + newClub
                        showAddClubForm = false // Sembunyikan form setelah klub ditambahkan
                    })
                } else {
                    // Menampilkan daftar klub
                    ClubListScreen(clubs)

                    // Tombol untuk menambah klub baru
                    Button(
                        onClick = { showAddClubForm = true }, // Tampilkan form saat tombol di-klik
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.Start)
                    ) {
                        Text("Tambah Data Klub")
                    }

                    // Tampilkan klub dengan lebih dari 30 trofi
                    Text(
                        text = "Klub yang memiliki lebih dari 30 trofi:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    clubs.filter { it.totalTrophies > 30 }.forEach { club ->
                        Text(
                            text = "${club.name}: ${club.totalTrophies} trofi",
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}


data class Club(
    val name: String,
    val premierLeague: Int?,
    val faCup: Int?,
    val eflCup: Int?,
    val championsLeague: Int?,
    val europaLeague: Int?
) {
    val totalTrophies: Int
        get() = (premierLeague ?: 0) + (faCup ?: 0) + (eflCup ?: 0) + (championsLeague ?: 0) + (europaLeague ?: 0)
}

@Composable
fun StudentInfoScreen(studentName: String, studentId: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Nama: $studentName",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        Text(
            text = "NIM: $studentId",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun ClubListScreen(clubs: List<Club>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(clubs) { club ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tampilkan gambar logo klub
                ClubLogoImage(clubName = club.name)

                Spacer(modifier = Modifier.width(16.dp))

                // Tampilkan informasi klub
                Column {
                    Text(
                        text = club.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Total Trofi: ${club.totalTrophies}",
                        fontSize = 14.sp
                    )

                    if (club.championsLeague ?: 0 > 0) {
                        Text(
                            text = "Trofi internasional: ${club.championsLeague}",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    } else {
                        Text(
                            text = "${club.name} belum pernah memenangkan trofi internasional.",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ClubLogoImage(clubName: String) {
    val imageResource = when (clubName) {
        "Liverpool" -> R.drawable.liverpool
        "Manchester United" -> R.drawable.man_united
        "Arsenal" -> R.drawable.arsenal
        "Chelsea" -> R.drawable.chelsea
        "Manchester City" -> R.drawable.man_city
        "Tottenham Hotspur" -> R.drawable.tottenham
        else -> R.drawable.campuran // Fallback image
    }

    Image(
        painter = painterResource(id = imageResource),
        contentDescription = "$clubName Logo",
        modifier = Modifier.size(60.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewClubList() {
    val clubs = listOf(
        Club("Liverpool", 19, 8, 10, 6, 3),
        Club("Manchester United", 20, 12, 6, 3,1),
        Club("Arsenal", 13, 14, 2, null, null),
        Club("Chelsea", 6, 8, 5, 2, 2),
        Club("Manchester City", 9, 7, 8, 1, null),
        Club("Tottenham Hotspur", 2, 8, 4, null, null),
    )
    ClubListScreen(clubs)
}