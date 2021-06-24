package com.geekbrains.gb_movie

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(stringResource: Int, length: Int) =
        Snackbar.make(this, resources.getString(stringResource), length)