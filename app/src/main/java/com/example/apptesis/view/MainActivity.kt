package com.example.apptesis.view

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.apptesis.PacientesAdapter
import com.example.apptesis.R
import com.example.apptesis.core.FirebaseHelper
import com.example.apptesis.core.Pref
import com.example.apptesis.databinding.ActivityMainBinding
import com.example.apptesis.databinding.ItemDialgoAltaBinding
import com.example.apptesis.databinding.ItemDialogBinding
import com.example.apptesis.model.PacienteModel
import com.example.apptesis.viewmodel.MainViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal
import com.google.firebase.messaging.FirebaseMessaging
import www.sanju.motiontoast.MotionToast


class MainActivity : AppCompatActivity() {
    private lateinit var txthr : TextView
    private val duration = MotionToast.LONG_DURATION
    private val gravity = MotionToast.GRAVITY_BOTTOM
    private var font = null
    private var deletedPos = 0
    private lateinit var binding : ActivityMainBinding
    private var isClikeable = false
    private lateinit var bindingDialog : ItemDialogBinding
    private lateinit var alertDialog: AlertDialog
    private val mainViewModel : MainViewModel by viewModels()
    private  var list = mutableListOf<PacienteModel>()
    private var toSaveList = mutableListOf<PacienteModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Covid-Monitor"
        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingDialog = ItemDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pref = Pref(this)

        list = pref.getList()!!.toMutableList()
        toSaveList = list

        val adapter = PacientesAdapter(list,this)
        binding.rvPacientes.adapter = adapter
        createDialog()
        mainViewModel.dealta.observe(this,{
            MotionToast.createColorToast(this,getString(R.string.exito),"¡El paciente fue dado de alta satisfactoriamente!",MotionToast.TOAST_DELETE,gravity,duration,font)
        })
        mainViewModel.isLoading.observe(this, Observer {
            if(it)
            {
                binding.loading.visibility = View.VISIBLE
                binding.loading.playAnimation()
            }
            else
            {
                binding.loading.visibility = View.GONE
            }
        })
        mainViewModel.idNoExiste.observe(this, Observer {
            MotionToast.createColorToast(this,getString(R.string.error),"El ID del dispotivo no esta en la base de datos",MotionToast.TOAST_ERROR,gravity,duration,font)
            openDialog()
        })
        mainViewModel.paciente.observe(this, Observer {
            if(toSaveList.contains(it))
            {
                MotionToast.createColorToast(this,getString(R.string.advertencia),"El paciente ya está en la lista",MotionToast.TOAST_ERROR,gravity,duration,font)
            }
            else {
                list.add(it)
                toSaveList = list
                pref.save(toSaveList)
                Log.e("ERROR", list.toString())
                adapter.notifyItemInserted(list.size-1)
            }
             })
        mainViewModel.asignado.observe(this, Observer {
            MotionToast.createColorToast(this,getString(R.string.advertencia),"EL ID ya esta asignado al Paciente de CI ${it.ci}",MotionToast.TOAST_INFO,gravity,duration, font)
            openDialog()
        })
        mainViewModel.noExiste.observe(this, Observer {
            openDialog()
            MotionToast.createColorToast(this,getString(R.string.error),"La cedula/id no registrada",MotionToast.TOAST_ERROR,gravity,duration, font)

        })
        binding.fabmenu.setOnClickListener{
            if(!isClikeable)
            {
                binding.fabadd.visibility = View.VISIBLE
                binding.fabadd2.visibility = View.VISIBLE
                binding.fabinfo.visibility = View.VISIBLE
                binding.fabtimeline.visibility = View.VISIBLE

            }
            else{

                binding.fabadd.visibility = View.GONE
                binding.fabadd2.visibility = View.GONE
                binding.fabinfo.visibility = View.GONE
                binding.fabtimeline.visibility = View.GONE


            }
            binding.fabadd.isClickable = !isClikeable
            binding.fabadd2.isClickable = !isClikeable
            binding.fabtimeline.isClickable = !isClikeable
            binding.fabinfo.isClickable = !isClikeable
            isClikeable = !isClikeable
        }
        binding.fabtimeline.setOnClickListener {
            goto(2)
            goto(2)
        }
        binding.fabadd2.setOnClickListener {
            goto(1)
        }
        binding.fabadd.setOnClickListener {
            openDialog()
        }
        binding.fabinfo.setOnClickListener {
            goto(3)
        }
        bindingDialog.dialogAdd.setOnClickListener {
            val ci=bindingDialog.dialogCI.text.toString()
            val id = bindingDialog.dialogID.text.toString().toUpperCase()
            mainViewModel.addData(ci,id)
            alertDialog.dismiss()}
        bindingDialog.dialogCancel.setOnClickListener{
            alertDialog.cancel() }

       val itemTouchHelperCallback = object : ItemTouchHelper.Callback(){
           override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
               return makeMovementFlags(ItemTouchHelper.UP ,ItemTouchHelper.RIGHT)
           }

           override fun onMove(
               recyclerView: RecyclerView,
               viewHolder: RecyclerView.ViewHolder,
               target: RecyclerView.ViewHolder
           ): Boolean {
               TODO("Not yet implemented")
           }

           override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               deletedPos = viewHolder.absoluteAdapterPosition
               FirebaseHelper.UnsuscribeTopic(list[deletedPos].id)
               val deleteCI = toSaveList[deletedPos].ci
               val deletedItem = toSaveList[deletedPos]
               list.removeAt(deletedPos)
               toSaveList = list
               pref.save(toSaveList)
               adapter.notifyItemRemoved(deletedPos)
               adapter.notifyDataSetChanged()
               showdialogAlta(deleteCI,deletedItem,adapter,pref)
           }
       }
    val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvPacientes)
    }


    private fun showdialogAlta(item : String,deletedItem : PacienteModel,adapter: PacientesAdapter,pref: Pref) {

        val b = ItemDialgoAltaBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this)
        builder.setView(b.root)
        builder.setCancelable(false)
        val dialog = builder.create()
        b.yesAlta.setOnClickListener {
            mainViewModel.alta(item)
            dialog.dismiss()
        }
        b.noAlta.setOnClickListener{
            dialog.dismiss()
        }
        b.cancelAlta.setOnClickListener {
            list.add(deletedPos,deletedItem)
            adapter.notifyItemInserted(deletedPos)
            toSaveList=list
            pref.save(toSaveList)
            dialog.dismiss()
        }

        dialog.show()

    }

    private fun createDialog() {
       val  dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setView(bindingDialog.root)
        dialogBuilder.setCancelable(false)
        alertDialog = dialogBuilder.create()
    }

    private fun openDialog(){
        alertDialog.show()
    }

    private fun goto(identify : Int){

        when(identify)
        {
            1 -> startActivity(Intent(this, AddPacientActivity::class.java))
            2 -> startActivity(Intent(this, TimeLineActivity::class.java))
            3 -> startActivity(Intent(this, InfoActivity::class.java))
        }

    }



}