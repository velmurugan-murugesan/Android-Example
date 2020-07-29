package com.example.myappauth

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_activity.*
import net.openid.appauth.*
import net.openid.appauth.AuthState.AuthStateAction
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private val RC_AUTH = 100

    private var mAuthService: AuthorizationService? = null
    private var mStateManager: AuthStateManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        mStateManager = AuthStateManager.getInstance(this)
        mAuthService = AuthorizationService(this)

        if (mStateManager?.current?.isAuthorized!!) {
            Log.d("Auth", "Done")
            button_login.setText("Logout")
            mStateManager?.current?.performActionWithFreshTokens(
                mAuthService!!
            ) { accessToken, idToken, exception ->
                ProfileTask().execute(accessToken)
            }

        }

        button_login.setOnClickListener {
            if (mStateManager?.current?.isAuthorized!!) {

            } else {
                val serviceConfig = AuthorizationServiceConfiguration(
                    Uri.parse("https://accounts.google.com/o/oauth2/v2/auth"), // authorization endpoint
                    Uri.parse("https://www.googleapis.com/oauth2/v4/token") // token endpoint
                )

                val clientId =
                    "511828570984-fuprh0cm7665emlne3rnf9pk34kkn86s.apps.googleusercontent.com"
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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_AUTH) {
            val resp = AuthorizationResponse.fromIntent(data!!)
            val ex = AuthorizationException.fromIntent(data)

            if (resp != null) {
                mAuthService = AuthorizationService(this)
                mStateManager?.updateAfterAuthorization(resp, ex)

                mAuthService?.performTokenRequest(
                    resp.createTokenExchangeRequest()
                ) { resp, ex ->
                    if (resp != null) {

                        mStateManager?.updateAfterTokenResponse(resp, ex)
                        button_login.setText("Logout")
                        Log.d("accessToken", resp.accessToken)
                        ProfileTask().execute(resp.accessToken)
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
        if (mStateManager?.current?.isAuthorized!!) {
            Log.d("Auth", "Done")
            button_login.text = "Logout"
            mStateManager?.current?.performActionWithFreshTokens(
                mAuthService!!
            ) { accessToken, idToken, exception ->
                ProfileTask().execute(accessToken)
            }

        }
    }

    inner class ProfileTask : AsyncTask<String?, Void, JSONObject>() {
        override fun doInBackground(vararg tokens: String?): JSONObject? {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://www.googleapis.com/oauth2/v3/userinfo")
                .addHeader("Authorization", String.format("Bearer %s", tokens[0]))
                .build()
            try {
                val response = client.newCall(request).execute()
                val jsonBody: String = response.body()!!.string()
                Log.i("LOG_TAG", String.format("User Info Response %s", jsonBody))
                return JSONObject(jsonBody)
            } catch (exception: Exception) {
                Log.w("LOG_TAG", exception)
            }
            return null
        }
        override fun onPostExecute(userInfo: JSONObject?) {
            if (userInfo != null) {
                val fullName = userInfo.optString("name", null)
                val imageUrl =
                    userInfo.optString("picture", null)
                if (!TextUtils.isEmpty(imageUrl)) {
                    Glide.with(this@LoginActivity).load(imageUrl).into(imgProfile);
                }
                if (!TextUtils.isEmpty(fullName)) {
                    textUsername.setText(fullName)
                }
                val message = if (userInfo.has("error")) { java.lang.String.format(
                        "%s [%s]", getString(R.string.request_failed), userInfo.optString("error_description", "No description"))
                } else {
                    getString(R.string.request_complete)
                }
                Snackbar.make(imgProfile, message, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

}
