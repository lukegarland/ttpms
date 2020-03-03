//Include Libraries
#include <SPI.h>
#include <nRF24L01.h>
#include <RF24.h>
#include "transmitter.h"

RF24 radio(CE_PIN, CSN_PIN);  // CE, CSN

//address through which two modules communicate.
const byte deviceAddress[6] = ADDRESS_1;

void setup()
{
    
  radio.begin();
  
  //set the address
  radio.openWritingPipe(deviceAddress);
  
  radio.setPALevel(RF24_PA_MAX);

  //Set module as transmitter
  radio.stopListening();
}

void loop()
{
  static unsigned long timeStart;
  static char text[MAX_DATA_MSG_LENGTH];
  static int ambientV, IR1V, IR2V, IR3V;

  timeStart = millis();
  
  
  ambientV = getVoltage(AMBIENT_PIN);
  IR1V = getVoltage(IR1_PIN);
  IR2V = getVoltage(IR2_PIN);
  IR3V = getVoltage(IR3_PIN);

  sprintf(text, "%d %d %d %d %d", deviceAddress, ambientV, IR1V, IR2V, IR3V);
  
  //Send message to receiver
  radio.write(&text, sizeof(text));

  
  while(millis() < PERIOD + timeStart) ;//wait if not ready to send message
}


int getVoltage(int pin)
{
  
  
  return 0;
  
}
