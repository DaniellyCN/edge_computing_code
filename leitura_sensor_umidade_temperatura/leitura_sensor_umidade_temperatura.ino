#include <DHT.h>
#include <ArduinoJson.h>

#define DHTPIN 4
#define DHTTYPE DHT11

DHT dht(DHTPIN, DHTTYPE);

void setup() {
  
  Serial.begin(9600);
  dht.begin();
}

void loop() {
  float humidity = dht.readHumidity();
  float temperature = dht.readTemperature();

  if (isnan(humidity) || isnan(temperature)) {
    Serial.println("Erro ao ler o sensor!");
    return;
  }

// Cria um objeto JSON
  StaticJsonDocument<200> jsonDoc;

  // Adiciona dados ao JSON
  jsonDoc["temperature"] = temperature;
  jsonDoc["humidity"] = humidity;

  // Serializa o JSON para uma string e envia para o Serial Monitor
  String jsonString;
  serializeJson(jsonDoc, jsonString);
  Serial.println(jsonString);

  

  delay(60000);// 1 minuto
}