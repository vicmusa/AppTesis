package com.example.apptesis.model

import com.example.apptesis.R

class Repository {

    companion object{


        // SINTOMAS

        val fiebre = InnerInfoModel("Fiebre", R.raw.fiebre,"Se considera fiebre si la temperatura de la axila de 99 °F (37,2 °C) o mayor. Casi un 90% de los contagiados de COVID-19 padece de fiebre")
        val tos = InnerInfoModel("Tos Seca", R.raw.tos,"Tos irritante e insistente, no presenta flema o mucosidad y puede ser intensa a tal punto de producir ataques de tos. Alrededor del 67% de la población contagiada reporta haber presentado este síntoma.")
        val respirar = InnerInfoModel("Disnea", R.raw.respirar,"Dificultad para respirar o falta de aire. El 18,6 % de los contagiados reporta haber tenido dificultad para respirar.")
        val cabeza = InnerInfoModel("Cefalea", R.raw.cabeza,"Dolor de cabeza. Se reporta alrededor de 13,6% de contagiados con este síntoma.")
        val fatiga = InnerInfoModel("Fatiga", R.raw.fatiga,"Sensacion de falta de energía, de agotamiento o de cansancio. El 38% de los pacientes de COVID-19 registra haber experimentado fatiga.")
        val neumonia = InnerInfoModel("Neumonía",R.raw.neumo,"Infección que inflama los sacos aéreos de uno o ambos pulmones, estos se pueden llenar de líquido o pus provocando tos con flema, fiebre, escalofríos y dificultad para respirar.")
        val garganta = InnerInfoModel("Faringitis", R.raw.garganta,"Dolor de garganta, carraspera o irritación de la garganta que a menudo empeora al tragar. Aproximadamente el 14% de los pacientes de COVID-19 reporta haber presentado este síntoma.")
        val perdida = InnerInfoModel("Perdida del gusto y el olfalto", R.raw.olfato,"El 88% de los pacientes con COVID-19 presentan pérdida del olfato y del gusto, la mayoría de estos pacientes recupera los sentidos de forma espontánea con el paso del tiempo.")
        val diarrea = InnerInfoModel("Diarrea", R.raw.diarrea,"Heces acuosas y blandas. Se considera diarrea si se evacúa heces sueltas tres o más veces en un día. Alrededor de 3,7% de los pacientes de COVID-19 presenta este síntoma")
        val nauseas = InnerInfoModel("Nauseas", R.raw.nauseas,"Sensación de enfermedad o malestar en el estómago que puede aparecer con una necesidad imperiosa de vomitar. Se calcula que un 5% de los diagnosticados presenta este síntoma")

        val listsintom = listOf(fiebre, tos, respirar, perdida, neumonia, garganta, cabeza, fatiga, diarrea, nauseas)


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