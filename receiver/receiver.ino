//Include Libraries
#include <SPI.h>
#include <RF24.h>
#include <nRF24L01.h>


#include "receiver.h"
RF24 radio(CE_PIN, CSN_PIN); 
const byte addressToReceive[6] = ADDRESS_1;


void setup() {
  // put your setup code here, to run once:
  while (!Serial);
    Serial.begin(RECEIVER_BAUD);

  radio.begin();

  radio.openReadingPipe(0, addressToReceive);


  radio.setPALevel(RF24_PA_MAX);

  //start reveiving
  radio.startListening();
}

void loop() {
  // put your main code here, to run repeatedly:

    //Read the data if available in buffer
  if (radio.available())
  {
    static char text[MAX_DATA_MSG_LENGTH] = {0};
    radio.read(&text, sizeof(text)); // put message received into text
    Serial.println(text);//print text to serial monitor
  }
  

}
