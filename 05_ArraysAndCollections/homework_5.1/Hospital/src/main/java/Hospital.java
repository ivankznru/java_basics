public class Hospital {

    public static float[] generatePatientsTemperatures(int patientsCount) {
        float MIN_TEMPERATURE = 32;
        float MAX_TEMPERATURE = 40;
        float patientsTemperatures[] = new float[patientsCount];
        for (int i = 0; i < patientsCount; i++) {

            patientsTemperatures[i] = (float) Math.random() * (MAX_TEMPERATURE - MIN_TEMPERATURE) + MIN_TEMPERATURE;

        }

        return patientsTemperatures;
    }

    public static String getReport(float[] temperatureData) {
        int PLACES=2;
        int COEFFICIENT=10;
        float MIN_TEMPERATURE = 36.2f;
        float MAX_TEMPERATURE = 36.9f;
        float average = 0;
        int sumIll = 0;
        float sum = 0;
        float temperaturePatient = 0;
        String temperaturePatients = "";
        if (temperatureData.length > 0) {
            for (int i = 0; i < temperatureData.length; i++) {
                temperaturePatient = temperatureData[i];
                if (temperatureData[i] < MIN_TEMPERATURE || temperatureData[i] > MAX_TEMPERATURE) {
                    sumIll++;
                }
                temperaturePatients = temperaturePatients + Float.toString(temperaturePatient) + " ";
                sum += temperatureData[i];
            }
      //      При использовании метода Math.round() можно контролировать п-количество десятичных разрядов
            //      путем умножения и деления на 10^п :

            float scale = (float) Math.pow(COEFFICIENT, PLACES);
            average = (Math.round((sum / temperatureData.length) * scale) / scale);
        }


        String report =
                "Температуры пациентов: " + temperaturePatients.trim() +
                        "\nСредняя температура: " + average +
                        "\nКоличество здоровых: " + (temperatureData.length - sumIll);

        return report;
    }
}
