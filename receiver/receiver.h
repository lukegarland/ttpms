#ifndef RECEIVER_H
#define RECEIVER_H

#define CE_PIN 7
#define CSN_PIN 9
#define MAX_DATA_MSG_LENGTH 100
#define TIRE_ADDRESS "10000"
#define BASE_ADDRESS "20000"
#define PERIOD (1000) //50 for 20 Hz or 33.33 for 30 Hz

#define PRESSURE_PIN A0
#define AMBIENT_PIN A2
#define IR1_PIN A3
#define IR2_PIN A4
#define IR3_PIN A5

#define SEND_DATA_CMD 0xFF
#define DEVICE_DELAY 0

#define NUM_OF_TRANSMITTERS 1
#define RECEIVER_BAUD 9600

#endif
