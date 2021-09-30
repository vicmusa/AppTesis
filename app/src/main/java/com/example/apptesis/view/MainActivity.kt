package com.example.apptesis.view

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.example.apptesis.core.Pref
import com.example.apptesis.databinding.ActivityMainBinding
import com.example.apptesis.databinding.ItemDialogBinding
import com.example.apptesis.model.PacienteModel
import com.example.apptesis.viewmodel.MainViewModel
import www.sanju.motiontoast.MotionToast


class MainActivity : AppCompatActivity() {
    private lateinit var txthr : TextView
    private val duration = MotionToast.LONG_DURATION
    private val gravity = MotionToast.GRAVITY_BOTTOM
    private var font = null
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
            toSaveList.add(it)
            pref.save(toSaveList)
            adapter.addItem(it)
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
               val deleteCI = toSaveList[viewHolder.absoluteAdapterPosition].ci
               toSaveList.removeAt(viewHolder.adapterPosition)
               pref.save(toSaveList)
               adapter.deleteItem(viewHolder.adapterPosition)
               showdialogAlta(deleteCI)
           }
       }
    val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvPacientes)
    }

    private fun showdialogAlta(item : String) {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea dar de alta al paciente?")
        builder.setMessage("El paciente se eliminó de la lista pero aún no se le ha dado de alta")
        builder.setPositiveButton("Si",DialogInterface.OnClickListener{dialog, id ->
            mainViewModel.alta(item)
            dialog.cancel()
        })
        builder.setNegativeButton("No",DialogInterface.OnClickListener{dialog, which ->
            dialog.cancel()
        })
        var alert = builder.create()
        alert.show()
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