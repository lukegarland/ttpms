//Include Libraries
#include <SPI.h>
#include <nRF24L01.h>
#include <RF24.h>
#include "TTPMS.h"

//create an RF24 object
RF24 radio(CE_PIN, CSN_PIN);  // CE, CSN

//address through which two modules communicate.
const byte address[6] = ADDRESS_1;

void setup()
{
    
  radio.begin();
  
  //set the address
  radio.openWritingPipe(address);
    radio.setPALevel(RF24_PA_MAX);

  //Set module as transmitter
  radio.stopListening();
}

void loop()
{
  static int i = 0;
  
  char text[MAX_DATA_MSG_LENGTH];

  //Send message to receiver
  sprintf(text, "Hello World %d", i++);
  radio.write(&text, sizeof(text));
  delay(100);
}
