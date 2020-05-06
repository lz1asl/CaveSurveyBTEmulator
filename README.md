
This is a device emulator for the [CaveSurvey](https://github.com/lz1asl/CaveSurvey) project.

The goal is to use your computer's Bluetooth radio to simulate measurement device that is not currently present (one of the supported [laser distance meters](https://github.com/lz1asl/CaveSurvey/wiki/Measurement-Devices) such as CEM, Trimble, LTI, DistoX, etc). CaveSurvey can connect to it and receive predefined measurements to verify the data exchange.

How to use :
 - On Linux you may need to install `libbluetooth-dev`
 - Run `mvn jetty:run` as root to start the server
 - Open http://localhost:8080/
 
Prebuild Java war available under [Actions](https://github.com/lz1asl/CaveSurveyBTEmulator/actions).
