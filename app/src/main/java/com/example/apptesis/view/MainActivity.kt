package com.example.apptesis.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.apptesis.InfoActivity
import com.example.apptesis.PacientesAdapter
import com.example.apptesis.core.Pref
import com.example.apptesis.databinding.ActivityMainBinding
import com.example.apptesis.databinding.ItemDialogBinding
import com.example.apptesis.model.PacienteModel
import com.example.apptesis.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var txthr : TextView
    private lateinit var txtspo2 : TextView
    private lateinit var txttemp : TextView
    private lateinit var binding : ActivityMainBinding
    private var isClikeable = false
    private lateinit var bindingDialog : ItemDialogBinding
    private lateinit var alertDialog: AlertDialog
    private val mainViewModel : MainViewModel by viewModels()
    private  var list = mutableListOf<PacienteModel>()
    private var toSaveList = mutableListOf<PacienteModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingDialog = ItemDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pref = Pref(this)
        list = pref.getList()!!.toMutableList()
        toSaveList = list

        val adapter = PacientesAdapter(list)
        binding.rvPacientes.adapter = adapter
        createDialog()

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
            openDialog()
            Toast.makeText(this,"El ID no esta en la base de datos",Toast.LENGTH_LONG).show()
        })
        mainViewModel.paciente.observe(this, Observer {
            toSaveList.add(it)
            pref.save(toSaveList)
            adapter.addItem(it)
             })
        mainViewModel.asignado.observe(this, Observer {
            openDialog()
            Toast.makeText(this, "EL ID Selecionacionado ya esta asignado al Paciente de CI ${it.ci}",Toast.LENGTH_LONG).show()
        })
        mainViewModel.noExiste.observe(this, Observer {
            openDialog()
            Toast.makeText(this, "LA CEDULA NO EXISTE O EL ID INGRESADO NO ESTA ASIGNADO A NINGUN PACIENTE", Toast.LENGTH_LONG).show()
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
            alertDialog.dismiss() }

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
               toSaveList.removeAt(viewHolder.adapterPosition)
               pref.save(toSaveList)
               adapter.deleteItem(viewHolder.adapterPosition)
               val snackbar = Snackbar.make(binding.root,"Paciente eliminado de la lista",Snackbar.LENGTH_LONG).show()
           }
       }
    val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvPacientes)
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