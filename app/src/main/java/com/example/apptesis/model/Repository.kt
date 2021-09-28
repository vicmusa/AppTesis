package com.example.apptesis.model

import com.example.apptesis.R

class Repository {

    companion object{


        // SINTOMAS

        val fiebre = InnerInfoModel("Fiebre", R.raw.fiebre,"FIEBRE...")
        val tos = InnerInfoModel("Tos Seca", R.raw.tos,"TOS SECA")
        val respirar = InnerInfoModel("Dificultad para respirar", R.raw.respirar,"DIFICULTAD PARA RESPIRAR")
        val cabeza = InnerInfoModel("Dolor de cabeza", R.raw.cabeza,"DOLOR DE CABEZA")
        val fatiga = InnerInfoModel("Fatiga", R.raw.fatiga,"FATIGA")
        val neumonia = InnerInfoModel("Neumonía",R.raw.neumo,"NEUMO")
        val garganta = InnerInfoModel("Dolor de garganta", R.raw.garganta,"DOLOR DE GARGANTA")
        val perdida = InnerInfoModel("Perdida del gusto y el olfalto", R.raw.olfato,"PERDIDA DEL GUSTO Y EL OLFATO")
        //val conjuntivitis = InnerInfoModel("Conjuntivitis", R.drawable.conjuntivitis,"CONJUNTIVITIS")
        val diarrea = InnerInfoModel("Diarrea", R.raw.diarrea,"LA DIERRA ES UNA CAGALADERA DURISIMA")
        val listsintom = listOf(fiebre, tos, respirar, perdida, neumonia, garganta, cabeza, fatiga, diarrea)

        // RECOMENDACIONES

        val distancia = InnerInfoModel("Distanciamiento social", R.raw.distancia,"Mantenga una distancia segura de los demás, incluso si no parecen estar enfermos. Se recomienda mantener 2 metros de distancia")
        val mascarilla = InnerInfoModel("Usar mascarilla", R.raw.mascarilla,"Use una mascarilla en público, especialmente en interiores o cuando no sea posible el distanciamiento físico.")
        val espacios = InnerInfoModel("Evite espacios cerrados", R.raw.gente,"Elija espacios abiertos y bien ventilados en lugar de cerrados y evite lugares con alto volúmen de personas.")
        val manos = InnerInfoModel("Lave sus manos con frecuencia", R.raw.manos,"Lávese las manos con frecuencia. Use agua y jabón o un desinfectante para manos a base de alcohol.")
        val cara = InnerInfoModel("Evite tocar su rostro", R.raw.cara,"Evite tocar su rostro con las manos")
        val casa = InnerInfoModel("Permanezca en casa", R.raw.casa,"Quédese en casa si no se siente bien o presenta algún síntoma")
        val vacuna = InnerInfoModel("Vacúnese", R.raw.vacuna,"Vacúnese cuando sea su turno. Siga las pautas locales sobre vacunación.")

        val listrecom2 = listOf<InnerInfoModel>(distancia, mascarilla, espacios, manos, cara, casa, vacuna)


        //INFOAPP

        /*val oxi = InnerInfoModel("Oxigenación",R.raw.oxi,"Mediante un sensor el dispositivo es capaz de medir la oxigenación de la persona")
        val hr = InnerInfoModel("Ritmo Cardiaco",R.raw.hr,"Mediante el mismo sensor es capaz de medir el ritmo cardiaco de la persona")
        val temp = InnerInfoModel("Temperatura",R.raw.tempe,"Mediante un sensor el dispositivo es capaz de medir la oxigenación de la persona")
        val listinfo = listOf(oxi,hr,temp)*/

        val recomendaciones = InfoModel("Recomendaciones", listrecom2)
        val sintomas = InfoModel("Sintomas", listsintom)



    }
}