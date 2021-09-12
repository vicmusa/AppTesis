package com.example.apptesis.model

import com.example.apptesis.R

class Repository {

    companion object{
        val fiebre = InnerInfoModel("Fiebre", R.raw.Fiebre,"FIEBRE...")
        val tos = InnerInfoModel("Tos Seca", R.raw.tos,"TOS SECA")
        val respirar = InnerInfoModel("Dificultad para respirar", R.raw.respirar,"DIFICULTAD PARA RESPIRAR")
        val cabeza = InnerInfoModel("Dolor de cabeza", R.raw.cabeza,"DOLOR DE CABEZA")
        val fatiga = InnerInfoModel("Fatiga", R.raw.fatiga,"FATIGA")
        val garganta = InnerInfoModel("Dolor de garganta", R.raw.garganta,"DOLOR DE GARGANTA")
        val perdida = InnerInfoModel("Perdida del gusto y el olfalto", R.drawable.perdida,"PERDIDA DEL GUSTO Y EL OLFATO")
        val conjuntivitis = InnerInfoModel("Conjuntivitis", R.drawable.conjuntivitis,"CONJUNTIVITIS")
        val diarrea = InnerInfoModel("Diarrea", R.raw.diarrea,"LA DIERRA ES UNA CAGALADERA DURISIMA")


        val listrecom1 = listOf<InnerInfoModel>(fiebre, tos, respirar, cabeza, fatiga, garganta, perdida, conjuntivitis, diarrea)

        val distancia = InnerInfoModel("Distanciamiento social", R.raw.distancia,"Mantenga una distancia segura de los demás, incluso si no parecen estar enfermos. Se recomienda mantener 2 metros de distancia")
        val mascarilla = InnerInfoModel("Usar mascarilla", R.raw.mascarilla,"Use una mascarilla en público, especialmente en interiores o cuando no sea posible el distanciamiento físico.")
        val espacios = InnerInfoModel("Evite espacios cerrados o con mucha gente", R.raw.gente,"Elija espacios abiertos y bien ventilados en lugar de cerrados y evite lugares con alto volúmen de personas.")
        val manos = InnerInfoModel("Lave sus manos con frecuencia", R.raw.manos,"Lávese las manos con frecuencia. Use agua y jabón o un desinfectante para manos a base de alcohol.")
        val cara = InnerInfoModel("Evite tocar su rostro", R.raw.cara,"Evite tocar su rostro con las manos")
        val casa = InnerInfoModel("Permanezca en casa", R.raw.casa,"Quédese en casa si no se siente bien o presenta algún síntoma")
        val vacuna = InnerInfoModel("Vacúnese", R.raw.vacuna,"Vacúnese cuando sea su turno. Siga las pautas locales sobre vacunación.")

        val listrecom2 = listOf<InnerInfoModel>(distancia, mascarilla, espacios, manos, cara, casa, vacuna)

        val recomendaciones = InfoModel("Recomendaciones", listrecom2)
        val sintomas = InfoModel("Sintomas",listrecom1)



    }
}