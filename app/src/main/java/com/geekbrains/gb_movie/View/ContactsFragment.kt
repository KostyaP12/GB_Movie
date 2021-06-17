package com.geekbrains.gb_movie.View

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.geekbrains.gb_movie.R
import com.geekbrains.gb_movie.databinding.ContactsFragmentBinding

class ContactsFragment : Fragment() {

    private lateinit var binding: ContactsFragmentBinding

    private val request = 1
    private val contacts = arrayListOf<String>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = ContactsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        checkPermissions()
    }

    private fun checkPermissions() {
        context?.let {
            if (ContextCompat.checkSelfPermission(
                            it,
                            Manifest.permission.READ_CONTACTS
                    ) == PackageManager.PERMISSION_GRANTED
            ) {
                getContacts()
            } else {
                requestPermission()
            }
        }
    }

    private fun requestPermission() {
        requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), request)
    }

    @SuppressLint("InflateParams")
    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        when (requestCode) {
            request -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContacts()
                } else {
                    context?.let {
                        Toast.makeText(context, R.string.contacts, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    private fun getContacts() {
        context?.let {
            val cursor = it.contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null
            )

            cursor?.let {
                while (cursor.moveToNext()) {
                    val number =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    val name =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    contacts.add("$name:\n$number")
                }
                cursor.close()
            }
        }
        setNumbers()
    }

    private fun setNumbers() {
        binding.contacts.adapter = ArrayAdapter(requireContext(), R.layout.simple_list, contacts)
    }
}