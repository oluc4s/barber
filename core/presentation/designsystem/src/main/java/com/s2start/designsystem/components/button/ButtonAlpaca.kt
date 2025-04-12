package com.s2start.designsystem.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.s2start.core.presentation.designsystem.R
import com.s2start.designsystem.AlpacaTheme
import com.s2start.designsystem.Blue
import com.s2start.designsystem.BlueContent
import com.s2start.designsystem.yellow
import com.s2start.designsystem.yellowContent


@Composable
fun ButtonAlpaca(
    modifier: Modifier = Modifier,
    enabled:Boolean = true,
    text:String,
    isLoading:Boolean = false,
    onClick:() -> Unit
){
    val animationLottie by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(resId = R.raw.button_loading)
    )

    val animationAction by animateLottieCompositionAsState(
        composition = animationLottie,
        iterations = LottieConstants.IterateForever
    )

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.padding(top = 12.dp).fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = yellow,
            disabledContainerColor = yellowContent,
        ),
        enabled = enabled && !isLoading
    ) {
        if(isLoading){
            LottieAnimation(
                composition = animationLottie,
                progress = { animationAction },
                modifier = Modifier.size(40.dp),
            )
        }else{
            Row (verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = text,
                    fontWeight = FontWeight.SemiBold,
                    color = if(enabled){ Color.White } else { yellow },
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
    }
}



@Composable
fun ButtonOutLineAlpaca(
    modifier: Modifier = Modifier,
    enabled:Boolean = true,
    text:String,
    isLoading:Boolean = false,
    onClick:() -> Unit
){
    val animationLottie by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(resId = R.raw.button_loading)
    )

    val animationAction by animateLottieCompositionAsState(
        composition = animationLottie,
        iterations = LottieConstants.IterateForever
    )

    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, yellow),
        modifier = modifier.padding(top = 12.dp).fillMaxWidth(),
        enabled = enabled && !isLoading
    ) {
        if(isLoading){
            LottieAnimation(
                composition = animationLottie,
                progress = { animationAction },
                modifier = Modifier.size(40.dp),
            )
        }else{
            Row (verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = text,
                    fontWeight = FontWeight.SemiBold,
                    color = yellow,
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
    }
}
@Preview
@Composable
private fun ButtonPreview(){
    AlpacaTheme {
        Column {
            ButtonAlpaca(text = "Entrar", onClick = {})
            ButtonAlpaca(text = "Entrar", onClick = {}, enabled = false)
            ButtonAlpaca(text = "Entrar", onClick = {}, enabled = false, isLoading = true)
            ButtonOutLineAlpaca(text = "Entrar", onClick = {})
        }
    }
}