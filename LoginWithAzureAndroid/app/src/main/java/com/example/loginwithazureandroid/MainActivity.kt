package com.example.loginwithazureandroid

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.microsoft.identity.client.*
import com.microsoft.identity.client.IPublicClientApplication.ISingleAccountApplicationCreatedListener
import com.microsoft.identity.client.ISingleAccountPublicClientApplication.CurrentAccountCallback
import com.microsoft.identity.client.exception.MsalClientException
import com.microsoft.identity.client.exception.MsalException
import com.microsoft.identity.client.exception.MsalServiceException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {

    /* Azure AD Variables */
    private var mSingleAccountApp: ISingleAccountPublicClientApplication? = null
    private var mAccount: IAccount? = null
    lateinit var button: Button
    private val SCOPES = arrayOf("user.read")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById<Button>(R.id.btnSignIn)
        /*try {
            val info = packageManager.getPackageInfo(
                "com.example.loginwithazureandroid",
                PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {

        } catch (e: NoSuchAlgorithmException) {

        }*/


        // Creates a PublicClientApplication object with res/raw/auth_config_single_account.json

        // Creates a PublicClientApplication object with res/raw/auth_config_single_account.json
        PublicClientApplication.createSingleAccountPublicClientApplication(this,
            R.raw.auth_config_single_account,
            object : ISingleAccountApplicationCreatedListener {
                override fun onCreated(application: ISingleAccountPublicClientApplication) {
                    /**
                     * This test app assumes that the app is only going to support one account.
                     * This requires "account_mode" : "SINGLE" in the config json file.
                     */
                    mSingleAccountApp = application
                    loadAccount()
                }

                override fun onError(exception: MsalException) {
                   // displayError(exception)
                    Log.d("Error", exception.message.toString())
                }
            })

        button.setOnClickListener {
            if (mSingleAccountApp == null) {

            } else {
                mSingleAccountApp!!.signIn(
                    this,
                    null,
                    SCOPES,
                    getAuthInteractiveCallback()
                )
            }

        }


    }

    private fun getAuthInteractiveCallback(): AuthenticationCallback
    {
        return object : AuthenticationCallback {
            override fun onSuccess(authenticationResult: IAuthenticationResult) {
                /* Successfully got a token, use it to call a protected resource - MSGraph */
                Log.d(
                    "TAG",
                    "Successfully authenticated"
                )


                /* Update account */mAccount = authenticationResult.account
                //updateUI()

            }

            override fun onError(exception: MsalException) {
                /* Failed to acquireToken */
                Log.d(
                    "TAG",
                    "Authentication failed: $exception"
                )
                //displayError(exception)
                if (exception is MsalClientException) {
                    /* Exception inside MSAL, more info inside MsalError.java */
                } else if (exception is MsalServiceException) {
                    /* Exception when communicating with the STS, likely config issue */
                }
            }

            override fun onCancel() {
                /* User canceled the authentication */
                Log.d(
                    "TAG",
                    "User cancelled login."
                )
            }
        }
    }

    private fun loadAccount() {
        if (mSingleAccountApp == null) {
            return
        }
        mSingleAccountApp!!.getCurrentAccountAsync(object : CurrentAccountCallback {
            override fun onAccountLoaded(activeAccount: IAccount?) {
                // You can use the account data to update your UI or your app database.
                mAccount = activeAccount
                //updateUI()
            }

            override fun onAccountChanged(priorAccount: IAccount?, currentAccount: IAccount?) {
                if (currentAccount == null) {
                    // Perform a cleanup task as the signed-in account changed.
                   // showToastOnSignOut()
                }
            }

            override fun onError(exception: MsalException) {
                //displayError(exception)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        /**
         * The account may have been removed from the device (if broker is in use).
         *
         * In shared device mode, the account might be signed in/out by other apps while this app is not in focus.
         * Therefore, we want to update the account state by invoking loadAccount() here.
         */
        loadAccount()
    }

}