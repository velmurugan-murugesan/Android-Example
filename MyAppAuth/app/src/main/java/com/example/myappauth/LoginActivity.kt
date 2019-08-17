package com.example.myappauth

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login_activity.*
import android.content.Intent
import androidx.browser.customtabs.CustomTabsIntent
import android.app.PendingIntent
import android.util.Log
import androidx.core.content.ContextCompat
import net.openid.appauth.*
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse


class LoginActivity : AppCompatActivity() {
    private val RC_AUTH = 100

    private var mAuthService: AuthorizationService? = null
    private var mStateManager: AuthStateManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        mStateManager = AuthStateManager.getInstance(this)

        if(mStateManager?.current?.isAuthorized!!) {
            Log.d("Auth","Done")
        }

        button_login.setOnClickListener {
            val serviceConfig = AuthorizationServiceConfiguration(
                Uri.parse("https://accounts.google.com/o/oauth2/v2/auth"), // authorization endpoint
                Uri.parse("https://www.googleapis.com/oauth2/v4/token") // token endpoint
            )

            mAuthService = AuthorizationService(this)
            val clientId = "511828570984-fuprh0cm7665emlne3rnf9pk34kkn86s.apps.googleusercontent.com"
            val redirectUri = Uri.parse("com.google.codelabs.appauth:/oauth2callback")
            val builder = AuthorizationRequest.Builder(
                serviceConfig,
                clientId,
                ResponseTypeValues.CODE,
                redirectUri
            )
            builder.setScopes("profile")

            val authRequest = builder.build()


          /*  val intentBuilder = mAuthService?.createCustomTabsIntentBuilder(authRequest.toUri())
            intentBuilder?.setToolbarColor(ContextCompat.getColor(this, R.color.colorAccent))
            customIntent = intentBuilder?.build()*/

            val authService = AuthorizationService(this)
            val authIntent = authService.getAuthorizationRequestIntent(authRequest)
            startActivityForResult(authIntent, RC_AUTH)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_AUTH) {
            val resp = AuthorizationResponse.fromIntent(data!!)
            val ex = AuthorizationException.fromIntent(data)

            if (resp != null) {
                mAuthService = AuthorizationService(this)
                mStateManager?.updateAfterAuthorization(resp,ex)

                mAuthService?.performTokenRequest(
                    resp.createTokenExchangeRequest()) { resp, ex ->
                    if (resp != null) {
                        mStateManager?.updateAfterTokenResponse(resp, ex)

                        Log.d("accessToken",resp.accessToken)

                        // exchange succeeded
                    } else {
                        // authorization failed, check ex for more details
                    }
                }

                //Log.d("res",resp.accessToken)
                // authorization completed
            } else {
                // authorization failed, check ex for more details
            }
            // ... process the response or exception ...
        } else {
            // ...
        }
    }
}
