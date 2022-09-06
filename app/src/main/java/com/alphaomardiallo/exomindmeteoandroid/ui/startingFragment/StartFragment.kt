package com.alphaomardiallo.exomindmeteoandroid.ui.startingFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.alphaomardiallo.exomindmeteoandroid.R


class StartFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                navController = findNavController()
                NavigateToResultFragment()
            }
        }
    }

    @Composable
    fun NavigateToResultFragment() {
        Surface {
            Box(contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextOverButton()
                    SimpleButton()
                }
            }
        }
    }

    @Composable
    fun TextOverButton() {
        Text(
            text = stringResource(id = R.string.text_over_main_button),
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }

    @Composable
    fun SimpleButton() {
        Button(onClick = {
            val action = StartFragmentDirections.actionStartFragmentToResultFragment()
            navController.navigate(action)
        }) {
            Text(text = stringResource(id = R.string.start_fragment_button_text))
        }
    }

}