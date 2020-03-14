//Include Libraries
#include <SPI.h>
#include <nRF24L01.h>
#include <RF24.h>
#include "transmitter.h"

RF24 radio(CE_PIN, CSN_PIN);  // CE, CSN

//address through which two modules communicate.
const byte deviceAddress[6] = TIRE_ADDRESS;
const byte baseAddress[6] = BASE_ADDRESS;

void setup()
{
    
  radio.begin();  
  radio.setPALevel(RF24_PA_MAX);


}

void loop()
{



  waitForBeacon();
  sendSensorValues();
  
}

void waitForBeacon()
{
  radio.openReadingPipe(0, baseAddress);
  radio.startListening(); // listen for beacon from base address
  
  static char text[MAX_DATA_MSG_LENGTH] = {0};

  while(true)
  {

  //Read the data if available in buffer
    if(radio.available())
    {
      radio.read(&text, sizeof(text)); // put message received into text 
        
      if (text[0] == SEND_DATA_CMD)
        break;
    }
  }
}

void sendSensorValues()
{
  //Set module as transmitter
  radio.stopListening();
  radio.openWritingPipe(baseAddress);


  static char text[MAX_DATA_MSG_LENGTH];
  static int ambientV, IR1V, IR2V, IR3V, pressureV;
    
  pressureV = getVoltage(PRESSURE_PIN);
  ambientV = getVoltage(AMBIENT_PIN);
  IR1V = getVoltage(IR1_PIN);
  IR2V = getVoltage(IR2_PIN);
  IR3V = getVoltage(IR3_PIN);

  sprintf(text, "%d %d %d %d %d %d", DEVICE_ID, pressureV, ambientV, IR1V, IR2V, IR3V);
  delay(DEVICE_ID);
  //Send message to receiver
  radio.write(&text, sizeof(text));
  
}


int getVoltage(int pin)
{
  
  
  return analogRead(pin);
  
}
