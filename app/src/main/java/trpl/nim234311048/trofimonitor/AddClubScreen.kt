package trpl.nim234311048.trofimonitor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddClubScreen(onClubAdded: (Club) -> Unit) {
    var clubName by remember { mutableStateOf(TextFieldValue("")) }
    var premierLeague by remember { mutableStateOf(TextFieldValue("")) }
    var faCup by remember { mutableStateOf(TextFieldValue("")) }
    var eflCup by remember { mutableStateOf(TextFieldValue("")) }
    var championsLeague by remember { mutableStateOf(TextFieldValue("")) }
    var europaLeague by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Tambahkan Klub Baru", fontSize = 20.sp)

        // Input untuk nama klub
        BasicTextField(
            value = clubName,
            onValueChange = { clubName = it },
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                if (clubName.text.isEmpty()) {
                    Text("Nama Klub")
                }
                innerTextField()
            }
        )

        // Input untuk Premier League trofi
        BasicTextField(
            value = premierLeague,
            onValueChange = { premierLeague = it },
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                if (premierLeague.text.isEmpty()) {
                    Text("Jumlah Premier League")
                }
                innerTextField()
            }
        )

        // Input untuk FA Cup trofi
        BasicTextField(
            value = faCup,
            onValueChange = { faCup = it },
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                if (faCup.text.isEmpty()) {
                    Text("Jumlah FA Cup")
                }
                innerTextField()
            }
        )

        // Input untuk EFL Cup trofi
        BasicTextField(
            value = eflCup,
            onValueChange = { eflCup = it },
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                if (eflCup.text.isEmpty()) {
                    Text("Jumlah EFL Cup")
                }
                innerTextField()
            }
        )

        // Input untuk Champions League trofi
        BasicTextField(
            value = championsLeague,
            onValueChange = { championsLeague = it },
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                if (championsLeague.text.isEmpty()) {
                    Text("Jumlah Champions League")
                }
                innerTextField()
            }
        )

        // Input untuk Europa League trofi
        BasicTextField(
            value = europaLeague,
            onValueChange = { europaLeague = it },
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                if (europaLeague.text.isEmpty()) {
                    Text("Jumlah Europa League")
                }
                innerTextField()
            }
        )

        // Tombol untuk menambahkan klub baru
        Button(
            onClick = {
                val club = Club(
                    name = clubName.text,
                    premierLeague = premierLeague.text.toIntOrNull(),
                    faCup = faCup.text.toIntOrNull(),
                    eflCup = eflCup.text.toIntOrNull(),
                    championsLeague = championsLeague.text.toIntOrNull(),
                    europaLeague = europaLeague.text.toIntOrNull()
                )
                onClubAdded(club)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Tambah Klub")
        }
    }
}