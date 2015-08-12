
[CaveSurvey](https://github.com/lz1asl/CaveSurvey) device emulator.

The goal is to use your computer's Bluetooth radio to simulate measurement device (laser distance meters - CEM, Trimble, LTI, DistoX, etc) that is not currently physically present e.g. connect with CaveSurvey from Android phone/tablet and verify codebase update is still compatible with all supported devices.

How to run :
 - On Linux you may need to install libbluetooth-dev
 - mvn jetty:run as root to start server
 - http://localhost:8080/ to proceed
 
