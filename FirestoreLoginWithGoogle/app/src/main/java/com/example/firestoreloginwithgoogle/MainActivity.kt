package com.example.firestoreloginwithgoogle

import android.content.Intent
import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var oneTapClient: SignInClient
    lateinit var signInRequest: BeginSignInRequest
    private val REQ_ONE_TAP = 2  // Can be any integer unique to the Activity
    lateinit var btnLoginWithGoogle: MaterialButton
    lateinit var btnSignOut: MaterialButton
    lateinit var textUsername: MaterialTextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLoginWithGoogle = findViewById(R.id.btnLoginWithGoogle)
        textUsername = findViewById(R.id.textUsername)
        btnSignOut = findViewById(R.id.btnSignout)

        oneTapClient = Identity.getSignInClient(this)
        signInRequest = BeginSignInRequest.builder()
            .setPasswordRequestOptions(
                BeginSignInRequest.PasswordRequestOptions.builder()
                    .setSupported(true)
                    .build()
            )
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(getString(R.string.default_web_client_id))
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build()
            )
            // Automatically sign in when exactly one credential is retrieved.
            .setAutoSelectEnabled(true)
            .build()

        btnLoginWithGoogle.setOnClickListener {
            oneTapClient.beginSignIn(signInRequest).addOnSuccessListener { result ->
                try {

                    startIntentSenderForResult(
                        result.pendingIntent.intentSender, REQ_ONE_TAP,
                        null, 0, 0, 0, null
                    )
                } catch (e: IntentSender.SendIntentException) {
                    Log.e("Error", "Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }.addOnFailureListener {
                Log.d("Error", it.localizedMessage)
            }
        }

        btnSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            textUsername.text = ""
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_ONE_TAP -> {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
                    val idToken = credential.googleIdToken
                    val username = credential.id
                    val password = credential.password
                    when {
                        idToken != null -> {
                            // Got an ID token from Google. Use it to authenticate
                            // with your backend.
                            Log.d("Debug", "Got ID token.")
                            textUsername.text = username
                        }
                        password != null -> {
                            // Got a saved username and password. Use them to authenticate
                            // with your backend.
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
                                .addOnSuccessListener {
                                    textUsername.text = it.user?.displayName
                                    Toast.makeText(this, it.user?.email, Toast.LENGTH_SHORT)
                                        .show()
                                }.addOnFailureListener {
                                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT)
                                        .show()

                                }
                            Log.d("Debug", "Got password.")

                        }
                        else -> {
                            // Shouldn't happen.
                            Log.d("Error", "No ID token or password!")
                        }
                    }
                } catch (e: ApiException) {
                    // ...
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        currentUser?.let {
            textUsername.text = it.displayName
        }
    }
}