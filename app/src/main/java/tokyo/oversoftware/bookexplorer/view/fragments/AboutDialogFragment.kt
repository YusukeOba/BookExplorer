package tokyo.oversoftware.bookexplorer.view.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import tokyo.oversoftware.bookexplorer.R

/**
 * アプリの説明ダイアログ
 */
class AboutDialogFragment : DialogFragment() {

    interface Callbacks {
        /**
         * ダイアログを閉じた
         */
        fun onDismissAboutDialog()
    }

    companion object {
        const val TAG = "AboutDialogFragment"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.dialog_title)
            .setMessage(R.string.dialog_message)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                (activity as Callbacks).onDismissAboutDialog()
            }
            .create()
    }
}