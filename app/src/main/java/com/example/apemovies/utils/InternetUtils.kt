package com.example.apemovies.utils

import android.os.AsyncTask
import com.example.apemovies.interfaces.AsyncTaskDelegate
import java.lang.Exception
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress



 class InternetUtils() : AsyncTask<Void, Void, Boolean>() {

    override fun doInBackground(vararg params: Void?): Boolean {
                try {
                    val timeout = 1500;
                    val socket = Socket();
                    val socketAddr: SocketAddress = InetSocketAddress("8.8.8.8", 53);
                    socket.connect(socketAddr, timeout);
                    socket.close();
                    return true;
                } catch (e: Exception) {
                    return false;
                }
    }

     override fun onPostExecute(result: Boolean) {
         super.onPostExecute(result)
         taskDelegate!!.onTaskEnd(result);
     }

     companion object {
         var taskDelegate: AsyncTaskDelegate? = null;
     }

}