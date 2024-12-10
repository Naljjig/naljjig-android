package com.naljjig.presentation.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.naljjig.R
import com.naljjig.core.designsystem.NaljjigTheme

@Composable
fun WelcomeScreen(
    navigateToLogin: () -> Unit,
    navigateToSignUp: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            text = AnnotatedString(
                text = stringResource(R.string.welcome_text),
                spanStyle = SpanStyle(
                    color = NaljjigTheme.colors.titleText,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                paragraphStyle = ParagraphStyle(
                    textAlign = TextAlign.Center,
                    lineHeight = 4.em,
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Top,
                        trim = LineHeightStyle.Trim.Both
                    )
                )
            )
        )

        Spacer(modifier = Modifier.height(90.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = NaljjigTheme.colors.titleText,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable { navigateToLogin() }
                    .padding(vertical = 15.dp)
            ){
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Login",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NaljjigTheme.colors.secondaryButton
                )
            }

            Spacer(modifier = Modifier.width(30.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = NaljjigTheme.colors.secondaryButton,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable { navigateToSignUp() }
                    .padding(vertical = 15.dp)
            ){
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "SignUp",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NaljjigTheme.colors.titleText
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview(){
    NaljjigTheme {
        WelcomeScreen(
            {},
            {}
        )
    }
}