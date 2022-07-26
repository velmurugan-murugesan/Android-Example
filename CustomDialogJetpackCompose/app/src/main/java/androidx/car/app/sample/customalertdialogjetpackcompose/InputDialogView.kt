package androidx.car.app.sample.customalertdialogjetpackcompose

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun InputDialogView(onDismiss:() -> Unit) {
    val context = LocalContext.current
    var searchedFood by remember {
        mutableStateOf("")
    }

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(10.dp),
            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier.padding(8.dp),
            elevation = 8.dp
        ) {
            Column(
                Modifier
                    .background(Color.White)
            ) {

                Text(
                    text = "Search your favorite food",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 20.sp
                )

                OutlinedTextField(
                    value = searchedFood,
                    onValueChange = { searchedFood = it }, modifier = Modifier.padding(8.dp),
                    label = { Text("Favorite Food") }
                )

                Row {
                    OutlinedButton(
                        onClick = { onDismiss() },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(text = "Cancel")
                    }


                    Button(
                        onClick = {
                            Toast.makeText(context, searchedFood, Toast.LENGTH_SHORT).show()
                            onDismiss() },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(text = "Search")
                    }
                }


            }
        }
    }
}

@Preview
@Composable
fun InputDialogViewPreview() {
    InputDialogView(){}
}