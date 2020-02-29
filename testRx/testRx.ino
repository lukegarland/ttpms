//Include Libraries
#include <SPI.h>
#include <nRF24L01.h>
#include <RF24.h>

#include "TTPMS.h"
//create an RF24 object
RF24 radio(CE_PIN, CSN_PIN); 
const byte address[6] = ADDRESS_1;

void setup()
{
  while (!Serial);
    Serial.begin(9600);
  
  radio.begin();
  
  //set the address
  radio.openReadingPipe(0, address);

  //set power level
  radio.setPALevel(RF24_PA_MAX);
  
  //Set module as receiver
  radio.startListening();
}

void loop()
{
  //Read the data if available in buffer
  if (radio.available())
  {
    static char text[MAX_DATA_MSG_LENGTH] = {0};
    radio.read(&text, sizeof(text)); // put message received into text
    Serial.println(text);//print text to serial monitor
  }
}
