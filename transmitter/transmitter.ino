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
  static int temperature;

  timeStart = millis();
  
  
  temperature = calculateTemperature();
  

  sprintf(text, "Temperature: %d", temperature);
  
  //Send message to receiver
  radio.write(&text, sizeof(text));

  
  while(millis() < PERIOD + timeStart) ;//wait if not ready to send message
}


double calculateTemperature()
{
  
  
  return 0.0;
  
}
