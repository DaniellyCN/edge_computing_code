
# **Estação Meteorológica Mínima com Processamento em Apache Flink**

![image](https://github.com/user-attachments/assets/3581143e-01b1-4aa6-9318-995d8f31430b)

## **Descrição do Projeto**
Este projeto é uma estação meteorológica mínima que coleta dados de temperatura e umidade usando sensores conectados a uma placa Arduino Uno. Os dados são processados em tempo real por meio de **Apache Flink**, utilizando a linguagem **Java** em uma máquina com processador Intel i5. Após o processamento, os dados são disponibilizados através de uma **API** (como o ThingSpeak) e exibidos em um sistema web.

A proposta combina inovação tecnológica com computação distribuída para fornecer uma solução acessível e eficiente de monitoramento ambiental.

---

## **Funcionalidades**
- Coleta de dados meteorológicos em tempo real (temperatura e umidade).
- Processamento e agregação dos dados utilizando **janelas temporais de 10 minutos** no Apache Flink.
- Disponibilização dos dados via API para consumo externo.
- Visualização dos dados em um sistema web simples e amigável.

---

## **Tecnologias Utilizadas**
- **Arduino Uno**: Para coleta de dados dos sensores.
- **C++**: Para programação da placa Arduino.
- **Java**: Para implementar o processamento no Flink.
- **Apache Flink**: Para análise e processamento de fluxos contínuos de dados.
- **ThingSpeak API**: Para disponibilizar os dados processados.
- **HTML/CSS**: Para desenvolver o sistema web que consome os dados da API.

---

## **Como Funciona**
1. **Ingestão de Dados**: 
   - Um sensor DHT (temperatura e umidade) conectado ao Arduino Uno coleta as informações.
   - Os dados são enviados para a máquina onde o Flink está configurado.

2. **Análise e Processamento**:
   - Utilizando Java, o Apache Flink processa os dados em janelas temporais de 10 minutos, gerando médias.
   - O processamento é feito localmente na máquina com Intel i5.

3. **Entrega dos Dados**:
   - Os dados processados são enviados para a API ThingSpeak para armazenamento e consulta.

4. **Consumo dos Dados**:
   - O sistema web acessa os dados via API e exibe informações atualizadas sobre temperatura e umidade.

---

## **Pré-requisitos**
- Placa Arduino Uno e sensor DHT.
- Java 8 ou superior instalado.
- Apache Flink configurado na máquina.
- Conta no **ThingSpeak** para uso da API.
- Navegador web para visualizar o sistema.

---

## **Como Executar**
### 1. Configuração do Arduino
- Conecte o sensor DHT à placa Arduino conforme as especificações do hardware.
- Carregue o código em C++ (disponível no repositório) na placa.

### 2. Configuração do Flink
- Configure o ambiente do Flink em sua máquina seguindo o tutorial [oficial do Apache Flink](https://flink.apache.org/).
- Execute o job do Flink fornecido no diretório `/src`.

### 3. Configuração da API
- Registre-se no ThingSpeak e obtenha uma chave de API.
- Configure o job do Flink para enviar dados para a API ThingSpeak.

### 4. Sistema Web
- Abra o arquivo HTML no navegador ou implemente em um servidor local.

---

## **Contribuição**
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

---

## **Licença**
Este projeto está licenciado sob a [MIT License](./LICENSE).

---

