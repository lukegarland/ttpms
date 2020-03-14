#ifndef TTPMS_H_
#define TTPMS_H_

#define CE_PIN 7
#define CSN_PIN 8
#define MAX_DATA_MSG_LENGTH 100
#define TIRE_ADDRESS "10000"
#define BASE_ADDRESS "20000"
#define DEVICE_ID 0000
#define PERIOD (1000) //50 for 20 Hz or 33.33 for 30 Hz

#define PRESSURE_PIN A0
#define AMBIENT_PIN A2
#define IR1_PIN A3
#define IR2_PIN A4
#define IR3_PIN A5

#define SEND_DATA_CMD 0xFF

#endif
