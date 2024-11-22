
# **Estação Meteorológica Mínima com Edge Computing** ☀️⛈️🌤️

## **Descrição**
Este projeto implementa uma estação meteorológica mínima que coleta dados de temperatura e umidade utilizando um sensor conectado a uma placa Arduino Uno. Os dados são processados em janelas de tempo de 10 minutos ⏱️ com o **Apache Flink** e enviados para o **ThingSpeak**, que fornece uma interface web para visualização e análise dos dados.

---

## **Como Acessar os Dados** 
Os dados coletados e processados já estão disponíveis em tempo real por meio do **ThingSpeak**. Acesse diretamente no link abaixo:

🔗 **[Acessar Dados no ThingSpeak](https://thingspeak.mathworks.com/channels/2755986)**

Não é necessário configurar nada adicional. Toda a análise e visualização é feita pela interface do ThingSpeak.

---

## **Tecnologias Utilizadas**👩‍💻
- **Hardware**: Arduino Uno, sensor de temperatura e umidade.
- **Software**: 
  - Apache Flink para processamento e análise dos dados.
  - ThingSpeak para visualização e armazenamento.
  - Linguagens: C (Arduino) e Java (processamento com Flink).

---

## **Fluxo do Projeto**
![image](https://github.com/user-attachments/assets/3581143e-01b1-4aa6-9318-995d8f31430b)

1. **Ingestão de Dados**: Sensor coleta temperatura e umidade.🎲
2. **Análise e Processamento**: Dados processados em janelas de 10 minutos utilizando Apache Flink.
3. **Entrega dos Dados**: Dados enviados para a API do ThingSpeak.
4. **Consumo dos Dados**: Visualização e análise via interface do ThingSpeak.

---

## **Como Executar** 
1. Conecte o sensor e a placa Arduino ao computador e carregue o código de coleta disponível na pasta `arduino/`.
2. Execute o script de processamento de dados em Java disponível na pasta `flink/`.
3. Os dados serão automaticamente enviados para o ThingSpeak, e você poderá visualizá-los no link:
   - 🔗 **[ThingSpeak - Visualização dos Dados](https://thingspeak.mathworks.com/channels/2755986)**
